package fr.tangv.nestmc.game;

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

	public void start() {
		this.nextIdMap = Short.MAX_VALUE;
		
	}
	
	public void stop() {
		
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
	
	public RequestController getRequest(Player player) {
		return null;//return request
	}
	
	public void addRequest(RequestController req) {
		//add in list for listener mc
	}
	
	public void removeRequest(Player player) {
		//remove in list for listener mc
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
