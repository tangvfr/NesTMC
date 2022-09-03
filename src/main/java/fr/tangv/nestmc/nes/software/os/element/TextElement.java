package fr.tangv.nestmc.nes.software.os.element;

import org.apache.commons.lang.Validate;

import fr.tangv.nestmc.draw.CalcCofMinecraftFont;
import fr.tangv.nestmc.nes.NesScreen;
import fr.tangv.nestmc.nes.TMCNes;

/**
 * @author Tangv - https://tangv.fr
 * Element permetant d'afficher du texte
 */
public class TextElement extends AlignedElement {

	public static final int NONE_HEIGHT = -1;//taille mini pour afficher el texte
	public static final int MIN_HEIGHT = 7;//taille mini pour afficher el texte
	public static final int GOOD_HEIGHT = 9;//bonne taille pour afficher du texte
	
	//ajouter systeme pour faire pack le text element prend le moins de place possible mais lissible
	
	private String text;
	private byte textCof = 1;
	private byte textColor;
	private String drawedText;
	private int drawedX;
	private int drawedY;
	
	/**
	 * Permet de construire un element contenant du texte par default est en alignement start et de taille 1
	 * @param x décalage horizontal
	 * @param y décalage vertical
	 * @param width largeur
	 * @param height hauteur
	 * @param background coleur de fond
	 * @param text texte dessiner
	 * @param textColor couleur du texte
	 */
	public TextElement(int x, int y, int width, int height, byte background, String text, byte textColor) {
		super(x, y, width, height, background);
		Validate.notNull(text, "Text can't null !");
		this.text = text;
		this.textColor = textColor;
	}
	
	/**
	 * Permet de construire un element contenant du texte par default est en alignement start
	 * @param x décalage horizontal
	 * @param y décalage vertical
	 * @param width largeur
	 * @param height hauteur
	 * @param background coleur de fond
	 * @param text texte dessiner
	 * @param textColor couleur du texte
	 * @param textCof taille du texte
	 */
	public TextElement(int x, int y, int width, int height, byte background, String text, byte textColor, byte textCof) {
		this(x, y, width, height, background, text, textColor);
		this.textCof = textCof;
	}
	
	@Override
	public void render(TMCNes nes, NesScreen screen) {
		super.render(nes, screen);
		if (!Element.colorIsInvisible(this.textColor)) {
			screen.setCof(this.textCof);
			screen.setColor(this.textColor);
			screen.drawText(this.drawedX, this.drawedY, this.drawedText);
		}
	}

	/**
	 * Permet de récupérer le texte dessiner
	 * @return le texte dessiner
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * Permet de modifier le texte dessiner
	 * @param text le nouveau texte dessiner
	 */
	public void setText(String text) {
		this.text = text;
		this.updateSizeAndPosition();
	}

	/**
	 * Permet de récupérer le coefficient de la taille du texte
	 * @return le coefficient de la taille du texte
	 */
	public byte getTextCof() {
		return this.textCof;
	}

	/**
	 * Permet de modifier le coefficient de la taille du texte
	 * @param cof le nouveau coefficient de la taille du texte
	 */
	public void setTextCof(byte cof) {
		this.textCof = cof;
		this.updateSizeAndPosition();
	}

	/**
	 * Permet de récupérer la couleur du texte
	 * @return la couleur du texte
	 */
	public byte getTextColor() {
		return this.textColor;
	}

	/**
	 * Permet de modifier la couleur du texte
	 * @param textColor la nouvelle couleur du texte
	 */
	public void setTextColor(byte textColor) {
		this.textColor = textColor;
	}
	
	@Override
	public void updateSizeAndPosition() {
		int height = this.getHeight();
		int lineHeight = CalcCofMinecraftFont.getHeightText(this.textCof) - this.textCof;
		
		if (height >= lineHeight) {//test si on peu écrire une ligne
			int textLength = this.text.length();
			int i = 0;
			
			int drawLength = 0;
			int calcLength = 0;
			int width = this.getWidth();
			
			while (i < textLength) {
				//add length
				if (i != 0) {
					calcLength += this.textCof;
				}
				calcLength += CalcCofMinecraftFont.getWidthChar(this.text.charAt(i), this.textCof);
				
				//test length
				if (calcLength <= width) {
					drawLength = calcLength;
					i++;
				} else {
					textLength = -1;
				}
			}
			
			//draw test
			this.drawedX = this.getX() + this.getHorizontalAlign().calcOffset(width, drawLength);
			this.drawedY = this.getY() + this.getVerticalAlign().calcOffset(height, lineHeight);
			this.drawedText = text.substring(0, i);
		} else {
			this.drawedText = "";
		}
	}
	
}
