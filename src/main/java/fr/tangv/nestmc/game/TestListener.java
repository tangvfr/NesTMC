package fr.tangv.nestmc.game;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.server.v1_8_R3.EntityItemFrame;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.EnumDirection;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.Items;
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
	private PlayerConnection co;

	public TestListener(JavaPlugin plugin, short idMap) {
		this.plugin = plugin;
		this.idMap = idMap;
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
			
			ItemStack tt = new ItemStack(Items.FILLED_MAP, 1, this.idMap);
			EntityItemFrame eif = new EntityItemFrame(null);
			eif.setPosition(ep.locX, ep.locY, ep.locZ);
			eif.setDirection(EnumDirection.fromAngle(ep.yaw));
			
			co.sendPacket(new PacketPlayOutSpawnEntity(eif, 46));
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext chx, Object ob) throws Exception {
		

		super.channelRead(chx, ob);
	}

}
