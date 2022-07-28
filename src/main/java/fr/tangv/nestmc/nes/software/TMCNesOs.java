/**
 * 
 */
package fr.tangv.nestmc.nes.software;

import fr.tangv.nestmc.draw.Drawable;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.controller.InputController;

/**
 * @author Tangv - https://tangv.fr
 *
 */
public class TMCNesOs extends NesOs {

	@Override
	public void update(TMCNes nes, InputController firstIn, InputController secondIn, InputController mixedIn) {
		// TODO Auto-generated method stub
		//when player exit think to destruct controller
	}

	@Override
	public void render(TMCNes nes, Drawable draw) {
		
		this.setSend(true);
	}

}
