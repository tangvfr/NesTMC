package fr.tangv.nestmc.nes.software.os.element.text;

import org.apache.commons.lang.Validate;

import fr.tangv.nestmc.draw.CalcCofMinecraftFont;
import fr.tangv.nestmc.nes.NesScreen;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.software.os.element.AlignedElement;
import fr.tangv.nestmc.nes.software.os.element.Element;

/**
 * @author Tangv - https://tangv.fr
 * Element permetant d'afficher du texte
 */
public class TextElement extends AlignedElement {

	public static final int MIN_HEIGHT = 7;//taille mini pour afficher el texte
	public static final int GOOD_HEIGHT = 9;//bonne taille pour afficher du texte
	
	private String text;
	private byte textCof = 1;
	private byte textColor;
	private int preferedWidth;
	private int preferedHeigth;
	private SizeMode horizontalMode = SizeMode.PREFERED;
	private SizeMode verticalMode = SizeMode.PREFERED;
	private String drawedText;
	private int drawedX;
	private int drawedY;
	
	/**
	 * Permet de construire un element contenant du texte par default est en alignement start et de taille 1 et prendra la place donner par default
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
		this.preferedWidth = width;
		this.preferedHeigth = height;
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
	public void setWidth(int width) {
		super.setWidth(width);
		this.preferedWidth = width;
	}
	
	@Override
	public void setHeight(int height) {
		super.setHeight(height);
		this.preferedHeigth = height;
	}
	
	/**
	 * Permet de récupérer l'ajustement de la largueur de l'element de texte
	 * @return le mode d'ajustement de la largueur de l'element de texte
	 */
	public SizeMode getHorizontalMode() {
		return this.horizontalMode;
	}

	/**
	 * Permet de modifier l'ajustement de la largueur de l'element de texte
	 * @param horizontalMode le nouveau mode d'ajustement de la largueur de l'element de texte
	 */
	public void setHorizontalMode(SizeMode horizontalMode) {
		this.horizontalMode = horizontalMode;
		this.updateSizeAndPosition();
	}

	/**
	 * Permet de récupérer l'ajustement de la hauteur de l'element de texte
	 * @return le mode d'ajustement de la hauteur de l'element de texte
	 */
	public SizeMode getVerticalMode() {
		return this.verticalMode;
	}

	/**
	 * Permet de modifier l'ajustement de la hauteur de l'element de texte
	 * @param horizontalMode le nouveau mode d'ajustement de la hauteur de l'element de texte
	 */
	public void setVerticalMode(SizeMode verticalMode) {
		this.verticalMode = verticalMode;
		this.updateSizeAndPosition();
	}

	@Override
	public void updateSizeAndPosition() {
		int height;
		switch (this.verticalMode) {
			case MIN:
				height = TextElement.MIN_HEIGHT * this.textCof;
				break;
			case PREFERED:
				height = TextElement.GOOD_HEIGHT * this.textCof;
				break;
			case MAX:
			default:
				height = this.preferedHeigth;
				break;
		}
		super.setHeightNoUpdate(height);
		
		//calc width and check height
		int lineHeight = CalcCofMinecraftFont.getHeightText(this.textCof) - this.textCof;
		
		if (height >= lineHeight) {//test si on peu écrire une ligne
			int textLength = this.text.length();
			int i = 0;
			
			int drawLength = 0;
			int calcLength = 0;
			int width = this.preferedWidth;
			
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
					textLength = -1;//pour sortie de la boucle
				}
			}
			
			//test mode horizontal
			if (this.horizontalMode == SizeMode.MIN) {
				width = drawLength;
			}
			super.setWidthNoUpdate(width);
			
			//draw test
			this.drawedX = this.getX() + this.getHorizontalAlign().calcOffset(width, drawLength);
			this.drawedY = this.getY() + this.getVerticalAlign().calcOffset(height, lineHeight);
			this.drawedText = text.substring(0, i);
		} else {
			this.drawedText = "";
		}
	}
	
}
