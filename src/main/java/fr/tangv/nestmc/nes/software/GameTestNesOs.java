/**
 * 
 */
package fr.tangv.nestmc.nes.software;

import java.util.ArrayList;

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
	
	private String[] input = new String[17];
	
	@Override
	public void update(TMCNes nes, InputController firstIn, InputController secondIn, InputController mixedIn) {
		input[0] = "A:" + (mixedIn.isPress(InputController.ATTACK) ? "•" : "○");
		input[1] = "D:" + (mixedIn.isPress(InputController.DOWN) ? "•" : "○");
		input[2] = "H1:" + (mixedIn.isPress(InputController.HEALD_1) ? "•" : "○");
		input[3] = "H2:" + (mixedIn.isPress(InputController.HEALD_2) ? "•" : "○");
		input[4] = "H3:" + (mixedIn.isPress(InputController.HEALD_3) ? "•" : "○");
		input[5] = "H4:" + (mixedIn.isPress(InputController.HEALD_4) ? "•" : "○");
		input[6] = "H5:" + (mixedIn.isPress(InputController.HEALD_5) ? "•" : "○");
		input[7] = "H6:" + (mixedIn.isPress(InputController.HEALD_6) ? "•" : "○");
		input[8] = "H7:" + (mixedIn.isPress(InputController.HEALD_7) ? "•" : "○");
		input[9] = "H8:" + (mixedIn.isPress(InputController.HEALD_8) ? "•" : "○");
		input[10] = "I:" + (mixedIn.isPress(InputController.INTERACT) ? "•" : "○");
		input[11] = "L:" + (mixedIn.isPress(InputController.LEFT) ? "•" : "○");
		input[12] = "O:" + (mixedIn.isPress(InputController.OPEN_INV) ? "•" : "○");
		input[13] = "R:" + (mixedIn.isPress(InputController.RIGHT) ? "•" : "○");
		input[14] = "SN:" + (mixedIn.isPress(InputController.SNEAK) ? "•" : "○");
		input[15] = "SP:" + (mixedIn.isPress(InputController.SPACE) ? "•" : "○");
		input[16] = "U:" + (mixedIn.isPress(InputController.UP) ? "•" : "○");
		
		if (firstIn.isPress(InputController.DOWN + InputController.SNEAK + InputController.SPACE + InputController.HEALD_1)) {
			System.out.println("force exit nes !");
			nes.closeController(TMCNes.FIRST_CONTROLLER);
		} else if (mixedIn.isClicked(InputController.HEALD_8)) {
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
