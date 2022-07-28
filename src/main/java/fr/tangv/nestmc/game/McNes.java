package fr.tangv.nestmc.game;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.tangv.nestmc.draw.Drawable;
import fr.tangv.nestmc.draw.FourMapScreen;
import fr.tangv.nestmc.game.controller.PlayerController;
import fr.tangv.nestmc.game.controller.RequestController;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.controller.NesController;
import fr.tangv.nestmc.nes.software.NesGui;
import fr.tangv.nestmc.nes.software.NesOs;
import fr.tangv.nestmc.nes.software.TMCNesOs;

/**
 * @author Tangv - https://tangv.fr
 * une nes dans minecraft
 * @param <T> le type de packet envoier au joueur
 */
public abstract class McNes<T> extends TMCNes {

	/*objet pour sycronizer les données*/
	private final Object obSync = new Object();
	
	/*gestionnaire de tous les nes sur le serveur*/
	private final McNesManager<T> manager;
	/*position de la nes sur la map*/
	private final Location location;
	
	/*requets pour demmandé les controlleurs*/
	private RequestController firstRequest = null;
	private RequestController secondRequest = null;
	/*les controlleur associé a des joueurs*/
	private PlayerController firstPlayer = null;
	private PlayerController secondPlayer = null;

	/**
	 * Permet de construire une nes qui serait dans minecraft
	 * @param manager gestionnaire des nes sur le serveur
	 * @param loc possition de la nes dans le monde
	 */
	public McNes(McNesManager<T> manager, Location loc) {
		super(
			new NesGui(
				new PacketMapBuffer[] {
					manager.createPacketMapBuffer(),
					manager.createPacketMapBuffer(),
					manager.createPacketMapBuffer(),
					manager.createPacketMapBuffer()
					},
				manager.getPalette()
				),
			new TMCNesOs()
			);
		this.manager = manager;
		this.location = loc;
	}

	@Override
	public void update() {
		//update input
		synchronized (this.obSync) {
			if (this.firstPlayer != null) this.firstPlayer.updateController();
			if (this.secondPlayer != null) this.secondPlayer.updateController();
		}
		
		NesOs os = this.getOs();
		//update os
		os.update(this, this.getFirstController(), this.getSecondController(), this.getMixedController());
		Drawable screen = this.getScreen();
		
		synchronized (screen) {
			//render os on screen
			os.render(this, screen);
			//send packet to player
			if (os.isSend()) {
				for (PacketMapBuffer<T> packet : this.getMapPackets()) {
					this.sendPacket(packet.getPacket());
				}
			}
		}
	}
	
	/**
	 * Permet d'obtenir les 4 map qui ont les packets
	 * @return les 4 map qui ont les packets
	 */
	@SuppressWarnings("unchecked")
	public PacketMapBuffer<T>[] getMapPackets() {
		return (PacketMapBuffer<T>[]) ((FourMapScreen) this.getScreen()).getBitScreens();
	}
	
	/**
	 * Permet de faire une demmande de controlleur et d'envoyer le message approprié
	 * @param player joueur qui dait la requête
	 * @param isFirst true si c'est une requête pour le premier controlleur
	 * @return -1 impossible, 0 demmande faite, sinon le nombre de ms avant l'expiration de la dernier requêt
	 */
	public void request(Player player, boolean isFirst) {
		RequestController rcontrol = null;
		int remainingTime;
		int col = 8000;//temps de validiter d'une requet
		
		synchronized (this.obSync) {
			if (isFirst) {//si le premier
				//si le premier est libre
				if (this.firstPlayer == null) {
					remainingTime = this.validdity(this.firstRequest);
					//test de r si la requet est accepter
					if (remainingTime == 0) {
						rcontrol = new RequestController(player, this, true, col);
						this.firstRequest = rcontrol;
					}
				} else {
					remainingTime = -1;
				}
			} else {//si le second
				//si le deucieme est libre
				if (this.secondPlayer == null) {
					remainingTime = this.validdity(this.secondRequest);
					//test de r si la requet est accepter
					if (remainingTime == 0) {
						rcontrol = new RequestController(player, this, false, col);
						this.secondRequest = rcontrol;
					}
				} else {
					remainingTime = -1;
				}
			}
		}
		
		//ajout la requets si elle est crée
		if (rcontrol != null) {
			this.manager.addRequest(this.firstRequest);
		}
		player.sendMessage(this.requestMsg(remainingTime));
	}
	
	/**
	 * Permet d'obtenir le message en focntion du temps restant pour faire une requet de controlleur
	 * @param time temps en ms avant de pouvoir faire une rêquet, -1 pour jamais
	 */
	private String requestMsg(int time) {
		if (time <= -1) return "Place deja prise !";
		if (time == 0) return "Tu as 8sec pour t'asseoir !";
		return "Une personne essay d'avoir la place il lui reste "+((time / 1000) + 1)+"sec !";
	}
	
	/**
	 * Permet de renvoyer le temps de validiter d'une requet
	 * @param req la requet
	 * @return le temps de validity restante de la requet
	 */
	private int validdity(RequestController req) {
		if (req == null) return 0;
		return req.calcValidity();
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
			synchronized (this.obSync) {
				this.firstPlayer = this.manager.createPlayerController(player, (NesController) this.getFirstController());
				this.firstRequest = null;
			}
			this.openController(this.firstPlayer);
		} else {//si c'est le deuxième controlleur controlleur
			synchronized (this.obSync) {
				this.secondPlayer = this.manager.createPlayerController(player, (NesController) this.getSecondController());
				this.secondRequest = null;
			}
			this.openController(this.secondPlayer);
		}
	}
	
	/**
	 * Permet de detruire un PlayerController en envoyant les bon packet au personne qui le voyais
	 * @param isFirst true si c'est le premier controlleur
	 */
	private void closeController(boolean isFirst) {
		PlayerController control = null;
		
		if (isFirst) {
			synchronized (this.obSync) {
				if (this.firstPlayer != null) {
					control = this.firstPlayer;
					this.firstPlayer = null;
				}
			}
		} else {
			synchronized (this.obSync) {
				if (this.secondPlayer != null) {
					control = this.secondPlayer;
					this.secondPlayer = null;
				}
			}
		}
		
		if (control != null)
			this.closeController(control);
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
	 * Permet de récupérer l'objet de syncronisation de la McNes
	 * @return l'objet de syncronisation de la McNes
	 */
	public Object getObSync() {
		return this.obSync;
	}
	
	/**
	 * Permet de récupérer la location de la nes
	 * @return la location de la nes
	 */
	public Location getLocation() {
		return this.location;
	}

	/**
	 * Permet de récupérer le premier controlleur
	 * @return le premier controlleur (peu être null)
	 */
	public PlayerController getFirstPlayer() {
		return this.firstPlayer;
	}

	/**
	 * Permet de récupérer le deuxième controlleur
	 * @return le deuxième controlleur (peu être null)
	 */
	public PlayerController getSecondPlayer() {
		return this.secondPlayer;
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
	 * Permet de savoir si un joueur vois la nes
	 * @param player le joueur que l'on souhaite tester
	 * @return true si le joueur voie deja la nes
	 */
	public abstract boolean canSee(Player player);

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
	
	/**
	 * Permet d'envoyer un packet a tous les joueurs qui peuvent voir la nes
	 */
	public abstract void sendPacket(T packet);

}
