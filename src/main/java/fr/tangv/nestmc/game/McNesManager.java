package fr.tangv.nestmc.game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.tangv.nestmc.game.controller.PlayerController;
import fr.tangv.nestmc.game.controller.RequestController;
import fr.tangv.nestmc.nes.controller.NesController;

/**
 * @author Tangv - https://tangv.fr
 * Permet de géré les différente NES sur le serveur
 * @param <T> le type de packet envoie au joueur
 */
public abstract class McNesManager<T> {

	/**
	 * id de map en partant de la fin des id possible
	 */
	private short nextIdMap;
	/*
	 * liste des consoles
	 */
	private ConcurrentLinkedDeque<McNes<T>> consoles;
	/*
	 * liste des requêtes des joueurs
	 */
	private ConcurrentHashMap<UUID, RequestController> requests;
	/*
	 * configuration dans laquelle est stocker les nes du serveur
	 */
	private YamlConfiguration config;
	/**
	 * distance maximal pour voir une nes
	 */
	private double maxRange;

	/**
	 * Permet de construire 
	 * @param config
	 * @param range
	 */
	public McNesManager(YamlConfiguration config, double maxRange) {//https://minecraft-heads.com/custom-heads/decoration/2001-nes
		this.nextIdMap = Short.MAX_VALUE;
		this.consoles = new ConcurrentLinkedDeque<McNes<T>>();
		this.requests = new ConcurrentHashMap<UUID, RequestController>();
		this.config = config;
		this.maxRange = maxRange;
		//saved consoles is loading
		//his.config.get
	}
	
	public void stop() {
		//consoles is saving
		//save consoles same create and remove
		
	}
	
	/**
	 * Permet de récupéré la prochaine id de map en partant de la fin des id possible
	 * @return l'id de prochaine map
	 */
	public short nextIdMap() {
		short idMap = this.nextIdMap;
		this.nextIdMap--;
		return idMap;
	}
	
	/**
	 * Permet désactiver un joueur qui quite le serveur
	 * @param player le joueur qui quite le serveur
	 */
	public void quit(Player player) {
		//clear request
		this.removeRequest(player);
		//clear nes
		for (McNes<T> nes : this.consoles) {
			if (nes.canSee(player)) {
				nes.hide(player, true);
			}
		}
	}
	
	/**
	 * Permet de mettre a jour les nes que peux voir un joueur
	 * @param player le joueur concerner
	 */
	public void updateAroundConsoles(Player player) {
		Location loc = player.getLocation();
		
		for (McNes<T> nes : this.consoles) {
			boolean have = nes.canSee(player);
			
			if (nes.getLocation().distance(loc) <= this.maxRange) {
				if (!have) {
					nes.show(player);
				}
			} else {
				if (have) {
					nes.hide(player, false);
				}
			}
		}
	}
	
	/**
	 * Permet d'ajouter une console à la liste
	 * @param nes la nes a ajouter
	 */
	public void addNes(McNes<T> nes) {
		this.consoles.add(nes);
	}
	
	/**
	 * Permet suprimer une console de la liste
	 * @param nes la nes a suprimer
	 */
	public boolean removeNes(McNes<T> nes) {
		return this.consoles.remove(nes);
	}
	
	/**
	 * Permet de récupéré une requêt de controlleur dans la liste des requêtes
	 * @param player le joueur qui a fait la requêt
	 * @return la requête du controlleur
	 */
	public RequestController getRequest(Player player) {
		return this.requests.get(player.getUniqueId());
	}
	
	/**
	 * Permet d'ajouter une requête de controlleur dans la liste des requêtes
	 * @param req la requête de controlleur ajouter
	 */
	public void addRequest(RequestController req) {
		this.requests.put(req.getPlayer().getUniqueId(), req);
	}
	
	/**
	 * Permet de retiré une requête de la liste des requêtes
	 * @param player le joueur qui a fait la requêt
	 * @return true si a bien été suprimé
	 */
	public boolean removeRequest(Player player) {
		return this.requests.remove(player.getUniqueId()) != null;
	}
	
	/**
	 * Permet de crée une association entre un joueur et un controlleur de nes
	 * @param player le joueur a associé au controlleur
	 * @param controller lec ontrolleur de nes associé au joueur
	 * @return l'association entre le joueur et le controlleur
	 */
	public abstract PlayerController createPlayerController(Player player, NesController controller);
	
	/**
	 * Permet de crée un flux de map envoyable
	 * @return le flux de map envoyable
	 */
	public abstract PacketMapBuffer<T> createPacketMapBuffer();
	
	/**
	 * Permet de renvoier la palette de couleur de map qui corespond a la palette de couleur de la nes
	 * @return la palette des couleurs pour la nes
	 */
	public abstract byte[] getPalette();
	
}
