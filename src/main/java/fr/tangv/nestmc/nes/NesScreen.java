/**
 * 
 */
package fr.tangv.nestmc.nes;

import fr.tangv.nestmc.draw.FourMapScreen;
import fr.tangv.nestmc.draw.MapBuffer;

/**
 * @author Tangv - https://tangv.fr
 * Permet de crée un écran de 4 map pour une nes desinable
 */
public abstract class NesScreen extends FourMapScreen {

	/**
	 * Permet de crée l'écran de la nes sur 4 MapBuffer
	 * @param bitScreens les ecrans qui font l'écran de la NES
	 * @param colors palette de couleur pour la nes
	 */
	public NesScreen(MapBuffer[] bitScreens) {
		super(bitScreens);
	}

	/**
	 * Permet de dessiner l'écran de la nes dernièrement calculé sur le desin
	 */
	public abstract void drawNesScreen();
	
}
