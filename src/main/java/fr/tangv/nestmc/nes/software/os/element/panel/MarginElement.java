package fr.tangv.nestmc.nes.software.os.element.panel;

import javax.swing.text.View;

import fr.tangv.nestmc.nes.software.os.element.Element;

/**
 * @author Tangv - https://tangv.fr
 * Element qui contient un autre pour lui faire une marge
 */
public class MarginElement extends ViewElement {
	/*
	protected int topBorder;
	protected int bottomBorder;
	protected int leftBorder;
	protected int rightBorder;
	
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
	}*/
	
	@Override
	public void updateSizeAndPosition() {
		Element view = this.getView();
		if (view != null) {
			view.setX(this.getX() + this.getHorizontalAlign().calcOffset(
					this.getWidth(),
					view.getWidth()
					));
			
			view.setY(this.getY() + this.getVerticalAlign().calcOffset(
					this.getHeight(),
					view.getHeight()
					));
			
			view.updateSizeAndPosition();
		}
	}
	
}
