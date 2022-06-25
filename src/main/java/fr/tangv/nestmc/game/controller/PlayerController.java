package fr.tangv.nestmc.game.controller;

import org.bukkit.entity.Player;

import fr.tangv.nestmc.nes.NesController;
import io.netty.channel.ChannelDuplexHandler;

/**
 * @author tangv
 * Permet de lier un joueur a un controlleur (une manette)
 */
public abstract class PlayerController extends ChannelDuplexHandler implements SofaController {

	//controler (manette) a la qu'elle on est associe
	private final NesController controller;
	//joueur sur qui on va récupéré les infos
	private final Player player;
	
	/**
	 * Construteur de contrustruire un récupérateur d'entré d'un joueur
	 * @param player joueur sur qui on va récupéré les infos
	 * @param controller controler (manette) a la qu'elle on est associe
	 */
	public PlayerController(Player player, NesController controller) {
		this.player = player;
		this.controller = controller;
	}

	/**
	 * Permet de récupérer le controller du controleur du joueur
	 * @return le controller du controleur du joueur
	 */
	public NesController getController() {
		return controller;
	}

	/**
	 * Permet de récupérer le player du controleur du joueur
	 * @return le player du controleur du joueur
	 */
	public Player getPlayer() {
		return player;
	}
	
}
