/**
 * 
 */
package fr.tangv.nestmc.nes.software.os.test;

import fr.tangv.nestmc.nes.NesScreen;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.controller.InputController;
import fr.tangv.nestmc.nes.software.NesOs;
import fr.tangv.nestmc.nes.software.img.TMCImageOs;
import fr.tangv.nestmc.palette.v1_8.MapColorV1_8;

/**
 * @author Tangv - https://tangv.fr
 * Sytème d'exploitation avec une image de nes qui bouge pour tester
 */
public class MovedTestNesOs extends NesOs {

	private int x = (int) (Math.random() * 63) * 4;
	private int y = (int) (Math.random() * 63) * 4;
	private int dx = 2;
	private int dy = 2;
	private int borderX = 256 - 38 - 1;
	private int borderY = 256 - 14 - 1;
	
	@Override
	public void update(TMCNes nes, InputController firstIn, InputController secondIn, InputController mixedIn) {
		//x
		x += dx;
		if (x <= 0 || x >= borderX) {
			dx *= -1;
		}
		y += dy;
		if (y <= 0 || y >= borderY) {
			dy *= -1;
		}
	}

	@Override
	public void render(TMCNes nes, NesScreen screen) {
		screen.clearScreen(MapColorV1_8.YELLOW_NORMAL);
		//draw image
		screen.setCof((byte) 1);
		screen.drawBuffer(this.x, this.y, TMCImageOs.NES_CONSOLE);
		//draw texte
		screen.setColor(MapColorV1_8.LAVA_LIGTH);
		screen.setCof((byte) 4);
		screen.drawText(80, 110, "Test !");
		
		this.setSend(true);
	}

	@Override
	public void addMessageBox(String msg) {
		//never
	}

}
