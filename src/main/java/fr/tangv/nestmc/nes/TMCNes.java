package fr.tangv.nestmc.nes;

import com.grapeshot.halfnes.NES;
import fr.tangv.nestmc.nes.controller.InputController;
import fr.tangv.nestmc.nes.controller.MixedInputController;
import fr.tangv.nestmc.nes.controller.NesController;
import fr.tangv.nestmc.nes.rom.NesRom;
import fr.tangv.nestmc.nes.software.NesOs;
import org.apache.commons.lang.Validate;


/**
 * @author Tangv - https://tangv.fr
 * une nes utilisable pour son interface d'utilisation
 */
public abstract class TMCNes {

	public static final int FIRST_CONTROLLER = 0x01;
	public static final int SECOND_CONTROLLER = 0x02;
	public static final int QUIT = 0x04;

	/*l'écran de la nes*/
	private final NesScreenMap screen;
	/*le premier controlleur de la console*/
	private final NesController first;
	/*le deuxieme partager de la console*/
	private final NesController second;
	/*le controlleur partager*/
	private final MixedInputController mixed;
	/*OS de la nes*/
	private final NesOs os;
	/*la console nes associer*/
	private NES nes = null;
	/*thread de l'emulateur peu etre null*/
	private Thread thread;
	
	/**
	 * Permet de crée une nes utilisable pour son interface d'utilisation
	 * @param screen l'écran de la nes
	 * @param os système d'explotation de la console
	 */
	public TMCNes(NesScreenMap screen, NesOs os) {
		this.screen = screen;
		this.first = new NesController();
		this.second = new NesController();
		this.mixed = new MixedInputController(first, second);
		this.os = os;
	}
	
	/**
	 * Permet nom de la ROM émuler
	 * @return le nom de la ROM émuler
	 */
	public synchronized String getCurrentRomName() {
		Validate.notNull(this.nes, "Aucune console nes n'est associé !");
		return this.nes.getCurrentRomName();
	}
	
	/**
	 * Permet de savoir si le mapper sera sauvegarder ou non
	 * @return true si le mapper et sa SRAM est sauvegarder
	 */
	public synchronized boolean currentMapperCanSave() {
		Validate.notNull(this.nes, "Aucune console nes n'est associé !");
		return this.nes.mapper.supportsSaves();
	}
	
	/**
	 * Permet de savoir si la console est entrain d'emuler ou non
	 * @return true si la console est entrain d'emuler (fonctionner)
	 */
	public synchronized boolean isRunning() {
		Validate.notNull(this.nes, "Aucune console nes n'est associé !");
		return this.nes.runEmulation;
	}
	
	/*
	 * Permet de mettre en pause la console
	 */
	public synchronized void pause() {
		Validate.notNull(this.nes, "Aucune console nes n'est associé !");
		this.nes.pause();
	}
	
	/**
	 * Permet de reprendre apres une pause de la console
	 */
	public synchronized void resume() {
		Validate.notNull(this.nes, "Aucune console nes n'est associé !");
		this.nes.resume();
	}
	
	/**
	 * Permet de simuler l'appuie du bouton reset de la console
	 */
	public synchronized void reset() {
		Validate.notNull(this.nes, "Aucune console nes n'est associé !");
		this.nes.reset();
	}
	
	/**
	 * Permet de recharger la ROM (hardreset)
	 */
	public synchronized void reloadROM() {
		Validate.notNull(this.nes, "Aucune console nes n'est associé !");
		this.nes.reloadROM();
	}
	
	/**
	 * Permet de charger une ROM
	 * @param rom la rom a charger
	 */
	public synchronized void loadROM(NesRom rom) {
		Validate.notNull(this.nes, "Aucune console nes n'est associé !");
		this.nes.loadROM(rom.getPath());
	}
	
	/**
	 * Permet d'initialiser et d'associer une console nes a l'interface de gestion de la nes
	 * @param rom rom de jeu a charger pour démmaré la nes
	 */
	public synchronized void start(NesRom rom) {
		Validate.isTrue(this.nes == null, "Une console nes est déja associé !");
		this.nes = new NES(screen);
		this.nes.setControllers(first, second);
		this.nes.loadROM(rom.getPath());
		//this.thread = new Thread(this.nes::run);
		this.thread = new Thread(new Runnable() {
			@Override
			public void run() {
				TMCNes.this.nes.run();
			}
		});
		this.thread.start();//start nes
	}
	
	/**
	 * Permet d'arrêter et de désassocier la console nes de l'interface de gestion de la nes
	 */
	public synchronized void stop() {
		Validate.notNull(this.nes, "Aucune console nes n'est associé !");
		this.nes.quit();
		this.nes = null;
	}
	
	/**
	 * Permet de savoir si la nes est démaré
	 * @return true si la nes est démaré
	 */
	public synchronized boolean isStart() {
		return this.nes != null;
	}
	
	/**
	 * Permet d'obtenir les entrées clavier du premier controlleur
	 * @return les entrées clavier du premier controlleur
	 */
	public InputController getFirstController() {
		return this.first;
	}
	
	/**
	 * Permet d'obtenir les entrées clavier du deuxième controlleur
	 * @return les entrées clavier du deuxième controlleur
	 */
	public InputController getSecondController() {
		return this.second;
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
	public NesScreen getScreen() {
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
