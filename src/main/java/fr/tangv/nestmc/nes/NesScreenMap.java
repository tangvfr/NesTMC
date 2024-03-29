package fr.tangv.nestmc.nes;

import com.grapeshot.halfnes.NES;
import com.grapeshot.halfnes.ui.GUIInterface;
import fr.tangv.nestmc.draw.MapBuffer;

/**
 * @author Tangv - https://tangv.fr
 * Permet de crée un écran de 4 map pour une nes
 */
public abstract class NesScreenMap extends NesScreen implements GUIInterface {

	private static final int LENGTH_SCREEN_BUFFER = 256 * 240;
	private static final int HALF_HEIGHT_SCREEN = NesScreen.SCREEN_HEIGHT / 2;
	private static final int HALF_WIDTH_SCREEN = NesScreen.WIDTH / 2;
	
	/*palette de couleur pour la nes*/
	private final byte[] colors;
	/*Nes a llaquelle apartien le Gui interface*/
	private NES nes;
	/*le dernier buffer d'image*/
	private int[] nespixels;
	
	/**
	 * Permet de crée l'écran de la nes sur 4 MapBuffer
	 * @param bitScreens les ecrans qui font l'écran de la NES
	 * @param colors palette de couleur pour la nes
	 */
	public NesScreenMap(MapBuffer[] bitScreens, byte[] colors) {
		super(bitScreens);
		this.colors = colors;
		this.nespixels = new int[NesScreenMap.LENGTH_SCREEN_BUFFER];//by default 0
	}
	
	@Override
	public NES getNes() {
		return this.nes;
	}
	
	@Override
	public void setNES(NES nes) {
		this.nes = nes;
	}

	/**
	 * Permet d'ecrire dans deux ecran gauche et droite un certain nombre de ligne de l'ecran de la nes
	 * @param nespixels buffer de l'ecran de la nes
	 * @param startBuf debut dans le buffer
	 * @param screenLeft le buffer de droite de 128x128
	 * @param screenRight le buffer de gauche de 128x128
	 * @param numberOfLine nombre de ligne a afficher
	 * @return index d'arrête de lecture du buffer de l'ecran de la nes
	 */
	private int writeLigneInScreen(int[] nespixels, int startBuf, byte[] screenLeft, byte[] screenRight, int numberOfLine) {
		//avancement dans le buffer de l'écran de la nes
		int i = startBuf;
		//palette des couleur nes pour les maps
		byte[] palette = this.colors;
		//index exclu de fin des lignes a copier
		int endLengthY = numberOfLine * NesScreenMap.HALF_WIDTH_SCREEN;
		//index exclu de fin de la ligne a copier
		int endLengthX;
		
		for (int y = 0; y < endLengthY; y += NesScreenMap.HALF_WIDTH_SCREEN) {//parcours tous les lignes de la map de 128x128
			endLengthX = y + NesScreenMap.HALF_WIDTH_SCREEN;
			//left screen
			for (int ls = y; ls < endLengthX; ls++) {//parcours les 128 premier pixel de la ligne de l'ecran de la nes
				screenLeft[ls] = palette[nespixels[i]];
				i++;
			}
			//right scren
			for (int rs = y; rs < endLengthX; rs++) {//parcours les 128 dernier pixel de la ligne de l'ecran de la nes
				screenRight[rs] = palette[nespixels[i]];
				i++;
			}
		}
		
		return i;
	}
	
	@Override
	public void drawNesScreen() {
		//nespixels = 256x240
		MapBuffer[] screens = this.getBitScreens();

		synchronized (this) {
			//haut de l'ecran les 128 premiere ligne
			int iStoped = this.writeLigneInScreen(nespixels, 0, screens[0].getBuffer(), screens[1].getBuffer(), NesScreenMap.HALF_HEIGHT_SCREEN);
			
			//bas de l'ecran les 112 dernier ligne
			this.writeLigneInScreen(nespixels, iStoped, screens[2].getBuffer(), screens[3].getBuffer(), NesScreen.NES_HEIGHT - NesScreenMap.HALF_HEIGHT_SCREEN);
		}
	}
	
	/**
	 * Méthode qui est appeler lorsque le PPU de la NES a calculer une frame
	 */
	@Override
	public void setFrame(int[] nespixels, int[] bgcolor, boolean dotcrawl) {
		synchronized (this) {
			for (int i = 0; i < NesScreenMap.LENGTH_SCREEN_BUFFER; i++) {
				this.nespixels[i] = nespixels[i];
			}
		}
	}

}
