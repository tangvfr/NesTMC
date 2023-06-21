/**
 * 
 */
package fr.tangv.nestmc.nes.software;

import fr.tangv.nestmc.nes.NesScreen;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.controller.InputController;

/**
 * @author Tangv - https://tangv.fr
 * Element d'un os de nes pouvent etre dessiner
 */
public interface TMCNesRendered {

	/**
	 * Est appelé lorsque que l'on doit être dessiné
	 * @param nes la nes sur laquelle on est executé
	 * @param screen l'écran de la nes sur le quelle on peut dessiner
	 */
	public void render(TMCNes nes, NesScreen screen);
	
}
