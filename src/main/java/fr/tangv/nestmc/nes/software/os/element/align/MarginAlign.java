package fr.tangv.nestmc.nes.software.os.element.align;

/**
 * @author Tangv - https://tangv.fr
 * Permet de faire un alignement en etant aligner au millieu du contenneur
 */
public class MarginAlign implements Align {

	private final int margin;
	
	/**
	 * Permet de construire un aligneur avec un marge constante
	 * @param margin la marge constante
	 */
	public MarginAlign(int margin) {
		this.margin = margin;
	}
	
	/**
	 * Permet de récupérer la marge constante de l'aglineur
	 * @return la marge constante de l'aglineur
	 */
	public int getMargin() {
		return this.margin;
	}
	
	@Override
	public int calcOffset(int containerLength, int elementLength) {
		return this.margin;
	}
	
}
