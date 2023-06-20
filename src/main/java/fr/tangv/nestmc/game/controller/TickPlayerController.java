package fr.tangv.nestmc.game.controller;

import fr.tangv.nestmc.nes.controller.InputController;
import fr.tangv.nestmc.nes.controller.NesController;
import org.bukkit.entity.Player;

/**
 * @author Tangv - https://tangv.fr
 * C'est une liasons de manette de nes avec un joueur normal sauf que pour les boutons utilsés par la nes et qui sont possibles que par implution ils auront une implussions de x temps (click souris et ouvrir l'inventaire)
 */
public abstract class TickPlayerController extends PlayerController {
	
	/*nombre de ticks de persistence*/
	private final int tickPers;
	
	/*nombre de ticks restant pour click d'attaque*/
	private int tickAttack = 0;
	
	/*nombre de ticks restant pour click d'interaction*/
	private int tickInteract = 0;
	
	/*nombre de ticks restant pour l'ouverture de l'inventaire*/
	private int tickInventory = 0;
	
	/**
	 * Construteur de contrustruire un récupérateur d'entré d'un joueur
	 * @param player joueur sur qui on va récupéré les infos
	 * @param controller controler (manette) a la qu'elle on est associe
	 * @param tickPers nombre de ticks de persitance des boutons de souris et d'ouverture d'inventaire
	 */
	public TickPlayerController(Player player, NesController controller, int tickPers) {
		super(player, controller);
		this.tickPers = tickPers;
	}
	
	@Override
	public void updateController() {
		NesController nc = this.getController();
		nc.update();
		//release les bouton qui le fond pas eux meme
		nc.setHeld(0);
		
		int btns = 0;
		synchronized (this) {
			//release pour le clique d'attaque
			if (this.tickAttack <= 0) {
				btns += InputController.ATTACK;
			} else {
				this.tickAttack--;
			}
			
			//release pour le clique d'interaction
			if (this.tickInteract <= 0) {
				btns += InputController.INTERACT;
			} else {
				this.tickInteract--;
			}
			
			//release pour le clique d'interaction
			if (this.tickInventory <= 0) {
				btns += InputController.OPEN_INV;
			} else {
				this.tickInventory--;
			}
		}
		nc.releaseButton(btns);
	}
	
	/**
	 * Permet d'appuyer sur la touche d'attack avec la persitence definie (tickPers)
	 */
	protected void pressAttack() {
		synchronized (this) {
			this.tickAttack = this.tickPers;
		}
		this.getController().pressButton(InputController.ATTACK);
	}
	
	/**
	 * Permet d'appuyer sur la touche d'interaction avec la persitence definie (tickPers)
	 */
	protected void pressInteract() {
		synchronized (this) {
			this.tickInteract = this.tickPers;
		}
		this.getController().pressButton(InputController.INTERACT);
	}
	
	/**
	 * Permet d'appuyer sur la touche d'ouverture de l'inventaire avec la persitence definie (tickPers)
	 */
	protected void pressInventory() {
		synchronized (this) {
			this.tickInventory = this.tickPers;
		}
		this.getController().pressButton(InputController.OPEN_INV);
	}

}
