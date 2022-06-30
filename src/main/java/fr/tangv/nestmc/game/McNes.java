package fr.tangv.nestmc.game;

import java.util.List;

import org.bukkit.entity.Player;

import fr.tangv.nestmc.draw.FourMapScreen;
import fr.tangv.nestmc.game.controller.PlayerController;
import fr.tangv.nestmc.game.controller.RequestController;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.controller.NesController;
import fr.tangv.nestmc.nes.software.NesGui;

/**
 * @author Tangv - https://tangv.fr
 * une nes dans minecraft
 * @param <T> le type de packet envoie au joueur
 */
public abstract class McNes<T> extends TMCNes {

	private final Object obSync = new Object();//for eaxmple to sync
	
	/*gestionnaire de tous les nes sur le serveur*/
	private final McNesManager<T> manager;
	/*requets pour demmandé les controlleurs*/
	private RequestController firstRequest = null;
	private RequestController secondRequest = null;
	/*les controlleur associé a des joueurs*/
	private PlayerController firstPlayer = null;
	private PlayerController secondPlayer = null;

	/**
	 * Permet de construire une nes qui serait dans minecraft
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public McNes(McNesManager<T> manager) {//cette objet peux etre use par plussieur truc pensser a sync-- et cons a revoir
		super(new NesGui(new PacketMapBuffer[] {
				manager.createPacketMapBuffer(),
				manager.createPacketMapBuffer(),
				manager.createPacketMapBuffer(),
				manager.createPacketMapBuffer()
		}));
		this.manager = manager;
	}

	@Override
	public void update() {
		//udp input
		//udp menu // action
		synchronized (this.getScreen()) {
			//draw  //anvant de draw ou envoie les pquet de l'ecran penser a synchronized--
			//send
		}
	}
	
	/**
	 * Permet de faire une demmande de controlleur et d'envoyer le message approprié
	 * @param player joueur qui dait la requête
	 * @param isFirst true si c'est une requête pour le premier controlleur
	 * @return -1 impossible, 0 demmande faite, sinon le nombre de ms avant l'expiration de la dernier requêt
	 */
	public int request(Player player, boolean isFirst) {
		int r;
		int col = 8000;//tmp
		
		//--test--
		if (isFirst) {//si le premier
			//si le premier est libre
			if (this.firstPlayer == null) {
				//test si la requet est dispo ou expiré
				if (this.firstRequest == null) {
					r = 0;
				} else {
					r = this.firstRequest.calcValidity();
				}
				//test de r si la requet est accepter
				if (r == 0) {
					this.firstRequest = new RequestController(player, this, true, col);
					this.manager.addRequest(this.firstRequest);
				}
			} else {
				r = -1;
			}
		} else {//si le second
			//si le deucieme est libre
			if (this.secondPlayer == null) {
				//test si la requet est dispo ou expiré
				if (this.secondRequest == null) {
					r = 0;
				} else {
					r = this.secondRequest.calcValidity();
				}
				//test de r si la requet est accepter
				if (r == 0) {
					this.secondRequest = new RequestController(player, this, false, col);
					this.manager.addRequest(this.secondRequest);
				}
			} else {
				r = -1;
			}
		}
		
		//--messages--
		if (r == -1) {
			player.sendMessage("Place deja prise !");
		} else if (r <= 0) {
			player.sendMessage("Tu as 8sec pour t'asseoir !");
		} else {
			player.sendMessage("Une personne essay d'avoir la place il lui reste "+((r / 1000) + 1)+"sec !");
		}
		return r;
	}
	
	/**
	 * Permet de suprimé et envoyer un message au joueur de la requêt
	 * @param player joueur a qui envoyer le message
	 * @param isFirst true si c'est la requête concernais le premier controlleur
	 */
	public void timeoutRequest(Player player, boolean isFirst) {
		this.manager.removeRequest(player);
		player.sendMessage("Timeout sitdown ! (I will write real messsage)");
	}
	
	/**
	 * Permet de definir et d'afficher un nouveau PlayerController 
	 * @param player joueur que l'on va associé au controller
	 * @param isFirst true si c'est le premier controlleur
	 */
	public void openController(Player player, boolean isFirst) {
		this.manager.removeRequest(player);
		if (isFirst) {//si c'est le premier controlleur
			this.firstPlayer = this.manager.createPlayerController(player, (NesController) this.getFirstController());
			this.firstRequest = null;
			this.openController(this.firstPlayer);
		} else {//si c'est le deuxième controlleur controlleur
			this.secondPlayer = this.manager.createPlayerController(player, (NesController) this.getSecondController());
			this.secondRequest = null;
			this.openController(this.secondPlayer);
		}
	}
	
	/**
	 * Permet de detruire un PlayerController en envoyant les bon packet au personne qui le voyais
	 * @param isFirst true si c'est le premier controlleur
	 */
	private void closeController(boolean isFirst) {
		if (isFirst) {
			if (this.firstPlayer != null) {
				this.closeController(this.firstPlayer);
				this.firstPlayer = null;
			}
		} else {
			if (this.secondPlayer != null) {
				this.closeController(this.secondPlayer);
				this.secondPlayer = null;
			}
		}
	}

	@Override
	public void closeController(int controllers) {
		//fermeture du premier controlleur
		if ((controllers & TMCNes.FIRST_CONTROLLER) != 0) {
			this.closeController(true);
		}
		//fermeture du second controlleur
		if ((controllers & TMCNes.SECOND_CONTROLLER) != 0) {
			this.closeController(false);
		}
	}

	/**
	 * Permet d'obtenir un packet nms pour mettre a jour l'une des maps qui forme l'écran
	 * @param index index de la map du pakcet souhaité
	 * @return un packet de mise a jour d'une map de l'écran de la nes
	 */
	@SuppressWarnings("unchecked")
	public T getPacket(int index) {
		return ((PacketMapBuffer<T>) ((FourMapScreen) this.getScreen()).getBitScreens()[index]).getPacket();
	}

	/**
	 * Permet de crée un PlayerController en envoyant les bon packet au personne qui doivent le voir
	 * @param control le PlayerController a cree
	 */
	public abstract void openController(PlayerController control);
	
	/**
	 * Permet de detruire un PlayerController en envoyant les bon packet au personne qui le voyais
	 * @param control le PlayerController a detruire
	 */
	public abstract void closeController(PlayerController control);
	
	/**
	 * Permet de créé la nes - nms
	 * @param list la liste de tous les joueur censer voir la nes
	 */
	public abstract void create(List<Player> list);

	/**
	 * Permet d'afficher la nes a un joueur - nms
	 * @param player le joueur a qui on affiche la nes
	 */
	public abstract void show(Player player);

	/**
	 * Permet de cacher la nes a un joueur - nms
	 * @param player le joueur a qui on cache la nes
	 * @param quit true si le joueur est retiré a cause qu'il se soit déco
	 */
	public abstract void hide(Player player, boolean quit);

	/**
	 * Permet de détruire la nes - nms
	 */
	public abstract void destruct();

}
