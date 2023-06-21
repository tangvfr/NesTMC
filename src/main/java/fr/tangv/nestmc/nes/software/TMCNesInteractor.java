/**
 * 
 */
package fr.tangv.nestmc.nes.software;

import fr.tangv.nestmc.nes.NesScreen;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.controller.InputController;

/**
 * @author Tangv - https://tangv.fr
 * Element d'un os de nes pouvent etre dessiner et mise à jour
 */
public interface TMCNesInteractor extends TMCNesRendered {

	/**
	 * Est appelé lorsque doit être mis à jour (calcule)
	 * @param nes la nes sur laquelle on est executé
	 * @param firstIn premier controlleur de la nes
	 * @param secondIn deuxième controlleur de la nes
	 * @param mixedIn mixte des deux controlleurs de la nes
	 */
	public void update(TMCNes nes, InputController firstIn, InputController secondIn, InputController mixedIn);
	
}
