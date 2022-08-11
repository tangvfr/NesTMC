package fr.tangv.nestmc.nes.software.os.element.border;

import fr.tangv.nestmc.nes.NesScreen;
import fr.tangv.nestmc.nes.software.os.element.Element;

/**
 * @author Tangv - https://tangv.fr
 * Permet d'appliquer a un element une bordure
 */
public abstract class Border {

	/**
	 * Permet de dessiner la bordure
	 * @param screen l'écran sur lequelle est dessiner la bordure
	 */
	public abstract void render(Element ele, NesScreen screen);

}
