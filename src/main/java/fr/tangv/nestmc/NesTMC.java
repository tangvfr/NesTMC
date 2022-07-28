package fr.tangv.nestmc;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import fr.tangv.nestmc.draw.Drawable;
import fr.tangv.nestmc.game.McNesManager;
import fr.tangv.nestmc.game.PacketMapBuffer;
import fr.tangv.nestmc.game.TestListener;
import fr.tangv.nestmc.game.controller.PlayerController;
import fr.tangv.nestmc.game.v1_8.PacketMapBufferV1_8;
import fr.tangv.nestmc.nes.controller.NesController;
import fr.tangv.nestmc.palette.tool.DrawableTest;
import fr.tangv.nestmc.palette.v1_8.MapColorV1_8;
import fr.tangv.nestmc.palette.v1_8.McNesPaletteV1_8;
import io.netty.channel.Channel;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.Items;
import net.minecraft.server.v1_8_R3.MapIcon;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketListenerPlayOut;
import net.minecraft.server.v1_8_R3.PacketPlayOutMap;
import net.minecraft.server.v1_8_R3.PacketPlayOutSetSlot;
import net.minecraft.server.v1_8_R3.PlayerConnection;

/**
 * @author Tangv - https://tangv.fr
 * Permet de démaré le plugin
 */
public class NesTMC extends JavaPlugin {
	
	private TestListener listener;
	
	@Override
	public void onEnable() {
		McNesManager<Packet<PacketListenerPlayOut>> mnm = new McNesManager<Packet<PacketListenerPlayOut>>(null, 0) {
			
			@Override
			public PlayerController createPlayerController(Player player, NesController controller) {
				return null;
			}
			
			@Override
			public PacketMapBuffer<Packet<PacketListenerPlayOut>> createPacketMapBuffer() {
				return null;
			}

			@Override
			public byte[] getPalette() {
				return McNesPaletteV1_8.MC_NES_PALETTE;
			}
		};
		
		mnm.start();
		PacketMapBufferV1_8 pmb = new PacketMapBufferV1_8(mnm);
		Drawable draw = new Drawable(128) {
			@Override
			public void setPixel(int x, int y, byte color) {
				pmb.setPixel(x, y, color);
			}
			@Override
			public void clearScreen(byte color) {}
		};
		DrawableTest.testDrawable(draw);
		draw.setColor(MapColorV1_8.BLACK_DARK);
		draw.fillCircle(64, 64, 6);
		

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
				}
			}
		}, this);
		

		this.listener = new TestListener(this, pmb.getIdMap());
		Bukkit.getPluginManager().registerEvents(this.listener, this);
		
		for (Player player : Bukkit.getOnlinePlayers()) {
			EntityPlayer ep = ((CraftPlayer) player).getHandle();
			PlayerConnection co = ep.playerConnection;
			this.listener.co = co;
			Channel ch = co.networkManager.channel;
			ch.pipeline().addBefore("packet_handler", ep.getName(), this.listener);
		}
	}
	
	@Override
	public void onDisable() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			EntityPlayer ep = ((CraftPlayer) player).getHandle();
			PlayerConnection co = ep.playerConnection;
			Channel ch = co.networkManager.channel;
			ch.pipeline().remove(ep.getName());
		}
	}
	
	
}
