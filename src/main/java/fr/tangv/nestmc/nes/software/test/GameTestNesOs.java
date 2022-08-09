/**
 * 
 */
package fr.tangv.nestmc.nes.software.test;

import com.grapeshot.halfnes.mappers.BadMapperException;

import fr.tangv.nestmc.nes.NesRom;
import fr.tangv.nestmc.nes.NesScreen;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.controller.InputController;
import fr.tangv.nestmc.nes.software.NesOs;
import fr.tangv.nestmc.palette.v1_8.MapColorV1_8;

/**
 * @author Tangv - https://tangv.fr
 * Sytème d'exploitation qui permet de tester l'emulation
 */
public class GameTestNesOs extends NesOs {
	
	private String[] input = new String[17];
	
	@Override
	public void update(TMCNes nes, InputController firstIn, InputController secondIn, InputController mixedIn) {
		input[0] = "A:" + (mixedIn.isPress(InputController.ATTACK) ? "V" : "O");
		input[1] = "D:" + (mixedIn.isPress(InputController.DOWN) ? "V" : "O");
		input[2] = "H1:" + (mixedIn.isPress(InputController.HEALD_1) ? "V" : "O");
		input[3] = "H2:" + (mixedIn.isPress(InputController.HEALD_2) ? "V" : "O");
		input[4] = "H3:" + (mixedIn.isPress(InputController.HEALD_3) ? "V" : "O");
		input[5] = "H4:" + (mixedIn.isPress(InputController.HEALD_4) ? "V" : "O");
		input[6] = "H5:" + (mixedIn.isPress(InputController.HEALD_5) ? "V" : "O");
		input[7] = "H6:" + (mixedIn.isPress(InputController.HEALD_6) ? "V" : "O");
		input[8] = "H7:" + (mixedIn.isPress(InputController.HEALD_7) ? "V" : "O");
		input[9] = "H8:" + (mixedIn.isPress(InputController.HEALD_8) ? "V" : "O");
		input[10] = "I:" + (mixedIn.isPress(InputController.INTERACT) ? "V" : "O");
		input[11] = "L:" + (mixedIn.isPress(InputController.LEFT) ? "V" : "O");
		input[12] = "O:" + (mixedIn.isPress(InputController.OPEN_INV) ? "V" : "O");
		input[13] = "R:" + (mixedIn.isPress(InputController.RIGHT) ? "V" : "O");
		input[14] = "SN:" + (mixedIn.isPress(InputController.SNEAK) ? "V" : "O");
		input[15] = "SP:" + (mixedIn.isPress(InputController.SPACE) ? "V" : "O");
		input[16] = "U:" + (mixedIn.isPress(InputController.UP) ? "V" : "O");
		
		if (firstIn.isPress(InputController.SNEAK + InputController.OPEN_INV + InputController.SPACE)) {
			System.out.println("force exit nes 1 !");
			nes.closeController(TMCNes.FIRST_CONTROLLER);
		} if (secondIn.isPress(InputController.SNEAK + InputController.OPEN_INV + InputController.SPACE)) {
			System.out.println("force exit nes 2 !");
			nes.closeController(TMCNes.SECOND_CONTROLLER);
		} else if (mixedIn.isClicked(InputController.HEALD_8)) {
			System.out.println("start");
			try {
				nes.start(new NesRom(System.getenv("testrom")));
			} catch (BadMapperException e) {
				e.printStackTrace();
			}
		} else if (mixedIn.isClicked(InputController.HEALD_7)) {
			System.out.println("stop");
			nes.stop();
		}
	}

	@Override
	public void render(TMCNes nes, NesScreen screen) {
		//nes
		if (nes.isStart()) {
			screen.drawNesScreen();
			screen.setColor(MapColorV1_8.GREEN_LIGTH);
		} else {
			screen.setColor(MapColorV1_8.RED_LIGTH);
		}
		screen.fillRect(4, 244, 8, 8);
		//overlay
		screen.setColor(MapColorV1_8.SNOW_LIGTH);
		screen.fillRect(0, 0, 48, 160);
		screen.setColor(MapColorV1_8.EMERALD_SHADOW);
		int y = 16;
		for (String str : this.input) {
			screen.drawText(16, y, str);
			y += screen.getHeightText();
		}
		this.setSend(true);
	}

	@Override
	public void addMessageBox(String msg) {
		System.out.println("msgbox: "+msg);
	}

}
