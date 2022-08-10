package fr.tangv.nestmc.nes.rom;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * @author Tangv - https://tangv.fr
 * Permet de regrouper différentes roms
 */
public abstract class RomRepository implements Iterable<NesRom> {

	/**
	 * List des roms de nes
	 */
	private final Vector<NesRom> roms;
	
	/**
	 * Permet de construire un répertoire de rom nes
	 * @param roms liste de roms nes
	 */
	public RomRepository(List<NesRom> roms) {
		this.roms = new Vector<NesRom>(roms);
	}
	
	/**
	 * Permet de récupéré une rom par son index
	 * @param index index de la rom
	 */
	public NesRom getRom(int index) {
		return this.roms.get(index);
	}

	/**
	 * Permet de savoir le nombre de roms
	 * @return le nombre de roms
	 */
	public int size() {
		return this.roms.size();
	}
	
	@Override
	public Iterator<NesRom> iterator() {
		return this.roms.iterator();
	}
	
}
