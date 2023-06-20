package fr.tangv.nestmc.game;

import fr.tangv.nestmc.draw.FourMapScreen;
import fr.tangv.nestmc.game.controller.PlayerController;
import fr.tangv.nestmc.game.controller.RequestController;
import fr.tangv.nestmc.nes.NesScreen;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.controller.NesController;
import fr.tangv.nestmc.nes.software.NesGui;
import fr.tangv.nestmc.nes.software.NesOs;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.io.File;
import java.security.MessageDigest;
import java.util.List;

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
	/*priopriétaire*/
	private final OfflinePlayer owner;
	
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
	 * @param owner propriétaire de la console
	 * @param os système d'exploitation de la nes
	 */
	public McNes(McNesManager<T> manager, Location loc, OfflinePlayer owner, NesOs os) {
		super(
				new NesGui(
					os,
					new PacketMapBuffer[] {
						manager.createPacketMapBuffer(),
						manager.createPacketMapBuffer(),
						manager.createPacketMapBuffer(),
						manager.createPacketMapBuffer()
						},
					manager.getPalette(),
					manager.getPlugin().getDataFolder().getAbsolutePath() + File.separatorChar + "save" + File.separatorChar + McNes.generateUIDNES(loc) + File.separatorChar
				),
				os
			);
		this.manager = manager;
		this.location = loc;
		this.owner = owner;
	}
	
	/**
	 * Permet de généré une emprinte de la console dans le monde
	 * @param loc location de la nes
	 * @return l'emprinte généré
	 */
	private static String generateUIDNES(Location loc) {
		try {
			return Hex.encodeHexString(MessageDigest.getInstance(MessageDigestAlgorithms.SHA_1).digest(
					(loc.getBlockX() + '.' + loc.getBlockY() + '.' + loc.getBlockZ() + '.' + loc.getWorld().getName())
				.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Permet de suprimé tout les sauvegarde de la nes
	 */
	public void clearSaveFolder() {
		File folder = new File(manager.getPlugin().getDataFolder().getAbsolutePath() + File.separatorChar + "save" + File.separatorChar + McNes.generateUIDNES(this.getLocation()));
		if (folder.exists() && folder.isDirectory()) {
			for (File file : folder.listFiles()) {//pour tout les fichier de sauvegarde dans le dossier de la console
				file.delete();
			}
			folder.delete();
		}
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
		NesScreen screen = this.getScreen();
		
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
					remainingTime = -1;//this.firstPlayer.getPlayer() == player ? -2 : -1;
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
					remainingTime = -1;//this.secondPlayer.getPlayer() == player ? -2 : -1;
				}
			}
		}
		
		//ajout la requets si elle est crée
		if (rcontrol != null) {
			this.manager.addRequest(rcontrol);
		}
		//message
		//if (remainingTime > -2) {
			player.sendMessage((isFirst ? "first" : "second") + ": " + this.requestMsg(remainingTime));
		//}
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
		player.sendMessage((isFirst ? "first" : "second") + ": " + "Timeout sitdown ! (I will write real messsage)");
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
				this.firstPlayer = this.manager.createPlayerController(player, (NesController) this.getFirstController(), isFirst);
				this.firstRequest = null;
			}
			this.openController(this.firstPlayer);
		} else {//si c'est le deuxième controlleur controlleur
			synchronized (this.obSync) {
				this.secondPlayer = this.manager.createPlayerController(player, (NesController) this.getSecondController(), isFirst);
				this.secondRequest = null;
			}
			this.openController(this.secondPlayer);
		}
	}
	
	/**
	 * Permet de detruire un PlayerController en envoyant les bon packet au personne qui le voyais
	 * @param isFirst true si c'est le premier controlleur
	 * @param quit true si la raison de la fermeture est du a une déconnexion
	 */
	private void closeController(boolean isFirst, boolean quit) {
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
		
		if (control != null) {
			control.destruct(quit);
			this.closeController(control);
		}
	}

	@Override
	public void closeController(int controllers) {
		//test de la raison
		boolean quit = (controllers & TMCNes.QUIT) != 0;
		
		//fermeture du premier controlleur
		if ((controllers & TMCNes.FIRST_CONTROLLER) != 0) {
			this.closeController(true, quit);
		}
		
		//fermeture du second controlleur
		if ((controllers & TMCNes.SECOND_CONTROLLER) != 0) {
			this.closeController(false, quit);
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
	 * Permet de récupérer le premier controlleur (pour utiliser sync avec obSync)
	 * @return le premier controlleur (peu être null)
	 */
	public PlayerController getFirstPlayer() {
		return this.firstPlayer;
	}

	/**
	 * Permet de récupérer le deuxième controlleur (pour utiliser sync avec obSync)
	 * @return le deuxième controlleur (peu être null)
	 */
	public PlayerController getSecondPlayer() {
		return this.secondPlayer;
	}
	
	/**
	 * Permet de savoir si une itemframe constitue l'écran
	 * @param idItemFrame identifiant de l'itemframe
	 * @return 1 si l'itemframe constitue l'écran de gauche de la console, 2 pour la droite, 0 si elle ne le constitue pas
	 */
	public abstract int haveIdItemFram(int idItemFrame);
	
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

	/**
	 * Permet d'obtenir le gesitonaire de la console
	 * @return le gesitonaire de la console
	 */
	public McNesManager<T> getManager() {
		return this.manager;
	}

	/**
	 * Permet de récupérer le propriétaire de la NES
	 * @return le propriétaire de la NES
	 */
	public OfflinePlayer getOwner() {
		return this.owner;
	}

	@Override
	public boolean equals(Object ob) {
		if (ob == null || !(ob instanceof McNes)) return false;
		return ((McNes<?>) ob).location.equals(this.location);
	}
	
}
