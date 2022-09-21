package fr.tangv.nestmc.nes.software.os.element.align;

/**
 * @author Tangv - https://tangv.fr
 * Permet de faire un alignement en etant à la fin du contenneur
 */
public class EndAlign implements Align {

	@Override
	public int calcOffset(int containerLength, int elementLength) {
		return (containerLength - elementLength);
	}
	
}
