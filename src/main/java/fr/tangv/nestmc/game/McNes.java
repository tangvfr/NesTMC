package fr.tangv.nestmc.game;

import org.bukkit.block.Block;

import fr.tangv.nestmc.game.controller.PlayerController;
import fr.tangv.nestmc.game.controller.RequestController;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.software.NesGui;

/**
 * @author Tangv - https://tangv.fr
 * une nes dans minecraft
 */
public abstract class McNes<T> extends TMCNes {

	private Block[] blocks;
	private RequestController firstRequest = null;
	private RequestController secondRequest = null;
	private PlayerController firstPlayer = null;
	private PlayerController secondPlayer = null;
	
	public McNes(PacketMapBuffer[] maps, Block[] blocks) {
		super(new NesGui(maps));
		
	}
	
	@Override
	public void update() {
		//udp input
		//udp menu
		//draw  //anvant de draw ou envoie les pquet de l'ecran penser a synchronized
		//action 
		//send
	}
	
	/**
	 * Permet d'obtenir les 4 packet nms pour mettre a jour les map qui forme l'écran de la nes
	 * @return les 4 packet nms pour mettre a jour les map
	 */
	public abstract T[] getPackets();

	@Override
	public void closeController(int controllers) {
		if ((controllers & TMCNes.FIRST_CONTROLLER) != 0) {
			this.firstPlayer.destruct(false);
			/*hide*/
			this.firstPlayer = null;
		}
		if ((controllers & TMCNes.SECOND_CONTROLLER) != 0) {
			this.secondPlayer.destruct(false);
			/*hide*/
			this.secondPlayer = null;
		}
	}
	
}
