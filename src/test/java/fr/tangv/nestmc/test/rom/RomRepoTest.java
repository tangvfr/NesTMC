package fr.tangv.nestmc.test.rom;

import fr.tangv.nestmc.nes.rom.FolderRomRepository;
import fr.tangv.nestmc.nes.rom.NesRom;

/**
 * @author Tangv - https://tangv.fr
 * permet de tester le repertoire de roms de nes
 */
public class RomRepoTest {

	public static void main(String[] args) {
		System.out.println("List des roms:");
		for (NesRom rom : new FolderRomRepository(args[0])) {
			System.out.println((rom.canSave() ? "  S- " : "  - ") + rom.getName());
		}
	}

}
