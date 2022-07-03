/**
 * 
 */
package fr.tangv.nestmc.nes.software;

import fr.tangv.nestmc.draw.Drawable;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.controller.InputController;

/**
 * @author Tangv - https://tangv.fr
 * Element d'un os de nes pouvent etre dessiner et mise à jour
 */
public interface TMCNesInteractor {

	public void update(TMCNes nes, InputController firstIn, InputController secondIn, InputController mixedIn);
	public void render(TMCNes nes, Drawable draw);
	
}
