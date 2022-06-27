package fr.tangv.nestmc.nes;

import com.grapeshot.halfnes.NES;

import fr.tangv.nestmc.draw.Drawable;
import fr.tangv.nestmc.nes.controller.InputController;
import fr.tangv.nestmc.nes.controller.NesController;

public abstract class TMCNes {

	public static final int FIRST_CONTROLLER = 0x01;
	public static final int SECOND_CONTROLLER = 0x02;
	
	private final NES nes;
	private final NesScreenMap screen;
	
	public TMCNes(NesScreenMap screen) {
		this.screen = screen;
		this.nes = new NES(screen);
		nes.setControllers(new NesController(), new NesController());
	}
	
	
	
	/**
	 * Permet d'obtenir les entrées clavier du premier controlleur
	 * @return les entrées clavier du premier controlleur
	 */
	public InputController getFirstController() {
		return (InputController) this.nes.getcontroller1();
	}
	
	/**
	 * Permet d'obtenir les entrées clavier du deuxième controlleur
	 * @return les entrées clavier du deuxième controlleur
	 */
	public InputController getSecondController() {
		return (InputController) this.nes.getcontroller2();
	}
	
	/**
	 * Permet d'obtenir l'écran dessinable de la TMCNes
	 * @return l'écran dessinable de la TMCNes
	 */
	public Drawable getScreen() {
		return this.screen;
	}
	
	/**
	 * Permet de faire fermer des controlleurs
	 * @param controllers le(s) controlleur(s) a faire fermé(s)
	 */
	public abstract void quitController(int controllers);
	
}
