package fr.tangv.nestmc.nes.software.os.element.border;

import fr.tangv.nestmc.nes.NesScreen;
import fr.tangv.nestmc.nes.software.os.element.Element;

/**
 * @author Tangv - https://tangv.fr
 * Bordure vide
 */
public class EmptyBorder extends Border {

	protected int topBorder;
	protected int bottomBorder;
	protected int leftBorder;
	protected int rightBorder;
	
	/**
	 * Permet de construire une bordure vide
	 * @param topBorder épaisseur du bord du haut
	 * @param bottomBorder épaisseur du bord du bas
	 * @param leftBorder épaisseur du bord de gauche
	 * @param rightBorder épaisseur du bord de droite
	 */
	public EmptyBorder(int topBorder, int bottomBorder, int leftBorder, int rightBorder) {
		this.topBorder = topBorder;
		this.bottomBorder = bottomBorder;
		this.leftBorder = leftBorder;
		this.rightBorder = rightBorder;
	}
	
	/**
	 * Permet de construire une bordure vide
	 * @param border épaisseur de tous les bords
	 */
	public EmptyBorder(int border) {
		this.topBorder = border;
		this.bottomBorder = border;
		this.leftBorder = border;
		this.rightBorder = border;
	}
	
	/**
	 * Permet de construire une bordure vide
	 * @param borderX épaisseur de tous les bords horizontaux
	 * @param borderY épaisseur de tous les bords verticaux
	 */
	public EmptyBorder(int borderX, int borderY) {
		this.topBorder = borderY;
		this.bottomBorder = borderY;
		this.leftBorder = borderX;
		this.rightBorder = borderX;
	}

	@Override
	public int getTopBorder() {
		return this.topBorder;
	}

	/**
	 * Permet de modifier l'épaisseur du bord du haut de l'element
	 * @param topBorder la nouvelle épaisseur du haut du bas de l'element
	 */
	public void setTopBorder(int topBorder) {
		this.topBorder = topBorder;
	}


	@Override
	public int getBottomBorder() {
		return this.bottomBorder;
	}

	/**
	 * Permet de modifier l'épaisseur du bord du bas de l'element
	 * @param bottomBorder la nouvelle épaisseur du bord du bas de l'element
	 */
	public void setBottomBorder(int bottomBorder) {
		this.bottomBorder = bottomBorder;
	}


	@Override
	public int getLeftBorder() {
		return this.leftBorder;
	}

	/**
	 * Permet de modifier l'épaisseur du bord gauche de l'element
	 * @param leftBorder la nouvelle épaisseur du bord gauche de l'element
	 */
	public void setLeftBorder(int leftBorder) {
		this.leftBorder = leftBorder;
	}


	@Override
	public int getRightBorder() {
		return this.rightBorder;
	}

	/**
	 * Permet de modifier l'épaisseur du bord droit de l'element
	 * @param rightBorder la nouvelle épaisseur du bord droit de l'element
	 */
	public void setRightBorder(int rightBorder) {
		this.rightBorder = rightBorder;
	}

	/**
	 * Permet de modifier les bords de l'element
	 * @param border la nouvelle épaisseur des bords l'element
	 */
	public void setBorder(int border) {
		this.topBorder = border;
		this.bottomBorder = border;
		this.leftBorder = border;
		this.rightBorder = border;
	}
	
	/**
	 * Permet de modifier l'épaisseur des bords de l'element
	 * @param topBorder la nouvelle épaisseur du haut du bas de l'element
	 * @param bottomBorder la nouvelle épaisseur du bord du bas de l'element
	 * @param leftBorder la nouvelle épaisseur du bord gauche de l'element
	 * @param rightBorder la nouvelle épaisseur du bord droit de l'element
	 */
	public void setBorder(int topBorder, int bottomBorder, int leftBorder, int rightBorder) {
		this.topBorder = topBorder;
		this.bottomBorder = bottomBorder;
		this.leftBorder = leftBorder;
		this.rightBorder = rightBorder ;
	}
	
	/**
	 * Permet de modifier les bords de l'element
	 * @param borderX la nouvelle épaisseur des bords horizontaux de l'element
	 * @param borderY la nouvelle épaisseur des bords verticaux de l'element
	 */
	public void setBorder(int xBorder, int yBorder) {
		this.topBorder = yBorder;
		this.bottomBorder = yBorder;
		this.leftBorder = xBorder;
		this.rightBorder = xBorder;
	}
	
	@Override
	public int calcXLength() {
		return this.leftBorder + this.rightBorder;
	}
	
	@Override
	public int calcYLength() {
		return this.topBorder + this.bottomBorder;
	}

	@Override
	public void render(Element ele, NesScreen screen) {}

}
