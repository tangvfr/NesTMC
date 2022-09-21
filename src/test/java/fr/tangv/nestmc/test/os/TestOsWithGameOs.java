package fr.tangv.nestmc.test.os;

import fr.tangv.nestmc.nes.software.test.GameTestNesOs;

/**
 * @author Tangv - https://tangv.fr
 * Permet de tester le moved os sur une nes virtuelle
 */
public class TestOsWithGameOs {

	public static void main(String[] args) {
		new TestFakeTMCNes(new GameTestNesOs(), args[0]);
	}
	
}
