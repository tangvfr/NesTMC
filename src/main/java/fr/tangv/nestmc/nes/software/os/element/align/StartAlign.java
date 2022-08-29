package fr.tangv.nestmc.nes.software.os.element.align;

/**
 * @author Tangv - https://tangv.fr
 * Permet de faire un alignement en etant coller au debut du contenneur
 */
public class StartAlign implements Align {

	@Override
	public int calcOffset(int containerLength, int elementLength) {
		return 0;
	}
	
}
