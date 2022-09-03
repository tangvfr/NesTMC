package fr.tangv.nestmc.nes.software.os.element.align;

/**
 * @author Tangv - https://tangv.fr
 * Different aligneur
 */
public abstract class Aligns {

	public static final Align START = new StartAlign();
	public static final Align CENTER = new CenterAlign();
	public static final Align END = new EndAlign();
	
}
