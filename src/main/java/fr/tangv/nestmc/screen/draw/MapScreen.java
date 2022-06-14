package fr.tangv.nestmc.screen.draw;

/**
 * @author tangv
 *	Permet de dessiner sur un buffer de map
 */
public class MapScreen extends Drawable implements MapBuffered {

	//tableau des couleurs
	private final byte[] buf;

	/**
	 * Constructeur qui permet de crée un buffer de la taille d'une map desinable
	 */
	public MapScreen() {
		super(MapBuffer.WIDTH);
		this.buf = new byte[MapBuffer.LENGTH];
	}

	@Override
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
