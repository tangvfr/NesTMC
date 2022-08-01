/**
 * 
 */
package fr.tangv.nestmc.nes.software;

import com.grapeshot.halfnes.mappers.BadMapperException;

import fr.tangv.nestmc.draw.Drawable;
import fr.tangv.nestmc.nes.NesRom;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.controller.InputController;

/**
 * @author Tangv - https://tangv.fr
 * Sytème d'exploitation qui permet de tester l'emulation
 */
public class GameTestNesOs extends NesOs {
	
	@Override
	public void update(TMCNes nes, InputController firstIn, InputController secondIn, InputController mixedIn) {
		if (mixedIn.isClicked(InputController.HEALD_8)) {
			try {
				nes.start(new NesRom(System.getenv("testrom")));
			} catch (BadMapperException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void render(TMCNes nes, Drawable draw) {
		this.setSend(true);
	}

	@Override
	public void addMessageBox(String msg) {
		System.out.println("msgbox: "+msg);
	}

}
