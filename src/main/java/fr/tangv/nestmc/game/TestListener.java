package fr.tangv.nestmc.game;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class TestListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		
	}
	
	@EventHandler
	public void onSneak(PlayerToggleSneakEvent e){
		
	}
	
}
