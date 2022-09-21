package fr.tangv.nestmc.draw;

/**
 * @author Tangv - https://tangv.fr
 * Permet de définir un pixel
 */
public interface Pixelable {

	/**
	 * Méthode qui permet de definir un pixel
	 * @param x décalage en partant de la gauche
	 * @param y décalage en partant du haut
	 * @param color couleur définie
	 */
	public void setPixel(int x, int y, byte color);
	
	/**
	 * Méthode qui permet de remplacé tout les couleurs par une autre
	 * @param color couleur de remplacement
	 */
	public void clearScreen(byte color);
	
}
