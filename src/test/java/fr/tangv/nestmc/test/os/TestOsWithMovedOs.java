package fr.tangv.nestmc.test.os;

import fr.tangv.nestmc.nes.software.test.MovedTestNesOs;

/**
 * @author Tangv - https://tangv.fr
 * Permet de tester le moved os sur une nes virtuelle
 */
public class TestOsWithMovedOs {

	public static void main(String[] args) {
		new TestFakeTMCNes(new MovedTestNesOs(), args[0]);
	}
	
}
