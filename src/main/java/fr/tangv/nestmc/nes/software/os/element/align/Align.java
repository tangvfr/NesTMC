package fr.tangv.nestmc.nes.software.os.element.align;

/**
 * @author Tangv - https://tangv.fr
 * Permet d'aligner un contenant et un contenue
 */
public interface Align {
	
	/**
	 * Permet de calculer le décalage pour aligner un une longueur par rapport a une autre
	 * @param containerLength longueur du contenant
	 * @param elementLength longueur du contenue
	 * @return le décalage pour affectue le bonne alignement
	 */
	public int calcOffset(int containerLength, int elementLength);
	
}
