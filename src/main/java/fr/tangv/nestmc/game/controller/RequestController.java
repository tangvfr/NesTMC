package fr.tangv.nestmc.game.controller;

import fr.tangv.nestmc.game.McNes;
import org.apache.commons.lang.Validate;
import org.bukkit.entity.Player;

/**
 * @author Tangv - https://tangv.fr
 * 
 * Permet de formuler une requet pour accepder a une manette d'une nes
 */
public class RequestController {
	
	/*joueur sur qui on va récupéré les infos*/
	private final Player player;
	/*console associé a la requet*/
	private final McNes<?> nes;
	/*si c'est le premier controller*/
	private final boolean isFirst;
	/*temps du moment ou a été faite la requet*/
	private final long createdTime = System.currentTimeMillis();
	/*temps pour accepté*/
	private final int cooldown;
	
	/**
	 * Permet de construire une requêt de demmande de controller valide pendant 8sec
	 * @param player le joueur associé
	 * @param nes la nes associé
	 * @param isFirst true si c'est le premier controller
	 * @param cooldown temps maxiume pour accepté
	 */
	public RequestController(Player player, McNes<?> nes, boolean isFirst, int cooldown) {
		Validate.isTrue(cooldown > 0, "Cooldown can't <= 0");
		this.player = player;
		this.nes = nes;
		this.isFirst = isFirst;
		this.cooldown = cooldown;
	}

	/**
	 * Permet de récupérer le joueur de la requête
	 * @return le joueur de la requête
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * Permet d'obtenir la durée de validité restante et de suprimé la requet si le temps est déppaser
	 * @return la durée de validité restante en ms
	 */
	public int calcValidity() {
		long cooldown = this.cooldown - (System.currentTimeMillis() - this.createdTime);
		if (cooldown <= 0) {
			cooldown = 0;
			this.nes.timeoutRequest(this.player, this.isFirst);
		}
		return (int) cooldown;
	}
	
	/**
	 * Permet de validé la requet de creation du PlayerController pour la nes
	 * @return false si le joueur est trop loin
	 */
	public boolean accept() {
		if (this.nes.canSee(this.player)) {//test si le joueur est assez proche de la nes et donc si il la vois
			this.nes.openController(this.player, this.isFirst);
			return true;
		} else {
			this.nes.getManager().removeRequest(this.player);
			this.player.sendMessage("You are too far !");
			return false;
		}
	}

	/**
	 * Permet de récupérer la console a qui est affectué la requet
	 * @return la console a qui est affectué la requet
	 */
	public McNes<?> getNes() {
		return this.nes;
	}
	
}
