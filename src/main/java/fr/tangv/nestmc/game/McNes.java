package fr.tangv.nestmc.game;

import fr.tangv.nestmc.game.controller.RequestController;
import fr.tangv.nestmc.nes.NesScreenMap;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.software.NesGui;

public class McNes extends TMCNes {

	private RequestController firstRequest = null;
	private RequestController secondRequest = null;
	private RequestController firstRequest = null;
	private RequestController secondRequest = null;
	
	public McNes(McNesManager ) {
		super(new NesGui(null));
		// TODO Auto-generated constructor stub
	}
	
	//udp input
	//udp menu
	//draw  //anvant de draw ou envoie les pquet de l'ecran penser a synchronized
	//action 
	//send
	
}
