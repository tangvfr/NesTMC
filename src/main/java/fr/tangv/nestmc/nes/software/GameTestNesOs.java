/**
 * 
 */
package fr.tangv.nestmc.nes.software;

import fr.tangv.nestmc.draw.Drawable;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.controller.InputController;

/**
 * @author Tangv - https://tangv.fr
 * Sytème d'exploitation qui permet de tester l'emulation
 */
public class GameTestNesOs extends NesOs {
	
	@Override
	public void update(TMCNes nes, InputController firstIn, InputController secondIn, InputController mixedIn) {
		
	}

	@Override
	public void render(TMCNes nes, Drawable draw) {
		this.setSend(true);
	}

}
