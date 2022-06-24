package fr.tangv.nestmc.game;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import fr.tangv.nestmc.util.ReflectionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.EntityHorse;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.PacketPlayInEntityAction;
import net.minecraft.server.v1_8_R3.PacketPlayInEntityAction.EnumPlayerAction;
import net.minecraft.server.v1_8_R3.PacketPlayInHeldItemSlot;
import net.minecraft.server.v1_8_R3.PacketPlayInSteerVehicle;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity.EnumEntityUseAction;
import net.minecraft.server.v1_8_R3.PacketPlayOutAttachEntity;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment;
import net.minecraft.server.v1_8_R3.PacketPlayOutHeldItemSlot;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.Vector3f;

@Sharable
public class TestListener extends ChannelDuplexHandler implements Listener {

	private final JavaPlugin plugin;
	private PlayerConnection co;
	private int old;
	private int oldd;
	private int sofa;
	
	public TestListener(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	public void onEnable() {
		//this.listener = new TestListener(this);
		//Bukkit.getPluginManager().registerEvents(this.listener, this);
		for (Player player : Bukkit.getOnlinePlayers()) {
			EntityPlayer ep = ((CraftPlayer) player).getHandle();
			PlayerConnection co = ep.playerConnection;
			Channel ch = co.networkManager.channel;
			//ch.pipeline().addBefore("packet_handler", ep.getName(), this.listener);
		}
	}
	
	public void onDisable() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			EntityPlayer ep = ((CraftPlayer) player).getHandle();
			PlayerConnection co = ep.playerConnection;
			Channel ch = co.networkManager.channel;
			ch.pipeline().remove(ep.getName());
		}
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
		if (e.getMessage().equalsIgnoreCase("test")) {
			CraftPlayer player = (CraftPlayer) e.getPlayer();
			EntityPlayer ep = player.getHandle();
			this.co = ep.playerConnection;
			
			
			
			EntityHorse h = new EntityHorse(ep.getWorld());
			this.old = h.getId();
			h.setInvisible(true);//work
			h.setHealth(2F);
			h.setType(3);
			h.onGround = false;
			h.setPositionRotation(ep.locX, ep.locY - 1.2, ep.locZ, ep.yaw, 0);
			h.f(ep.yaw);//set head rotation
			co.sendPacket(new PacketPlayOutSpawnEntityLiving(h));//spawn
			co.sendPacket(new PacketPlayOutAttachEntity(0, ep, h));//mount
			
			EntityArmorStand ar = new EntityArmorStand(null);
			this.oldd = ar.getId();
			ar.setPosition(ep.locX, ep.locY - 0.2, ep.locZ);
			ar.setInvisible(true);//work
			co.sendPacket(new PacketPlayOutSpawnEntityLiving(ar));//spawn
			
			/*  private static final int WEAPON_SLOT = 0;
			  private static final int HELMET_SLOT = 4;
			  private static final int CHEST_SLOT = 3;
			  private static final int LEG_SLOT = 2;
			  private static final int BOOT_SLOT = 1;
			 */
			
			EntityArmorStand sf = new EntityArmorStand(null);
			this.sofa = sf.getId();
			sf.setPositionRotation(ep.locX, ep.locY - 0.5, ep.locZ, ep.yaw - 45F, 0F);
			sf.setHeadPose(new Vector3f(0F, 0F, 180F));
			sf.setSmall(true);
			sf.setInvisible(true);//work
			sf.setEquipment(4, new ItemStack(Item.getById(35), 1, 10));
			co.sendPacket(new PacketPlayOutSpawnEntityLiving(sf));//spawn
			co.sendPacket(new PacketPlayOutEntityEquipment(sf.getId(), 4, sf.getEquipment(4)));
			
			//set held
			player.getInventory().setHeldItemSlot(4);
			
			/*
			HELD for MENU
			CTRL for MENU
			ZQSD = UP LEFT DOWN RIGTH
			A = START
			ATTACK = SELECT
			SPACE = A
			INTERACT = B
			*/
		}
	}
	
	@Override
	public void channelRead(ChannelHandlerContext chx, Object ob) throws Exception {
		if (ob instanceof PacketPlayInSteerVehicle) {
			PacketPlayInSteerVehicle sv = (PacketPlayInSteerVehicle) ob;
			/*sv.a()//Positive to the left 
			sv.b()//Positive forward
			sv.c()//jump
			sv.d()//unmount*/
			//Bukkit.getLogger().info("left: "+sv.a()+" forward: "+sv.b()+" jump:"+sv.c()+" unmount: "+sv.d());
			
			return;
		}
		if (ob instanceof PacketPlayInEntityAction) {
			PacketPlayInEntityAction ea = (PacketPlayInEntityAction) ob;
			if (ea.b() == EnumPlayerAction.OPEN_INVENTORY) {
				Bukkit.getLogger().info("action: "+ea.b().name());
				return;
			}
		}
		if (ob instanceof PacketPlayInHeldItemSlot) {
			PacketPlayInHeldItemSlot hi = (PacketPlayInHeldItemSlot) ob;
			
			Bukkit.getLogger().info("held: "+ (4-hi.a()));
			
			if (hi.a() == 0) {
				co.sendPacket(new PacketPlayOutAttachEntity(0, co.player, null));//dismount
				co.sendPacket(new PacketPlayOutEntityDestroy(this.old));//despawn horse
				co.sendPacket(new PacketPlayOutEntityDestroy(this.oldd));//despawn
				co.sendPacket(new PacketPlayOutEntityDestroy(this.sofa));//despawn
			}
			
			co.sendPacket(new PacketPlayOutHeldItemSlot(4));
			return;
		}
		if (ob instanceof PacketPlayInUseEntity) {
			PacketPlayInUseEntity ue = (PacketPlayInUseEntity) ob;
			if (ue.a() != EnumEntityUseAction.INTERACT) {
				int id = (int) ReflectionUtil.getValue("a", ob);
				if (id == this.oldd) {
					Bukkit.getLogger().info("useentity: "+ ue.a().name());
					return;
				}
			}
		}
		
		super.channelRead(chx, ob);
	}
	
}
