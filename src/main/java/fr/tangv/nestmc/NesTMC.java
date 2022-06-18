package fr.tangv.nestmc;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.tangv.nestmc.game.TestListener;

/**
 * @author tangv
 * Permet de démaré le plugin
 */
public class NesTMC extends JavaPlugin {
	
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new TestListener(), this);
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
}
