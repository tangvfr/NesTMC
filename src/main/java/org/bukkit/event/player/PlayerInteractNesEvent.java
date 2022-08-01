package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import fr.tangv.nestmc.game.McNes;

/**
 * @author Tangv - https://tangv.fr
 * Evenement appler lorsqu'un joueur interagie avec une NES dans le monde
 */
public class PlayerInteractNesEvent extends PlayerEvent {

	private static final HandlerList HANDLERS_LIST = new HandlerList();
	
	private final McNes<?> nes;//console avec laquelle a interagie le joueur
	private final boolean attack;//si le joueur a fait un clique gauche
	
	/**
	 * Permet de construire une interaction avec une NES dans le monde
	 * @param player joueur qui interagie
	 * @param nes console avec laquelle a interagie le joueur
	 * @param attack true si le joueur a fait un clique gauche
	 */
	public PlayerInteractNesEvent(Player player, McNes<?> nes, boolean attack) {
		super(player, false);
		this.nes = nes;
		this.attack = attack;
	}
	
	/**
	 * Permet de savoir si le joueur a fait un clique gauche
	 * @return true si le joueur a fait un clique gauche
	 */
	public boolean isAttack() {
		return this.attack;
	}
	
	/**
	 * Permet de savoir si le joueur a fait un clique droit
	 * @return true si le joueur a fait un clique droit
	 */
	public boolean isInteract() {
		return !this.attack;
	}
	
	/**
	 * Permet de récupérer la nes avec laquelle a interagie le joueur
	 * @return la nes avec laquelle a interagie le joueur
	 */
	public McNes<?> getNes() {
		return this.nes;
	}

	@Override
	public HandlerList getHandlers() {
		return PlayerInteractNesEvent.HANDLERS_LIST;
	}
	
}
