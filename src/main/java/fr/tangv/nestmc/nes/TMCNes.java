package fr.tangv.nestmc.nes;

import com.grapeshot.halfnes.NES;

import fr.tangv.nestmc.draw.Drawable;
import fr.tangv.nestmc.nes.controller.InputController;
import fr.tangv.nestmc.nes.controller.NesController;


/**
 * @author tangv
 * une nes utilisable pour son interface d'utilisation
 */
public abstract class TMCNes {

	public static final int FIRST_CONTROLLER = 0x01;
	public static final int SECOND_CONTROLLER = 0x02;

	/*la nes*/
	private final NES nes;
	/*l'écran de la nes*/
	private final NesScreenMap screen;
	
	/**
	 * Permet de crée une nes utilisable pour son interface d'utilisation
	 * @param screen l'écran de la nes
	 */
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
	 * Permet de mettre a jour l'écran, les boutons, et les actions réalisé
	 */
	public abstract void update();
	
	/**
	 * Permet de fermer des controlleurs
	 * @param controllers le(s) controlleur(s) a fermer
	 */
	public abstract void closeController(int controllers);
	
}
