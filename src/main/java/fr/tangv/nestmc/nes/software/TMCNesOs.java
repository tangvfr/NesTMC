/**
 * 
 */
package fr.tangv.nestmc.nes.software;

import fr.tangv.nestmc.draw.Drawable;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.controller.InputController;

/**
 * @author Tangv - https://tangv.fr
 * Sytème d'exploitation des nes pour permet au joueur d'intergir avec la console
 */
public class TMCNesOs extends NesOs {
	
	@Override
	public void addMessageBox(String msg) {
		System.out.println("msgbox: "+msg);
	}
	
	@Override
	public void update(TMCNes nes, InputController firstIn, InputController secondIn, InputController mixedIn) {
		
	}

	@Override
	public void render(TMCNes nes, Drawable draw) {
		
	}

}
