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

import fr.tangv.nestmc.draw.Drawable;
import fr.tangv.nestmc.game.v1_8.PacketMapBufferV1_8;
import fr.tangv.nestmc.palette.tool.DrawableTest;
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
	
	/*McNesManager mnm = new McNesManager();
	mnm.start();
	PacketMapBufferV1_8 pmb = new PacketMapBufferV1_8(mnm);
	DrawableTest.testDrawable(new Drawable(128) {
		@Override
		public void setPixel(int x, int y, byte color) {
			pmb.setPixel(x, y, color);
		}
		@Override
		public void clearScreen(byte color) {}
	});
	
	Bukkit.getPluginManager().registerEvents(new Listener() {
		@EventHandler
		public void onChat(PlayerChatEvent e) {
			if (e.getMessage().equals("item")) {
				ItemStack tt = new ItemStack(Items.FILLED_MAP, 1, pmb.getIdMap());
				//((CraftPlayer) e.getPlayer()).getHandle().inventory.items[0] = tt;dont work for id map special
				((CraftPlayer) e.getPlayer()).getHandle().playerConnection.sendPacket(new PacketPlayOutSetSlot(0, 37, tt));
			} else if (e.getMessage().equals("lime")) {
				byte[] bb = new byte[128*128];
				for (int i = 0; i < 128*128; i++) {
					bb[i] = MapColorV1_8.LIME_NORMAL;
				}
				PacketPlayOutMap map = new PacketPlayOutMap(pmb.getIdMap(), (byte)0, new ArrayList<MapIcon>(), bb, 64, 64, 64, 64);
				((CraftPlayer) e.getPlayer()).getHandle().playerConnection.sendPacket(map);
			} else if (e.getMessage().equals("draw")) {
				System.out.println("selected idmap: "+pmb.getIdMap());
				((CraftPlayer) e.getPlayer()).getHandle().playerConnection.sendPacket(pmb.getPacket());
			} else if (e.getMessage().equals("draw2")) {
				((CraftPlayer) e.getPlayer()).getHandle().playerConnection.sendPacket(
						new PacketPlayOutMap(pmb.getIdMap(), (byte)0, new ArrayList<MapIcon>(), pmb.getBuffer(), 0, 0, 128, 128)
						);
			}
		}
	}, this);
	*/
}
