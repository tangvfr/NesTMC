package fr.tangv.nestmc.nes.software.os.element.border;

import fr.tangv.nestmc.nes.NesScreen;
import fr.tangv.nestmc.nes.software.os.element.Element;

/**
 * @author Tangv - https://tangv.fr
 * Bordure simple pour un Element
 */
public class CornerBasicBorder extends BasicBorder {

	private byte corner;
	
	/**
	 * Permet de construire une bordure de base avec des coins
	 * @param topBorder épaisseur du bord du haut
	 * @param bottomBorder épaisseur du bord du bas
	 * @param leftBorder épaisseur du bord de gauche
	 * @param rightBorder épaisseur du bord de droite
	 * @param borderColor couleur des bords
	 * @param cornerColor couleur des coins
	 */
	public CornerBasicBorder(int topBorder, int bottomBorder, int leftBorder, int rightBorder, byte borderColor, byte cornerColor) {
		super(topBorder, bottomBorder, leftBorder, rightBorder, borderColor);
		this.corner = cornerColor;
	}
	
	/**
	 * Permet de construire un element de base avec des bord avec des coins
	 * @param border épaisseur de tous les bords
	 * @param borderColor couleur des bords
	 * @param cornerColor couleur des coins
	 */
	public CornerBasicBorder(int border, byte borderColor, byte cornerColor) {
		super(border, borderColor);
		this.corner = cornerColor;
	}
	
	@Override
	public void render(Element ele, NesScreen screen) {
		//x
		int x = ele.getX();
		int width = ele.getWidth();
		int mix = x - this.leftBorder;
		int max = x + width;
		
		//y
		int height = ele.getHeight();
		int y = ele.getY();
		int miy = y - this.topBorder;
		int may = y + height;
		
		//border
		if (!Element.colorIsInvisible(this.border)) {
			//horizontal border
			screen.setColor(this.border);
			screen.fillRect(mix, y, this.leftBorder, height);//left border
			screen.fillRect(max, y, this.rightBorder, height);//right border
			//vertical border
			screen.fillRect(x, miy, width, this.topBorder);//top border
			screen.fillRect(x, may, width, this.bottomBorder);//bottom border
		}
		
		//corner
		if (!Element.colorIsInvisible(this.corner)) {
			//draw corner
			screen.setColor(this.corner);
			screen.fillRect(mix, miy, this.leftBorder, this.topBorder);//top left
			screen.fillRect(max, miy, this.rightBorder, this.topBorder);//top right
			screen.fillRect(mix, may, this.leftBorder, this.bottomBorder);//bottom left
			screen.fillRect(max, may, this.rightBorder, this.bottomBorder);//bottom right
		}
	}

	/**
	 * Permet de récupérer la couleur des coins de l'element
	 * @return la couleur des coins de l'element
	 */
	public byte getCornerColor() {
		return this.corner;
	}

	/**
	 * Permet de modifier la couleur des coins de l'element
	 * @param border la nouvelle couleur des coins de l'element
	 */
	public void setCornerColor(byte color) {
		this.corner = color;
	}
	
}
