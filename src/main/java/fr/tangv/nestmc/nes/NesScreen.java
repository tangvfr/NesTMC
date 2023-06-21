/**
 * 
 */
package fr.tangv.nestmc.nes;

import fr.tangv.nestmc.draw.FourMapScreen;
import fr.tangv.nestmc.draw.MapBuffer;

/**
 * @author Tangv - https://tangv.fr
 * Permet de créer un écran de 4 map pour une nes desinable
 */
public abstract class NesScreen extends FourMapScreen {

	public final static int WIDTH = 256;
	public final static int NES_HEIGHT = 240;
	public final static int SCREEN_HEIGHT = 256;

	/**
	 * Permet de crée l'écran de la nes sur 4 MapBuffer
	 * @param bitScreens les ecrans qui font l'écran de la NES
	 */
	public NesScreen(MapBuffer[] bitScreens) {
		super(bitScreens);
	}

	/**
	 * Permet de dessiner l'écran de la nes dernièrement calculé sur le desin
	 */
	public abstract void drawNesScreen();
	
}
