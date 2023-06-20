package fr.tangv.nestmc.nes.software.os.element.border;

import fr.tangv.nestmc.nes.NesScreen;
import fr.tangv.nestmc.nes.software.os.element.Element;

/**
 * @author Tangv - https://tangv.fr
 * Bordure simple pour un Element
 */
public class BasicBorder extends Border implements ColoredBorder {

	protected int topBorder;
	protected int bottomBorder;
	protected int leftBorder;
	protected int rightBorder;
	protected byte border;
	
	/**
	 * Permet de construire une bordure de base
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
	 * Permet de construire une bordure de base
	 * @param border épaisseur de tous les bords
	 * @param borderColor couleur des bords
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
			//x
			int x = ele.getX();
			int width = ele.getWidth();
			int mix = x - this.leftBorder;
			int max = x + width;
			int lengthX = this.leftBorder + width + this.rightBorder;
			
			//y
			int height = ele.getHeight();
			int y = ele.getY();
			int miy = y - this.topBorder;
			int may = y + height;
			
			//horizontal border
			screen.setColor(this.border);
			screen.fillRect(mix, y, this.leftBorder, height);//left border
			screen.fillRect(max, y, this.rightBorder, height);//right border
			//vertical border
			screen.fillRect(mix, miy, lengthX, this.topBorder);//top border
			screen.fillRect(mix, may, lengthX, this.bottomBorder);//bottom border
		}
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

	@Override
	public byte getBorderColor() {
		return this.border;
	}

	@Override
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
	 * Permet de modifier l'épaisseur des bords de l'element
	 * @param topBorder la nouvelle épaisseur du bord du haut de l'element
	 * @param bottomBorder la nouvelle épaisseur du bord du bas de l'element
	 * @param leftBorder la nouvelle épaisseur du bord gauche de l'element
	 * @param rightBorder la nouvelle épaisseur du bord droit de l'element
	 */
	public void setBorder(int topBorder, int bottomBorder, int leftBorder, int rightBorder ) {
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

}
