package fr.tangv.nestmc.nes.software.os.element.panel.manager;

import fr.tangv.nestmc.nes.software.os.element.Element;
import fr.tangv.nestmc.nes.software.os.element.panel.ParamPanelElement;

import java.util.List;

/**
 * @author Tangv - https://tangv.fr
 * Permet d'aligner des élements
 */
public interface ElementManager {

	/**
	 * Permet de definir la taille et la posistion d'elements par rapport a un contenneur
	 * @param elements elements à aligner
	 * @param panel contenneur
	 */
	public void align(List<ParamPanelElement> elements, Element container);
	
}
