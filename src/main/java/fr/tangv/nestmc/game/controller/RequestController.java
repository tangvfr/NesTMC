package fr.tangv.nestmc.game.controller;

import org.bukkit.entity.Player;

import fr.tangv.nestmc.game.McNes;

/**
 * @author Tangv - https://tangv.fr
 * 
 * Permet de formuler une requet
 */
public class RequestController {

	private static final int TIME_TO_SNEAK = 8_000;
	
	//joueur sur qui on va récupéré les infos
	private final Player player;
	//console associé a la requet
	private final McNes nes;
	//si c'est le premier controller
	private final boolean isFirst;
	//temps du moment ou a été faite la requet
	private final long timeEnd;
	
	/**
	 * Permet de construire une requêt de demmande de controller valide pendant 8sec
	 * @param player le joueur associé
	 * @param nes la nes associé
	 * @param isFirst true si c'est le premier controller
	 */
	public RequestController(Player player, McNes nes, boolean isFirst) {
		this.player = player;
		this.nes = nes;
		this.isFirst = isFirst;
		this.timeEnd = System.currentTimeMillis();
	}
	
	
	public int isValid() {
		// - > TIME_TO_SNEAK
	}
	
	public void accept() {
		t//his.nes = 
		/*this.create();
		this.hide(player);*/
	}
	
	public void reject() {
		
	}
	
}
