/**
 * 
 */
package fr.tangv.nestmc.nes.controller;

import org.apache.commons.lang.Validate;

/**
 * @author Tangv - https://tangv.fr
 * Permet de mixer deux controlleurs, les sortie sont vrais si le premier ou deuxieme controlleur est vrais
 */
public class MixedInputController implements InputController {

	private final InputController first;
	private final InputController second;
	
	public MixedInputController(InputController first, InputController second) {
		Validate.notNull(first, "First controlleur is null !");
		Validate.notNull(second, "First controlleur is null !");
		this.first = first;
		this.second = second;
	}

	@Override
	public boolean isClicked(int button) {
		return this.first.isClicked(button) || this.second.isClicked(button);
	}

	@Override
	public boolean isPress(int button) {
		return this.first.isPress(button) || this.second.isPress(button);
	}

	@Override
	public boolean isConnected() {
		return false;
	}
}
