package fr.tangv.nestmc.test.os;

import fr.tangv.nestmc.nes.software.os.TMCNesOs;

/**
 * @author Tangv - https://tangv.fr
 * Permet de tester l'os de la nes sur une nes virtuelle
 */
public class TestOsWithTMCNesOs {

	public static void main(String[] args) {
		new TestFakeTMCNes(new TMCNesOs(), args[0]);
	}
	
}
