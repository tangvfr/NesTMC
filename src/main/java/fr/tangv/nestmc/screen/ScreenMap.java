package fr.tangv.nestmc.screen;

/*
 * �cran d'une nes sur 4 maps sois 256x256
 */
public class ScreenMap implements Drawable {

	//les 4 maps minecraft qui compose l'ecran de la nes
	private final BitScreen[] bitScreens;
	
	/**
	 * Construteur qui permet de construire un �cran pour la nes avec 4 partie d'�cran
	 * @param maps les 4 partie d'�cran compossent l'�cran
	 */
	public ScreenMap(BitScreen[] bitScreens) {
		if (bitScreens == null || bitScreens.length != 4)
			throw new IllegalArgumentException("Maps have length different of 4 !");
		
		this.bitScreens = bitScreens;
	}
	
	/**
	 * M�thode qui renvoie les partie d'�cran
	 * @return un tableau de 4 morceaux d'�cran compossant l'�cran
	 */
	public BitScreen[] getBitScreens() {
		return this.bitScreens;
	}
	
	/**
	 * M�thode qui permet de d�finir un pixel sur l'�cran
	 * @param x d�calage en partant de la gauche
	 * @param y d�calage en partant du haut
	 * @param color couleur d�finie
	 */
	@Override
	public void setPixel(int x, int y, byte color) {
		int bitS = 0;//partie d'�cran consern�
		int iX = x;
		int iY = y;

		//test de x dans quel �cran il se trouve
		if (x > 127) {
			bitS += 1;
			iX -= 128;
		}
		//test de y dans quel �cran il se trouve
		if (y > 127) {
			bitS += 2;
			iY -= 128;
		}
		
		this.bitScreens[bitS].setPixel(iX, iY, color);
	}
	
	/**
	 * M�thode qui permet de remplac� toutes les couleurs de l'�cran
	 * @param color couleur de remplacement
	 */
	public void clearScreen(byte color) {
		for (int i = 0; i < 4; i++) {//parcours des parties d'�cran qui compose l'�cran
			this.bitScreens[i].clearScreen(color);
		}
	}
	
}
