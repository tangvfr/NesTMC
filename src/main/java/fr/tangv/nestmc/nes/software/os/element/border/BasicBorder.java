package fr.tangv.nestmc.nes.software.os.element.border;

import fr.tangv.nestmc.nes.NesScreen;
import fr.tangv.nestmc.nes.software.os.element.Element;

/**
 * @author Tangv - https://tangv.fr
 * Bordure simple pour un Element
 */
public class BasicBorder extends Border {

	private int topBorder;
	private int bottomBorder;
	private int leftBorder;
	private int rightBorder;
	private byte border;
	
	/**
	 * Permet de construire un element de base avec des bord
	 * @param x décalage horizontal
	 * @param y décalage vertical
	 * @param width largeur
	 * @param height hauteur
	 * @param background coleur de fond
	 * @param topBorder épaisseur du bord du haut
	 * @param bottomBorder épaisseur du bord du bas
	 * @param leftBorder épaisseur du bord de gauche
	 * @param rightBorder épaisseur du bord de droite
	 * @param borderColor couleur des bords
	 */
	public BasicBorder(int topBorder, int bottomBorder, int leftBorder, int rightBorder, byte borderColor) {
		this.topBorder = topBorder;
		this.bottomBorder = bottomBorder;
		this.leftBorder = leftBorder;
		this.rightBorder = rightBorder;
		this.border = borderColor;
	}
	
	/**
	 * Permet de construire un element de base avec des bord
	 * @param x décalage horizontal
	 * @param y décalage vertical
	 * @param width largeur
	 * @param height hauteur
	 * @param background coleur de fond
	 * @param border épaisseur des bords
	 */
	public BasicBorder(int border, byte borderColor) {
		this.topBorder = border;
		this.bottomBorder = border;
		this.leftBorder = border;
		this.rightBorder = border;
		this.border = borderColor;
	}
	
	@Override
	public void render(Element ele, NesScreen screen) {
		if (!Element.colorIsInvisible(this.border)) {
			//draw border
			screen.setColor(border);
			//horizontal border
			int x = ele.getX();
			int mx = x - this.leftBorder;
			screen.fillRect(mx, ele.getY(), this.leftBorder, ele.getHeight());//left border
			screen.fillRect(x + ele.getWidth(), ele.getY(), this.rightBorder, ele.getHeight());//right border
			//vertical border
			int lengthX = this.leftBorder + ele.getWidth() + this.rightBorder;
			int y = ele.getY();
			screen.fillRect(mx, y - this.topBorder, lengthX, this.topBorder);//top border
			screen.fillRect(mx, y + ele.getHeight(), lengthX, this.bottomBorder);//bottom border
		}
	}

	/**
	 * Permet de récupérer l'épaisseur du bord du haut de l'element
	 * @return l'épaisseur du bord du haut de l'element
	 */
	public int getTopBorder() {
		return this.topBorder;
	}

	/**
	 * Permet de modifier l'épaisseur du bord du haut de l'element
	 * @param bottomBorder la nouvelle épaisseur du haut du bas de l'element
	 */
	public void setTopBorder(int topBorder) {
		this.topBorder = topBorder;
	}

	/**
	 * Permet de récupérer l'épaisseur du bord du bas de l'element
	 * @return l'épaisseur du bord du bas de l'element
	 */
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

	/**
	 * Permet de récupérer l'épaisseur du bord gauche de l'element
	 * @return l'épaisseur du bord gauche de l'element
	 */
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

	/**
	 * Permet de récupérer l'épaisseur du bord droit de l'element
	 * @return l'épaisseur du bord droit de l'element
	 */
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
	 * Permet de récupérer la couleur du bord de l'element
	 * @return la couleur du bord de l'element
	 */
	public byte getBorderColor() {
		return this.border;
	}

	/**
	 * Permet de modifier le border de l'element
	 * @param border le nouveau border de l'element
	 */
	public void setBorderColor(byte color) {
		this.border = color;
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

}
