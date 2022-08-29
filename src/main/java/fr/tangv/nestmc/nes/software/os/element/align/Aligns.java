package fr.tangv.nestmc.nes.software.os.element.align;

/**
 * @author Tangv - https://tangv.fr
 * Different aligneur
 */
public abstract class Aligns {

	public static final Align START = new StartAlign();
	public static final Align CENTER = new CenterAlign();
	public static final Align END = new EndAlign();
	
	/**
	 * Permet de crée un aligneur par marge
	 * @param margin la marge constante
	 * @return l'aglineur crée
	 */
	public static Align createMargin(int margin) {
		return new MarginAlign(margin);
	}
	
}
