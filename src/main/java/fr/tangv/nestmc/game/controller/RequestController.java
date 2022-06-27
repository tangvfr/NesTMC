package fr.tangv.nestmc.game.controller;

import org.bukkit.entity.Player;

import fr.tangv.nestmc.game.McNes;

/**
 * @author tangv
 * Permet de formuler une requet
 */
public class RequestController {

	//joueur sur qui on va récupéré les infos
	private final Player player;
	//console associé a la requet
	private final McNes nes;
	//si c'est le premier controller
	private final boolean isFirst;
	
	/**
	 * Permet de construire une requêt de demmande de controller
	 * @param player le joueur associé
	 * @param nes la nes associé
	 * @param isFirst true si c'est le premier controller
	 */
	public RequestController(Player player, McNes nes, boolean isFirst) {
		this.player = player;
		this.nes = nes;
		this.isFirst = isFirst;
	}
	
	public void accept() {
		t//his.nes = 
	}
	
	public void reject() {
		
	}
	
}
