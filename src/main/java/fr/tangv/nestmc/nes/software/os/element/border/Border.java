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

	/**
	 * Permet de calculer les pixels que la bodure prend sur l'axe X
	 * @return la taille que sa prend sur l'axe X
	 */
	public abstract int calcXLength();
	
	/**
	 * Permet de calculer les pixels que la bodure prend sur l'axe Y
	 * @return la taille que sa prend sur l'axe Y
	 */
	public abstract int calcYLength();
	
	/**
	 * Permet de récupérer l'épaisseur du bord du haut de l'element
	 * @return l'épaisseur du bord du haut de l'element
	 */
	public abstract int getTopBorder();

	/**
	 * Permet de récupérer l'épaisseur du bord du bas de l'element
	 * @return l'épaisseur du bord du bas de l'element
	 */
	public abstract int getBottomBorder();

	/**
	 * Permet de récupérer l'épaisseur du bord gauche de l'element
	 * @return l'épaisseur du bord gauche de l'element
	 */
	public abstract int getLeftBorder();

	/**
	 * Permet de récupérer l'épaisseur du bord droit de l'element
	 * @return l'épaisseur du bord droit de l'element
	 */
	public abstract int getRightBorder();
	
}
