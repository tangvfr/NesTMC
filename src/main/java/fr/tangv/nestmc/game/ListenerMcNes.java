package fr.tangv.nestmc.game;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

import fr.tangv.nestmc.game.controller.RequestController;


/**
 * @author Tangv - https://tangv.fr
 * Permet de capturé et traité les interation dans le jeu
 */
public class ListenerMcNes implements Listener {
	
	/**
	 * le gestionnaire de console
	 */
	private final McNesManager<?> manager;
	
	/**
	 * Permet de construire un captureur d'action
	 * @param manager les gestionnaire de console du serveur
	 */
	public ListenerMcNes(McNesManager<?> manager) {
		this.manager = manager;
	}
	
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		if (e.getMaterial() != Material.SEEDS) return;
		
		//l'action
		e.setCancelled(true);
		BlockFace face = e.getBlockFace();
		
		if (face == BlockFace.NORTH
			|| face == BlockFace.EAST
			|| face == BlockFace.SOUTH
			|| face == BlockFace.WEST
			) {
			Location loc = e.getClickedBlock().getRelative(face).getLocation();
			loc.setDirection(new Vector(face.getModX(), face.getModY(), face.getModZ()));
			//
		} else {
			e.getPlayer().sendMessage("Sense invalid !");
		}
	}
	
	@EventHandler
	public void onClick(PlayerToggleSneakEvent e) {
		Player player = e.getPlayer();
		RequestController req = this.manager.getRequest(player);
		
		//si il a une requet et qu'elle est valide
		if (req != null && req.calcValidity() > 0) {
			req.accept();
		}
	}
	
}
