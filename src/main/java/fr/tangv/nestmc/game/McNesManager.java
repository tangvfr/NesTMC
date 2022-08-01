package fr.tangv.nestmc.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.scheduler.BukkitRunnable;

import fr.tangv.nestmc.NesTMC;
import fr.tangv.nestmc.game.controller.PlayerController;
import fr.tangv.nestmc.game.controller.RequestController;
import fr.tangv.nestmc.nes.controller.NesController;
import fr.tangv.nestmc.nes.software.NesOs;

/**
 * @author Tangv - https://tangv.fr
 * Permet de géré les différente NES sur le serveur
 * @param <T> le type de packet envoie au joueur
 */
public abstract class McNesManager<T> extends BukkitRunnable {

	/**
	 * id de map en partant de la fin des id possible
	 */
	private short nextIdMap = Short.MAX_VALUE;
	/*
	 * liste des consoles
	 */
	private final ConcurrentLinkedQueue<McNes<T>> consoles = new ConcurrentLinkedQueue<McNes<T>>();
	/*
	 * liste des requêtes des joueurs
	 */
	private final ConcurrentHashMap<UUID, RequestController> requests = new ConcurrentHashMap<UUID, RequestController>();
	/*
	 * configuration dans laquelle est stocker les nes du serveur
	 */
	private final YamlConfiguration config;
	/**
	 * distance maximal pour voir une nes
	 */
	private final double maxRange;
	/**
	 * nombre maximal de nes autoriser sur le serveur
	 */
	private final int maxNes;
	/*
	 * gestionnaire d'event
	 */
	private final ListenerMcNes listener = new ListenerMcNes(this);
	/*
	 * le plugin auquel appartient le gestionnaire
	 */
	private final NesTMC plugin;

	/**
	 * Permet de construire un gestionnaire de consoles
	 * @param plugin le plugin du gestionaire
	 * @param config la configuration et les messages des console
	 */
	public McNesManager(NesTMC plugin, YamlConfiguration config) {//https://minecraft-heads.com/custom-heads/decoration/2001-nes
		this.plugin = plugin;
		this.config = config;
		this.maxRange = 16D;//replamce here
		this.maxNes = 2;//replamce here
		//saved consoles is loading
		//his.config.get
		
		//init
		Bukkit.getPluginManager().registerEvents(this.listener, plugin);
		this.runTaskTimerAsynchronously(plugin, 0, 1);
	}
	
	@Override
	public void run() {
		this.update();
	}
	
	/**
	 * Permet de mettre à jour toutes les consoles du serveur
	 */
	public void update() {
		Iterator<McNes<T>> it = this.consoles.iterator();
		while (it.hasNext()) {//parcour tout les consoles du serveur
			it.next().update();
		}
	}
	
	/**
	 * Permet d'arrêter toutes les consoles du serveur et les actions qui leurs sont liées
	 */
	public void stop() {
		this.cancel();
		HandlerList.unregisterAll(this.listener);
		//this.requests.clear();//pas besoin theoriquement
		
		//clear nes
		Iterator<McNes<T>> it = this.consoles.iterator();
		while (it.hasNext()) {//parcour tout les consoles du serveur
			it.next().destruct();
			//this.consoles.remove(nes);////pas besoin theoriquement
		}
		
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
	 * Permet de récupé la console a qui appartiens l'itemframe
	 * @param idItemFrame identifiant de l'itemframe
	 * @return la console a qui appartiens l'itemframe qui consistitue son écran
	 */
	public McNes<T> getNes(int idItemFrame) {
		for (McNes<T> nes : this.consoles) {
			if (nes.haveIdItemFram(idItemFrame)) {
				return nes;
			}
		}
		return null;
	}
	
	/**
	 * Permet de crée et d'ajouter à la liste des consoles une nouvelle NES
	 * @param nes la nes a ajouter, null si le nombre de nes maximal est atteint
	 */
	public McNes<T> createNes(Location loc) {
		McNes<T> nes = null;
		if (this.consoles.size() < this.maxNes) {
			nes = this.newConsole(loc);
			Validate.notNull(nes, "Nes can't create ! (Created Nes is NULL)");
			
			//joueur qui peuvent voir la nes
			ArrayList<Player> players = new ArrayList<Player>();
			for (Player player : Bukkit.getOnlinePlayers()) {//pour tout les joueur connecter
				if (player.getLocation().distance(loc) <= this.maxRange) {//si a moins de maxRange block de la nes
					players.add(player);
				}
			}
			
			//add consoles
			nes.create(players);
			this.consoles.add(nes);
		}
		return nes;
	}
	
	/**
	 * Permet de detruire et de retirer de la liste des consoles une NES
	 * @param nes la nes a detruire et retirer 
	 */
	public boolean removeNes(McNes<T> nes) {
		boolean rm = this.consoles.remove(nes);
		if (rm) {
			nes.destruct();
		}
		return rm;
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
	
	/*
	 * Permet d'instancier une nouvelle console nes
	 * @return l'intance de la nouvelle console
	 */
	protected abstract McNes<T> newConsole(Location loc);

	/**
	 * Permet de crée une instance de système d'explotation pour une console
	 * @return le système d'explotation
	 */
	public abstract NesOs createNesOs();
	
	/**
	 * Est apeller lorsqu'un joueur se connecte
	 */
	public abstract void playerJoin(Player player);

	/**
	 * Est apeller pour tous les joueur lorsque le manager est stopper
	 */
	public abstract void playerReload(Player player);
	
	/**
	 * Est apeller lorsqu'un joueur se déconnecte
	 */
	public abstract void playerQuit(Player player);

	/**
	 * Permet de récupérer le plugin du gestionnaire
	 * @return le plugin du gestionnaire
	 */
	public NesTMC getPlugin() {
		return this.plugin;
	}
	
}
