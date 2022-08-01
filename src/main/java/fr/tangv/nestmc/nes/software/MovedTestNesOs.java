/**
 * 
 */
package fr.tangv.nestmc.nes.software;

import fr.tangv.nestmc.draw.Drawable;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.controller.InputController;
import fr.tangv.nestmc.palette.v1_8.MapColorV1_8;

/**
 * @author Tangv - https://tangv.fr
 * Sytème d'exploitation avec une image de nes qui bouge pour tester
 */
public class MovedTestNesOs extends NesOs {

	private byte[] img = new byte[] {(byte) 0x21, (byte) 0x21, (byte) 0x21, (byte) 0x21, (byte) 0x21, (byte) 0x24, (byte) 0x21, (byte) 0x21, (byte) 0x21, (byte) 0x21, (byte) 0x21, (byte) 0x21, (byte) 0x21, (byte) 0x21, (byte) 0x21, (byte) 0x21, (byte) 0x21, (byte) 0x21, (byte) 0x21, (byte) 0x21, (byte) 0x21, (byte) 0x21, (byte) 0x21, (byte) 0x21, (byte) 0x21, (byte) 0x21, (byte) 0x24, (byte) 0x55, (byte) 0x55, (byte) 0x55, (byte) 0x55, (byte) 0x55, (byte) 0x55, (byte) 0x21, (byte) 0x21, (byte) 0x21, (byte) 0x21, (byte) 0x21, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x24, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x24, (byte) 0x54, (byte) 0x54, (byte) 0x54, (byte) 0x54, (byte) 0x54, (byte) 0x54, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x24, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x24, (byte) 0x54, (byte) 0x54, (byte) 0x54, (byte) 0x54, (byte) 0x54, (byte) 0x54, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x24, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x21, (byte) 0x21, (byte) 0x21, (byte) 0x21, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x24, (byte) 0x54, (byte) 0x54, (byte) 0x54, (byte) 0x54, (byte) 0x54, (byte) 0x54, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x54, (byte) 0x54, (byte) 0x54, (byte) 0x54, (byte) 0x54, (byte) 0x54, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x54, (byte) 0x54, (byte) 0x54, (byte) 0x54, (byte) 0x54, (byte) 0x54, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x0e, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x00, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x10, (byte) 0x27, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x27, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x00, (byte) 0x00, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x27, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x27, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x24, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x24, (byte) 0x24, (byte) 0x24, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x76, (byte) 0x27, (byte) 0x27, (byte) 0x27, (byte) 0x00, (byte) 0x00};	
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
	public void render(TMCNes nes, Drawable draw) {
		draw.clearScreen(MapColorV1_8.YELLOW_NORMAL);
		//draw image
		draw.setCof((byte) 1);
		draw.drawBuffer(this.x, this.y, this.img, 38, 14);
		//draw texte
		draw.setColor(MapColorV1_8.LAVA_LIGTH);
		draw.setCof((byte) 4);
		draw.drawText(80, 110, "Test !");
		
		this.setSend(true);
	}

}