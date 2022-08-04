package fr.tangv.nestmc.game;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

import fr.tangv.nestmc.game.controller.RequestController;
import fr.tangv.nestmc.game.event.PlayerInteractNesEvent;

/**
 * @author Tangv - https://tangv.fr
 * Permet de capturé et traité les interation dans le jeu
 */
public class ListenerMcNes implements Listener {
	
	/*le gestionnaire de console*/
	private final McNesManager<?> manager;
	
	/**
	 * Permet de construire un captureur d'action
	 * @param manager le gestionnaire de console du serveur
	 */ 
	public ListenerMcNes(McNesManager<?> manager) {
		this.manager = manager;
	}
	
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		if (e.getMaterial() == Material.SEEDS
			&& e.getAction() == Action.RIGHT_CLICK_BLOCK
		) {
			Player player = e.getPlayer();
			BlockFace face = e.getBlockFace();
			e.setCancelled(true);
			
			if (face == BlockFace.NORTH
				|| face == BlockFace.EAST
				|| face == BlockFace.SOUTH
				|| face == BlockFace.WEST
				) {//test si la face est compatible
				Location loc = e.getClickedBlock().getRelative(face).getLocation();
				loc.setDirection(new Vector(face.getModX(), face.getModY(), face.getModZ()));
				
				if (this.manager.createNes(loc) == null) {//creation de la console
					player.sendMessage("Nombre de nes maximal atteint");
				}
			} else {//si la face ne peux pas correspondre
				player.sendMessage("Invalid direction !");
			}
		}
	}
	
	@EventHandler
	public void onSneak(PlayerToggleSneakEvent e) {
		Player player = e.getPlayer();
		RequestController req = this.manager.getRequest(player);
		
		//si il a une requet et qu'elle est valide
		if (req != null && req.calcValidity() > 0) {
			req.accept();
		}
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		this.manager.updateAroundConsoles(e.getPlayer());
	}
	
	@EventHandler
	public void onNes(PlayerInteractNesEvent e) {
		Player player = e.getPlayer();
		if (e.isInteract()) {
			McNes<?> nes = e.getNes();
			nes.request(player, true);
			//1er MAGENTA, 2eme CYAN      & add time on click inv, and mouse & make message                
			System.out.println("Requet envoyé ! pour le first");
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		this.manager.playerJoin(e.getPlayer());
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		this.manager.quit(player);
		this.manager.playerQuit(player);
	}
	
}
