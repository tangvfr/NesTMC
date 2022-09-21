/**
 * 
 */
package fr.tangv.nestmc.nes;

import fr.tangv.nestmc.draw.MapBuffer;

/**
 * @author Tangv - https://tangv.fr
 * Permet de crée un écran de 4 map pour une nes avec les fps de calculé
 */
public abstract class FpsNesScreenMap extends NesScreenMap {

	/*tableau our faire la moyen des fps*/
	private final long[] frameTimes = new long[60];
	/*pointeur dans le tableau des fps*/
	private int frameTimePtr = 0;
	/*fps calculé*/
	private double fps = 0D;
	
	/**
	 * Permet de crée l'écran de la nes sur 4 MapBuffer avec les fps de calculé
	 * @param bitScreens les ecrans qui font l'écran de la NES
	 * @param colors palette de couleur pour la nes
	 */
	public FpsNesScreenMap(MapBuffer[] bitScreens, byte[] colors) {
		super(bitScreens, colors);
	}
	
	@Override
	public void setFrame(int[] nespixels, int[] bgcolor, boolean dotcrawl) {
		super.setFrame(nespixels, bgcolor, dotcrawl);
		
		//calc fps
		this.frameTimes[this.frameTimePtr] = this.getNes().getFrameTime();
        ++this.frameTimePtr;
        this.frameTimePtr %= this.frameTimes.length;

        if (this.frameTimePtr == 0) {
            long averageframes = 0;
            for (long l : this.frameTimes) {
                averageframes += l;
            }
            averageframes /= this.frameTimes.length;
            this.fps = 1E9 / averageframes;
        }
	}

	/**
	 * Permet de récupérer les fps de la nes
	 * @return les fps de la nes
	 */
	public double getFps() {
		return this.fps;
	}

}
