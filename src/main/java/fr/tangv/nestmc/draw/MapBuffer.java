package fr.tangv.nestmc.draw;

/**
 * @author tangv
 * Partie d'un écran, écran de la taille d'une map minecraft
 */
public class MapBuffer implements Pixelable, MapBuffered {
	
	//largeur du carré
	public final static int WIDTH = 128;
	//nombre de couleur (l'ecran est un carré)
	public final static int LENGTH = MapBuffer.WIDTH * MapBuffer.WIDTH;

	//tableau des couleur
	private final byte[] buf;
	
	/**
	 * Constructeur qui permet de crée une partie d'écran de 128x128
	 */
	public MapBuffer() {
		this.buf = new byte[MapBuffer.LENGTH];
	}
	
	public byte[] getBuffer() {
		return this.buf;
	}
	
	@Override
	public void setPixel(int x, int y, byte color) {
		this.buf[x + (y * MapBuffer.WIDTH)] = color;
	}
	
	@Override
	public void clearScreen(byte color) {
		for (int i = 0; i < MapBuffer.LENGTH; i++) {//parcours de tout les couleurs
			this.buf[i] = color;
		}
	}
	
}
