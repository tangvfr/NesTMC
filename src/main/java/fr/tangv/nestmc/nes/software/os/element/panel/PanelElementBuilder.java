package fr.tangv.nestmc.nes.software.os.element.panel;

import fr.tangv.nestmc.nes.software.os.element.Element;
import fr.tangv.nestmc.nes.software.os.element.EmptyElement;
import fr.tangv.nestmc.nes.software.os.element.border.Border;
import fr.tangv.nestmc.nes.software.os.element.panel.manager.FullerElementManager;

/**
 * @author Tangv - https://tangv.fr
 * Permet d'assembler et d'agliner deux element dans un panel
 */
public abstract class PanelElementBuilder {

	/**
	 * Permet de crée un encadrement pour un element
	 * @param ele element a encadré
	 * @param background couleur du fond du cadre
	 * @param margin marge du cadre
	 * @param border bordure du cadre
	 * @param padding marge interieur du cardre
	 * @return le cardre
	 */
	public static Element createWindowElement(Element ele, byte background, int margin, Border border, int padding) {
		return PanelElementBuilder.createWindowElement(ele, background, margin, margin, border, padding, padding);
	}
	
	/**
	 * Permet de crée un assemblage de deux element avec le plus petit a gauche
	 * @param min element a gauche en petit
	 * @param max element a droite en gros
	 * @param background fond de l'assemblage
	 * @param space espace entre les deux element
	 * @param margin marge du cadre des englobent les elements
	 * @param border bordure du cadre des englobent les elements
	 * @param padding marge interieur du cadre des englobent les elements
	 * @return l'element contenent l'asemblage des deux element
	 */
	public static Element createLeft(Element min, Element max, byte background, int space, int margin, Border border, int padding) {
		return PanelElementBuilder.createLeft(min, max, background, space, margin, margin, border, padding, padding);
	}
	
	/**
	 * Permet de crée un assemblage de deux element avec le plus petit a droite
	 * @param max element a gauche en gros
	 * @param min element a droite en petit
	 * @param background fond de l'assemblage
	 * @param space espace entre les deux element
	 * @param margin marge du cadre des englobent les elements
	 * @param border bordure du cadre des englobent les elements
	 * @param padding marge interieur du cadre des englobent les elements
	 * @return l'element contenent l'asemblage des deux element
	 */
	public static Element createRight(Element max, Element min, byte background, int space, int margin, Border border, int padding) {
		return PanelElementBuilder.createRight(max, min, background, space, margin, margin, border, padding, padding);
	}
	
	/**
	 * Permet de crée un assemblage de deux element avec le plus petit a gauche
	 * @param min element a gauche en petit
	 * @param max element a droite en gros
	 * @param background fond de l'assemblage
	 * @param space espace entre les deux element
	 * @param marginX marge horizontal du cadre des englobent les elements
	 * @param marginY marge vertical du cadre des englobent les elements
	 * @param border bordure du cadre des englobent les elements
	 * @param paddingX marge interieur horizontal du cadre des englobent les elements
	 * @param paddingY marge interieur vertical du cadre des englobent les elements
	 * @return l'element contenent l'asemblage des deux element
	 */
	public static Element createLeft(Element min, Element max, byte background, int space, int marginX, int marginY, Border border, int paddingX, int paddingY) {
		byte back = (paddingX > 0 || paddingY > 0) ? 0 : background;
		PanelElement pan = new PanelElement(0, 0, 256, 256, back, new FullerElementManager(FullerElementManager.HORIZONTAL));
		pan.addElement(min, FullerElementManager.MIN_SIZE);
		if (space > 0) {
			pan.addElement(new EmptyElement(space, 0), FullerElementManager.MIN_SIZE);
		}
		pan.addElement(max, FullerElementManager.MAX_SIZE);
		return PanelElementBuilder.createWindowElement(pan, background, marginX, marginY, border, paddingX, paddingY);
	}
	
	/**
	 * Permet de crée un assemblage de deux element avec le plus petit a droite
	 * @param max element a gauche en gros
	 * @param min element a droite en petit
	 * @param background fond de l'assemblage
	 * @param space espace entre les deux element
	 * @param marginX marge horizontal du cadre des englobent les elements
	 * @param marginY marge vertical du cadre des englobent les elements
	 * @param border bordure du cadre des englobent les elements
	 * @param paddingX marge interieur horizontal du cadre des englobent les elements
	 * @param paddingY marge interieur vertical du cadre des englobent les elements
	 * @return l'element contenent l'asemblage des deux element
	 */
	public static Element createRight(Element max, Element min, byte background, int space, int marginX, int marginY, Border border, int paddingX, int paddingY) {
		byte back = (paddingX > 0 || paddingY > 0) ? 0 : background;
		PanelElement pan = new PanelElement(0, 0, 256, 256, back, new FullerElementManager(FullerElementManager.HORIZONTAL));
		pan.addElement(max, FullerElementManager.MAX_SIZE);
		if (space > 0) {
			pan.addElement(new EmptyElement(space, 0), FullerElementManager.MIN_SIZE);
		}
		pan.addElement(min, FullerElementManager.MIN_SIZE);
		return PanelElementBuilder.createWindowElement(pan, background, marginX, marginY, border, paddingX, paddingY);
	}
	
	/**
	 * Permet de crée un encadrement pour un element
	 * @param ele element a encadré
	 * @param background couleur du fond du cadre
	 * @param marginX marge horizontal du cadre
	 * @param marginY marge vertical du cadre
	 * @param border bordure du cadre
	 * @param paddingX marge interieur horizontal du cardre
	 * @param paddingY marge interieur vertical du cardre
	 * @return le cardre
	 */
	public static Element createWindowElement(Element ele, byte background, int marginX, int marginY, Border border, int paddingX, int paddingY) {
		//padding
		if (paddingX > 0 || paddingY > 0) {
			ele = new MarginElement(ele, paddingX, paddingY);
			ele.setBackground(background);
		}
		//border
		if (border != null) {
			ele.setBorder(border);
		}
		//margin
		if (marginX > 0 || marginY > 0) {
			ele = new MarginElement(ele, marginX, marginY);
		}
		return ele;
	}
	
}
