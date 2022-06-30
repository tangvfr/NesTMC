package fr.tangv.nestmc.game;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import fr.tangv.nestmc.util.ReflectionUtil;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.server.v1_8_R3.DataWatcher;
import net.minecraft.server.v1_8_R3.EntityItemFrame;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.EnumDirection;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.Items;
import net.minecraft.server.v1_8_R3.MathHelper;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity.EnumEntityUseAction;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntity;
import net.minecraft.server.v1_8_R3.PlayerConnection;

/**
 * @author Tangv - https://tangv.fr
 *
 */
@Sharable
public class TestListener extends ChannelDuplexHandler implements Listener {

	private final JavaPlugin plugin;
	private final short idMap;
	public PlayerConnection co;
	private EntityItemFrame eif;

	public TestListener(JavaPlugin plugin, short idMap) {
		this.plugin = plugin;
		this.idMap = idMap;
		Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {

			byte r = 0;
			@Override
			public void run() {
				if (co != null && eif != null) {
					co.sendPacket(new PacketPlayOutEntityTeleport(
							eif.getId(),
							MathHelper.floor(eif.locX * 32.0D),
							MathHelper.floor(eif.locY * 32.0D),
							MathHelper.floor(eif.locZ * 32.0D),
							r,
							(byte) 0, 
							true
							));
					r += 3;
				}
			}
		}, 0, 1L);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		CraftPlayer player = (CraftPlayer) e.getPlayer();
		EntityPlayer ep = player.getHandle();
		this.co = ep.playerConnection;

		co.networkManager.channel.pipeline().addBefore("packet_handler", ep.getName(), this);
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		CraftPlayer player = (CraftPlayer) e.getPlayer();
		EntityPlayer ep = player.getHandle();
		//co.networkManager.channel.pipeline().remove(ep.getName());
		co.networkManager.channel.eventLoop().submit(() -> {
			co.networkManager.channel.pipeline().remove(ep.getName());
		});
	}

	@EventHandler
	public void onChat(PlayerChatEvent e){
		if (e.getMessage().equalsIgnoreCase("frame")) {
			CraftPlayer player = (CraftPlayer) e.getPlayer();
			EntityPlayer ep = player.getHandle();

			EnumDirection dir = EnumDirection.fromAngle(ep.yaw).opposite();
			EntityItemFrame eif = new EntityItemFrame(null);
			eif.setPosition(ep.locX, ep.locY, ep.locZ);
			eif.setDirection(dir);
			
			//set item
			DataWatcher dw = eif.getDataWatcher();
			dw.watch(8, new ItemStack(Items.FILLED_MAP, 1, this.idMap));
		    dw.update(8);

			if (this.eif == null) {
				this.eif = eif;
			}
		    
			co.sendPacket(new PacketPlayOutSpawnEntity(eif, 71, dir.b()));
			co.sendPacket(new PacketPlayOutEntityMetadata(eif.getId(), dw, true));
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext chx, Object ob) throws Exception {
		if (ob instanceof PacketPlayInUseEntity) {
			 PacketPlayInUseEntity use = (PacketPlayInUseEntity) ob;
			 EnumEntityUseAction ac = use.a();
			 int idE = (int) ReflectionUtil.getValue(use, "a");
			 System.out.println("ac: "+ac.name()+" id:"+idE);
		}

		super.channelRead(chx, ob);
	}

}
