package fr.tangv.nestmc;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.tangv.nestmc.game.McNesManager;
import fr.tangv.nestmc.game.v1_8.McNesManagerV1_8;

/**
 * @author Tangv - https://tangv.fr
 * Permet de démaré le plugin
 */
public class NesTMC extends JavaPlugin {
	
	/*
	 * getionaire des consoles
	 */
	private McNesManager<?> manager;
	
	@Override
	public void onEnable() {
		this.manager = new McNesManagerV1_8(this, null);
		for (Player player : Bukkit.getOnlinePlayers()) {//pour tous les joueur
			this.manager.playerJoin(player);
		}
	}
	
	@Override
	public void onDisable() {
		this.manager.stop();
		for (Player player : Bukkit.getOnlinePlayers()) {//pour tous les joueur
			this.manager.playerReload(player);
		}
		this.manager = null;
	}
	
}
