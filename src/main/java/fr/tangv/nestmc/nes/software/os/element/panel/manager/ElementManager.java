package fr.tangv.nestmc.nes.software.os.element.panel.manager;

import java.util.List;

import fr.tangv.nestmc.nes.software.os.element.Element;
import fr.tangv.nestmc.nes.software.os.element.panel.ParamPanelElement;

/**
 * @author Tangv - https://tangv.fr
 * Permet d'aligner des élements
 */
public abstract class ElementManager {

	/**
	 * Permet de definir la taille et la posistion d'elements par rapport a un contenneur
	 * @param elements elements à aligner
	 * @param panel contenneur
	 */
	public abstract void align(List<ParamPanelElement> elements, Element container);
	
}
