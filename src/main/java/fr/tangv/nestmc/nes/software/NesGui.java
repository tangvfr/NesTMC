package fr.tangv.nestmc.nes.software;

import fr.tangv.nestmc.draw.MapBuffer;
import fr.tangv.nestmc.nes.NesScreenMap;

/**
 * @author Tangv - https://tangv.fr
 * ecran et interface permet des retour d'information d'une console
 */
public class NesGui extends NesScreenMap {

	/*
	 * permet d'afficher des message
	 */
	private final MessagebleOs msgOs;
	/*
	 * dossier ou sont les sauvegarde des mapper sauvegardable de la nes
	 * */
	private final String saveFolder;
	
	
	/**
	 * Permet de construire un interface par lequelle la nes peux intragir pour renvoyer des information
	 * @param msgOs afficheur de message
	 * @param bitScreens l'écran
	 * @param colors la palette de couelru a utiliser
	 * @param saveFolder le dossier ou seront stocker les sauvegarde des rom
	 */
	public NesGui(MessagebleOs msgOs, MapBuffer[] bitScreens, byte[] colors, String saveFolder) {
		super(bitScreens, colors);
		this.msgOs = msgOs;
		this.saveFolder = saveFolder;
	}

	@Override
	public void loadROMs(String rom) {
		//never called method for tmc
	}

	@Override
	public void messageBox(String msg) {
		this.msgOs.addMessageBox(msg);
	}

	@Override
	public void render() {
		//never called method for tmc
	}

	@Override
	public void run() {
		//when nes is create
	}

	@Override
	public String getSavePath(String curRomPath, String curRomName) {
		return this.saveFolder + curRomName + ".sav";
	}
	
}
