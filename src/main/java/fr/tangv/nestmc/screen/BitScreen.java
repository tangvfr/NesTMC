package fr.tangv.nestmc.screen;

/**
 * @author tangv
 * Partie d'un écran
 */
public class BitScreen implements Pixelable {
	
	//largeur du carré
	private final static int WIDTH = 128;
	//nombre de couleur (l'ecran est un carré)
	private final static int LENGTH = BitScreen.WIDTH * BitScreen.WIDTH;

	//tableau des couleur
	private final byte[] buf;
	
	/**
	 * Constructeur qui permet de crée une partie d'écran de 128x128
	 */
	public BitScreen() {
		this.buf = new byte[BitScreen.LENGTH];
	}
	
	/**
	 * Retourne les couleur qui compose la partie de l'écran
	 * @return donnée de l'image
	 */
	public byte[] getBuffer() {
		return this.buf;
	}
	
	/**
	 * Méthode qui permet de définir un pixel sur la partie d'écran
	 * @param x décalage en partant de la gauche
	 * @param y décalage en partant du haut
	 * @param color couleur définie
	 */
	@Override
	public void setPixel(int x, int y, byte color) {
		this.buf[x + (y * BitScreen.WIDTH)] = color;
	}
	
	/**
	 * Méthode qui permet de remplacé tout les couleurs de la partie d'écran
	 * @param color couleur de remplacement
	 */
	public void clearScreen(byte color) {
		for (int i = 0; i < BitScreen.LENGTH; i++) {//parcours de tout les couleurs
			this.buf[i] = color;
		}
	}
	
}
