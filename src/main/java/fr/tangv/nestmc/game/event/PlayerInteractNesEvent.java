package fr.tangv.nestmc.game.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.tangv.nestmc.game.McNes;

/**
 * @author Tangv - https://tangv.fr
 * Evenement appler lorsqu'un joueur interagie avec une NES dans le monde
 */
public class PlayerInteractNesEvent extends Event {

	private static final HandlerList HANDLERS = new HandlerList();
	
	private final McNes<?> nes;//console avec laquelle a interagie le joueur
	private final boolean attack;//si le joueur a fait un clique gauche
	private final Player player;//joueur qui a provoqué l'evenement
	
	/**
	 * Permet de construire une interaction avec une NES dans le monde
	 * @param player joueur qui interagie
	 * @param nes console avec laquelle a interagie le joueur
	 * @param attack true si le joueur a fait un clique sur l'écran
	 */
	public PlayerInteractNesEvent(Player player, McNes<?> nes, boolean attack) {
		super(false);
		this.player = player;
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

	/**
	 * Permet de récupérer le joueur qui a fait le clique
	 * @return le joueur qui a fait le clique
	 */
	public Player getPlayer() {
		return this.player;
	}

	public static HandlerList getHandlerList() {
        return PlayerInteractNesEvent.HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return PlayerInteractNesEvent.HANDLERS;
    }
	
}
