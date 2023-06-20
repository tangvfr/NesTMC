package fr.tangv.nestmc.nes.software.os.element;

import fr.tangv.nestmc.nes.software.os.element.align.Align;
import fr.tangv.nestmc.nes.software.os.element.align.Aligns;
import org.apache.commons.lang.Validate;

/**
 * @author Tangv - https://tangv.fr
 * Element dont sont contenue peux être aligner de plussieur manière
 */
public abstract class AlignedElement extends Element {

	private Align horizontalAlign = Aligns.START;
	private Align verticalAlign = Aligns.START;
	
	/**
	 * Permet de construire un element de base pouvant être aligner qui par default est en start
	 * @param x décalage horizontal
	 * @param y décalage vertical
	 * @param width largeur
	 * @param height hauteur
	 * @param background coleur de fond
	 */
	public AlignedElement(int x, int y, int width, int height, byte background) {
		super(x, y, width, height, background);
	}
	
	/**
	 * Permet de récupérer l'alignement horizontal du text
	 * @return l'alignement horizontal du text
	 */
	public Align getHorizontalAlign() {
		return this.horizontalAlign;
	}

	/**
	 * Permet de modifier l'alignement horizontal du text
	 * @param align le nouvelle alignement horizontal du text
	 */
	public void setHorizontalAlign(Align align) {
		Validate.notNull(align, "Align can't null");
		this.horizontalAlign = align;
		this.updateSizeAndPosition();
	}
	
	/**
	 * Permet de récupérer l'alignement verticale du text
	 * @return l'alignement verticale du text
	 */
	public Align getVerticalAlign() {
		return this.verticalAlign;
	}

	/**
	 * Permet de modifier l'alignement verticale du text
	 * @param align le nouvelle alignement verticale  du text
	 */
	public void setVerticalAlign(Align align) {
		Validate.notNull(align, "Align can't null");
		this.verticalAlign = align;
		this.updateSizeAndPosition();
	}
	
}
