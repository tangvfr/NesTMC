package fr.tangv.nestmc.nes.software.os.element;

/**
 * @author Tangv - https://tangv.fr
 * Element vide pouvant servir a faire des écartement
 */
public class EmptyElement extends Element {

	/**
	 * Permet de construire un element vide
	 * @param width largueur
	 * @param height hauteur
	 */
	public EmptyElement(int width, int height) {
		super(0, 0, width, height, (byte) 0);
	}

}
