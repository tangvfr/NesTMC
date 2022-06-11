package fr.tangv.nestmc.screen;

/**
 * @author tangv
 * Partie d'un �cran
 */
public class BitScreen implements Drawable {
	
	//largeur du carr�
	private final static int WIDTH = 128;
	//nombre de couleur (l'ecran est un carr�)
	private final static int LENGTH = BitScreen.WIDTH * BitScreen.WIDTH;

	//tableau des couleur
	private final byte[] buf;
	
	/**
	 * Constructeur qui permet de cr�e une partie d'�cran de 128x128
	 */
	public BitScreen() {
		this.buf = new byte[BitScreen.LENGTH];
	}
	
	/**
	 * Retourne les couleur qui compose la partie de l'�cran
	 * @return donn�e de l'image
	 */
	public byte[] getBuffer() {
		return this.buf;
	}
	
	/**
	 * M�thode qui permet de d�finir un pixel sur la partie d'�cran
	 * @param x d�calage en partant de la gauche
	 * @param y d�calage en partant du haut
	 * @param color couleur d�finie
	 */
	@Override
	public void setPixel(int x, int y, byte color) {
		this.buf[x + (y * BitScreen.WIDTH)] = color;
	}
	
	/**
	 * M�thode qui permet de remplac� tout les couleurs de la partie d'�cran
	 * @param color couleur de remplacement
	 */
	public void clearScreen(byte color) {
		for (int i = 0; i < BitScreen.LENGTH; i++) {//parcours de tout les couleurs
			this.buf[i] = color;
		}
	}
	
}
