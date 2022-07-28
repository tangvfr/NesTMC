package fr.tangv.nestmc.nes;

import com.grapeshot.halfnes.NES;

import fr.tangv.nestmc.draw.Drawable;
import fr.tangv.nestmc.nes.controller.InputController;
import fr.tangv.nestmc.nes.controller.MixedInputController;
import fr.tangv.nestmc.nes.controller.NesController;
import fr.tangv.nestmc.nes.software.NesOs;


/**
 * @author Tangv - https://tangv.fr
 * une nes utilisable pour son interface d'utilisation
 */
public abstract class TMCNes {

	public static final int FIRST_CONTROLLER = 0x01;
	public static final int SECOND_CONTROLLER = 0x02;

	/*l'écran de la nes*/
	private final NesScreenMap screen;
	/*le controlleur partager*/
	private final MixedInputController mixed;
	/*la nes*/
	private final NES nes;
	/*OS de la nes*/
	private final NesOs os;
	
	/**
	 * Permet de crée une nes utilisable pour son interface d'utilisation
	 * @param screen l'écran de la nes
	 */
	public TMCNes(NesScreenMap screen, NesOs os) {
		NesController first = new NesController();
		NesController second = new NesController();
		this.screen = screen;
		this.nes = new NES(screen);
		this.nes.setControllers(first, second);
		this.mixed = new MixedInputController(first, second);
		this.os = os;
		
		//make methodes
		//nes.runEmulation;
		//nes.getCurrentRomName();
		//nes.get
		
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
	 * Permet d'obtenir les entrées clavier des deux controlleur
	 * @return les entrées clavier des deux controlleurs
	 */
	public InputController getMixedController() {
		return this.mixed;
	}
	
	/**
	 * Permet d'obtenir l'écran dessinable de la TMCNes
	 * @return l'écran dessinable de la TMCNes
	 */
	public Drawable getScreen() {
		return this.screen;
	}
	
	/**
	 * Permet de récupérer l'os de la TMCNes
	 * @return l'os de la TMCNes
	 */
	public NesOs getOs() {
		return this.os;
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
