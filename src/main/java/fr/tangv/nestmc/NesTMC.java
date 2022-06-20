package fr.tangv.nestmc;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import fr.tangv.nestmc.game.TestListener;
import io.netty.channel.Channel;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PlayerConnection;

/**
 * @author tangv
 * Permet de démaré le plugin
 */
public class NesTMC extends JavaPlugin {
	
	private TestListener listener;
	
	@Override
	public void onEnable() {
		this.listener = new TestListener(this);
		Bukkit.getPluginManager().registerEvents(this.listener, this);
		for (Player player : Bukkit.getOnlinePlayers()) {
			EntityPlayer ep = ((CraftPlayer) player).getHandle();
			PlayerConnection co = ep.playerConnection;
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
