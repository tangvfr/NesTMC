package fr.tangv.nestmc.nes.software.os.element;

import fr.tangv.nestmc.draw.DrawableImage;
import fr.tangv.nestmc.nes.NesScreen;
import fr.tangv.nestmc.nes.TMCNes;

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
	 * @param background coleur de fond
	 * @param img image dessiner, null pour aucune
	 */
	public ImageElement(int x, int y, int width, int height, byte background, DrawableImage img) {
		super(x, y, width, height, background);
		this.img = img;
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
