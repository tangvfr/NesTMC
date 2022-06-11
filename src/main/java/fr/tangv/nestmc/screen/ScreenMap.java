package fr.tangv.nestmc.screen;

/*
 * écran d'une nes sur 4 maps sois 256x256
 */
public class ScreenMap implements Drawable {

	//les 4 maps minecraft qui compose l'ecran de la nes
	private final BitScreen[] bitScreens;
	
	/**
	 * Construteur qui permet de construire un écran pour la nes avec 4 partie d'écran
	 * @param maps les 4 partie d'écran compossent l'écran
	 */
	public ScreenMap(BitScreen[] bitScreens) {
		if (bitScreens == null || bitScreens.length != 4)
			throw new IllegalArgumentException("Maps have length different of 4 !");
		
		this.bitScreens = bitScreens;
	}
	
	/**
	 * Méthode qui renvoie les partie d'écran
	 * @return un tableau de 4 morceaux d'écran compossant l'écran
	 */
	public BitScreen[] getBitScreens() {
		return this.bitScreens;
	}
	
	/**
	 * Méthode qui permet de définir un pixel sur l'écran
	 * @param x décalage en partant de la gauche
	 * @param y décalage en partant du haut
	 * @param color couleur définie
	 */
	@Override
	public void setPixel(int x, int y, byte color) {
		int bitS = 0;//partie d'écran conserné
		int iX = x;
		int iY = y;

		//test de x dans quel écran il se trouve
		if (x > 127) {
			bitS += 1;
			iX -= 128;
		}
		//test de y dans quel écran il se trouve
		if (y > 127) {
			bitS += 2;
			iY -= 128;
		}
		
		this.bitScreens[bitS].setPixel(iX, iY, color);
	}
	
	/**
	 * Méthode qui permet de remplacé toutes les couleurs de l'écran
	 * @param color couleur de remplacement
	 */
	public void clearScreen(byte color) {
		for (int i = 0; i < 4; i++) {//parcours des parties d'écran qui compose l'écran
			this.bitScreens[i].clearScreen(color);
		}
	}
	
}
