package fr.tangv.nestmc.nes.software.os.element.align;

/**
 * @author Tangv - https://tangv.fr
 * Permet de faire un alignement en etant aligner au millieu du contenneur
 */
public class CenterAlign implements Align {

	@Override
	public int calcOffset(int containerLength, int elementLength) {
		return (containerLength - elementLength) / 2;
	}
	
}
