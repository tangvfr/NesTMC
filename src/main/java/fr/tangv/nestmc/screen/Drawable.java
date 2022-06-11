package fr.tangv.nestmc.screen;

public interface Drawable {

	/**
	 * Méthode qui permet de definir un pixel
	 * @param x décalage en partant de la gauche
	 * @param y décalage en partant du haut
	 * @param color couleur définie
	 */
	public void setPixel(int x, int y, byte color);
	
}
