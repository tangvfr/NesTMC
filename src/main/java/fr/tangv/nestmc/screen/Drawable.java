package fr.tangv.nestmc.screen;

public interface Drawable {

	/**
	 * M�thode qui permet de definir un pixel
	 * @param x d�calage en partant de la gauche
	 * @param y d�calage en partant du haut
	 * @param color couleur d�finie
	 */
	public void setPixel(int x, int y, byte color);
	
}
