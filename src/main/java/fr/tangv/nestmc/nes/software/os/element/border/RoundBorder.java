package fr.tangv.nestmc.nes.software.os.element.border;

import fr.tangv.nestmc.nes.NesScreen;
import fr.tangv.nestmc.nes.software.os.element.Element;

/**
 * @author Tangv - https://tangv.fr
 * Bordure simple arrondie pour un Element
 */
public class RoundBorder extends Border implements ColoredBorder {

	protected int borderSize;
	protected byte border;
	protected int round;

	/**
	 * Permet de construire une bordure de base arrondie
	 * @param borderSize épaisseur de tous les bords
	 * @param borderColor couleur des bords
	 * @param round l'arrondie
	 */
	public RoundBorder(int borderSize, byte borderColor, int round) {
		this.borderSize = borderSize;
		this.border = borderColor;
		this.round = round;
	}
	
	@Override
	public void render(Element ele, NesScreen screen) {
		if (!Element.colorIsInvisible(this.border)) {
			//x
			int x = ele.getX();
			int y = ele.getY();
			int width = ele.getWidth();
			int height = ele.getHeight();
			int sx = x - this.borderSize;
			int sy = y - this.borderSize;
			int lengthX = width + (this.borderSize * 2);
			int lengthY = height + (this.borderSize * 2);
			
			//horizontal border
			screen.setColor(this.border);
			screen.fillAroundRect(sx, sy, lengthX, lengthY, this.round);
		}
	}

	@Override
	public int getTopBorder() {
		return this.borderSize;
	}


	@Override
	public int getBottomBorder() {
		return this.borderSize;
	}


	@Override
	public int getLeftBorder() {
		return this.borderSize;
	}

	@Override
	public int getRightBorder() {
		return this.borderSize;
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
		this.borderSize = border;
	}

	/**
	 * Permet de récupérer la taille du bord
	 * @return la taille du bord
	 */
	public int getBorder() {
		return this.borderSize;
	}

	/**
	 * Permet de modifier l'arrondie du bord
	 * @param round le nouveau l'arrondie du bord
	 */
	public void setRound(int round) {
		this.round = round;
	}

	/**
	 * Permet de récupérer l'arrondie du bord
	 * @return l'arrondie du bord
	 */
	public int getRound() {
		return this.round;
	}
	
	@Override
	public int calcXLength() {
		return this.borderSize * 2;
	}
	
	@Override
	public int calcYLength() {
		return this.borderSize * 2;
	}

}
