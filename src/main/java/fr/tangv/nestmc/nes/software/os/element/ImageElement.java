package fr.tangv.nestmc.nes.software.os.element;

import fr.tangv.nestmc.draw.DrawableImage;
import fr.tangv.nestmc.nes.NesScreen;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.software.os.element.align.Aligns;

/**
 * @author Tangv - https://tangv.fr
 * Element affichant une Image
 */
public class ImageElement extends AlignedElement {

	private byte imgCof = 1;
	private DrawableImage img;
	private int offsetX;
	private int offsetY;
	
	/**
	 * Permet de construire un element contenant une image
	 * @param x décalage horizontal
	 * @param y décalage vertical
	 * @param width largeur
	 * @param height hauteur
	 * @param background couleur du fond de l'element
	 * @param img image dessiner, null pour aucune
	 */
	public ImageElement(int x, int y, int width, int height, byte background, DrawableImage img) {
		super(x, y, width, height, background);
		this.img = img;
		this.setHorizontalAlign(Aligns.CENTER);
		this.setVerticalAlign(Aligns.CENTER);
	}
	
	/**
	 * Permet de construire un element contenant une image, en mettant la taille de l'element en rapport
	 * @param img image dessiner 
	 * @param background couleur du fond de l'element
	 * @param marginX marge en haut et en bas du dessins
	 * @param marginY marge à gauche et a droite du dessins
	 */
	public ImageElement(DrawableImage img, byte background, int marginX, int marginY) {
		this(0, 0, img.getWidth() + (marginX * 2), img.getHeight() + (marginY * 2), background, img);
	}
	
	/**
	 * Permet de construire un element contenant une image, en mettant la taille de l'element en rapport
	 * @param img image dessiner 
	 * @param background couleur du fond de l'element
	 * @param margin marge autour du dessins
	 */
	public ImageElement(DrawableImage img, byte background, int margin) {
		this(img, background, margin, margin);
	}
	
	/**
	 * Permet de construire un element contenant une image, en mettant la taille de l'element en rapport
	 * @param img image dessiner 
	 * @param background couleur du fond de l'element
	 */
	public ImageElement(DrawableImage img, byte background) {
		this(img, background, 0);
	}

	@Override
	public void render(TMCNes nes, NesScreen screen) {
		super.render(nes, screen);
		
		if (this.img != null) {//check si il y a une image
			screen.setCof(this.imgCof);
			screen.drawImage(this.offsetX, this.offsetY, this.img);
		}
	}
	
	/**
	 * Permet de récupérer le coefficient de l'image
	 * @return le coefficient de l'image
	 */
	public byte getImgCof() {
		return this.imgCof;
	}

	/**
	 * Permet de modifier le coefficient de l'image
	 * @param cof le nouveau coefficient de l'image
	 */
	public void setImgCof(byte cof) {
		this.imgCof = cof;
		this.updateSizeAndPosition();
	}
	
	/**
	 * Permet de modifier le coefficient de l'image et les dimmensions de l'element en rapport
	 * @param cof le nouveau coefficient de l'image
	 * @param marginX marge en haut et en bas du dessins
	 * @param marginY marge à gauche et a droite du dessins
	 */
	public void setImgCofAndSize(byte cof, int marginX, int marginY) {
		this.setImgCof(cof);
		this.setHeight((img.getHeight() * cof) + (marginX * 2));
		this.setWidth((img.getWidth() * cof) + (marginX * 2));
	}
	
	/**
	 * Permet de modifier le coefficient de l'image et les dimmensions de l'element en rapport
	 * @param cof le nouveau coefficient de l'image
	 * @param margin autour du dessins
	 */
	public void setImgCofAndSize(byte cof, int margin) {
		this.setImgCofAndSize(cof, margin, margin);
	}
	
	/**
	 * Permet de modifier le coefficient de l'image et les dimmensions de l'element en rapport
	 * @param cof le nouveau coefficient de l'image
	 */
	public void setImgCofAndSize(byte cof) {
		this.setImgCofAndSize(cof, 0);
	}

	/**
	 * Permet de récupérer l'image dessiner
	 * @return l'image dessiner
	 */
	public DrawableImage getImg() {
		return this.img;
	}

	/**
	 * Permet de modifier l'image dessiner
	 * @param img la nouvelle image dessiner, null pour aucune
	 */
	public void setImg(DrawableImage img) {
		this.img = img;
		this.updateSizeAndPosition();
	}
	
	@Override
	public void updateSizeAndPosition() {
		if (this.img != null) {
			int height = this.getHeight();
			int width = this.getWidth();
			int heightImg = this.img.getHeight() * this.imgCof;
			int widthImg = this.img.getWidth() * this.imgCof;
			
			if (width >= widthImg && height >= heightImg) {//test si l'image rentre
				this.offsetX = this.getX() + this.getHorizontalAlign().calcOffset(width, widthImg);
				this.offsetY = this.getY() + this.getVerticalAlign().calcOffset(height, heightImg);
			}
		}
	}
	
}
