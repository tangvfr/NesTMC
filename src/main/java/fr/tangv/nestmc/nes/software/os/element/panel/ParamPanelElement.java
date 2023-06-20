package fr.tangv.nestmc.nes.software.os.element.panel;

import fr.tangv.nestmc.nes.software.os.element.Element;
import org.apache.commons.lang.Validate;

/**
 * @author Tangv - https://tangv.fr
 * Represente un element dans un panel avec son paramètre
 */
public class ParamPanelElement {

	private final Element element;
	private final int param;
	
	/**
	 * Permet de regrouper un element et son paramètre d'alignement
	 * @param ele l'element
	 * @param param paramètre d'alignement
	 */
	public ParamPanelElement(Element element, int param) {
		Validate.notNull(element, "Element can't null !");
		this.element = element;
		this.param = param;
	}

	/**
	 * Permet de récupérer l'element
	 * @return l'element
	 */
	public Element getElement() {
		return this.element;
	}

	/**
	 * Permet de récupérer le paramètre d'alignement
	 * @return le paramètre d'alignement
	 */
	public int getParam() {
		return this.param;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean eq = false;
		
		if (obj != null) {
			if (obj instanceof Element) {
				eq = ((Element) obj).equals(element);
			} else if (obj instanceof ParamPanelElement) {
				eq = ((ParamPanelElement) obj).element.equals(element);
			}
		}
		
		return eq;
	}
	
}
