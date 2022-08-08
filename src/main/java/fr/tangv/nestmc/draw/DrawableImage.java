/**
 * 
 */
package fr.tangv.nestmc.draw;

import org.apache.commons.lang.Validate;

/**
 * @author Tangv - https://tangv.fr
 * Image desinable dans un dessins
 */
public class DrawableImage {

	private final byte[] buf;
	private final int width;
	private final int height;
	
	/**
	 * Permet de construire 
	 * @param buf donnée de l'image
	 * @param width largeur de l'image
	 * @param height longueur de l'image
	 */
	public DrawableImage(byte[] buf, int width, int height) {
		Validate.isTrue(buf.length == width * height, "Invalide dimmension !");
		this.buf = buf;
		this.width = width;
		this.height = height;
	}
	/**
	 * Permet de récupérer le buffer de l'image
	 * @return le buffer de l'image
	 */
	public byte[] getBuf() {
		return this.buf;
	}
	/**
	 * Permet de récupérer la largeur de l'image
	 * @return la largeur de l'image
	 */
	public int getWidth() {
		return this.width;
	}
	/**
	 * Permet de récupérer la hauteur de l'image
	 * @return la hauteur de l'image
	 */
	public int getHeight() {
		return this.height;
	}
	
}
