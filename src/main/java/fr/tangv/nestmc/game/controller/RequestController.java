package fr.tangv.nestmc.game.controller;

import org.apache.commons.lang.Validate;
import org.bukkit.entity.Player;

import fr.tangv.nestmc.game.McNes;

/**
 * @author Tangv - https://tangv.fr
 * 
 * Permet de formuler une requet
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
	 */
	public void accept() {
		this.nes.openController(this.player, this.isFirst);
	}
	
}
