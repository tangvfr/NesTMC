package fr.tangv.nestmc.nes.software.os.element.focus;

/**
 * @author Tangv - https://tangv.fr
 * Interface de l'execution de l'action d'un FocusElement par une touche defini
 */
public interface FocusExecution {

	/**
	 * Est executer lorsque l'action d'execution a lieu
	 * @param buttons touche qui on lancé l'action
	 * @param ele element sur le qu'elle est execute l'action
	 */
	public void execute(int buttons, FocusElement ele);
	
}
