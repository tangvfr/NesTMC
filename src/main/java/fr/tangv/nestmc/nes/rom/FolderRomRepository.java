package fr.tangv.nestmc.nes.rom;

import java.io.File;
import java.util.LinkedList;

/**
 * @author Tangv - https://tangv.fr
 * Permet de regrouper différentes roms d'un même dossier
 */
public class FolderRomRepository extends RomRepository {

	/**
	 * Permet de construire un repertoire de rom a partir d'un dossier et de ses sous dossier
	 * @param file le dossier ou son les roms
	 */
	public FolderRomRepository(File file) {
		super(FolderRomRepository.getRoms(file));
	}
	
	/**
	 * Permet de construire un repertoire de rom a partir d'un dossier et de ses sous dossier
	 * @param file le chemin du dossier ou son les roms
	 */
	public FolderRomRepository(String path) {
		this(new File(path));
	}

	/**
	 * Permet d'obtenir la liste des roms dans 
	 * @param folder dossier contenent les roms
	 * @return liste des roms trouvé
	 */
	private static LinkedList<NesRom> getRoms(File folder) {
		if (folder == null || !folder.exists() || !folder.isDirectory()) {
			throw new IllegalArgumentException("Folder is invalid !");
		}
		//init
		LinkedList<NesRom> roms = new LinkedList<NesRom>();
		LinkedList<File> files = new LinkedList<File>();
		File file;
		files.add(folder);
		
		//pour tout les dossier et sous dossier du folder
		while (!files.isEmpty()) {//tant qu'il aura des fichiers a traiter
			file = files.pollFirst();
			
			if (file.isFile()) {//si c'est un fichier
				if (file.getName().endsWith(".nes")) {//si l'extenions du fichier corespond a celle des rom nes
					try {
						roms.offer(new NesRom(file.getAbsolutePath()));
					} catch (Exception e) {
						System.err.println("RomError: " + e.getMessage() + " Path:"+file.getAbsolutePath());
					}
				}
				
			} else if (file.isDirectory()) {//si c'est un dossier
				//pour tout les dossier et fichier du dossier file traitaiter
				for (File f : file.listFiles()) {
					files.offerFirst(f);
				}
			}
		}//files.isEmpty() sortie si plus de fichier a traiter
		
		//trier les roms par ordre alphabetique
		roms.sort((NesRom nr1, NesRom nr2) -> {
			return nr1.getName().compareToIgnoreCase(nr2.getName());
		});
		
		return roms;
	}
	
}
