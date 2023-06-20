package fr.tangv.nestmc.draw;

/**
 * @author Tangv - https://tangv.fr
 *	Permet de dessiner sur un buffer de map
 */
public class MapScreen extends DrawableBuffer {

	/**
	 * Constructeur qui permet de crée un buffer de la taille d'une map dessinable (128x128)
	 */
	public MapScreen() {
		super(MapBuffer.WIDTH);
	}

}
