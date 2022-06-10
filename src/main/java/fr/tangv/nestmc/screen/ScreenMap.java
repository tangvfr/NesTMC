package fr.tangv.nestmc.screen;

/*
 * écran d'une nes sur 4 maps
 */
public class ScreenMap {

	//les 4 maps minecraft qui compose l'ecran de la nes
	private final BitScreen[] maps;
	
	public ScreenMap() {
		this.maps = new BitScreen[4];
		this.maps[0].clearScreen((byte) 0);
	}
	
}
