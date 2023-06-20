/**
 * 
 */
package fr.tangv.nestmc.nes.rom;

import com.grapeshot.halfnes.ROMLoader;
import com.grapeshot.halfnes.mappers.BadMapperException;
import com.grapeshot.halfnes.mappers.Mapper;

import java.io.File;

/**
 * @author Tangv - https://tangv.fr
 * Information sur une rom de nes
 */
public class NesRom {

	/*
	 * nom de la rom
	 */
	private final String name;
	/*
	 * chemin du fichier de la rom
	 */
	private final String path;
	/*
	 * si la rom peux etre sauvegarder
	 */
	private final boolean save;
	
	/**
	 * Permet de construire une information de rom
	 * @param path chemin de la rom
	 * @throws BadMapperException 
	 */
	public NesRom(String path) throws BadMapperException {
		ROMLoader rom = new ROMLoader(path);
		rom.parseHeader();
		Mapper mapper = Mapper.getCorrectMapper(rom);//pour tester que le mapper est compatible
		this.save = rom.savesram;
		mapper.destroy();
		this.path = path;
		this.name = new File(path).getName().replaceAll(".nes", "");
	}

	/**
	 * Permet de de savoir si la rom peux etre sauvegarder ou non
	 * @return true si la rom a une sauvegarde
	 */
	public boolean canSave() {
		return this.save;
	}
	
	/**
	 * Permet de récupérer le nom de la rom
	 * @return le nom de la rom
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Permet de récupérer le chemin de la rom
	 * @return le chemin du fichier de la rom
	 */
	public String getPath() {
		return this.path;
	}
	
}
