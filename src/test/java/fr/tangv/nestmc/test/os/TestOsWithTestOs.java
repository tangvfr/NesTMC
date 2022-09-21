package fr.tangv.nestmc.test.os;

/**
 * @author Tangv - https://tangv.fr
 * Permet de tester le test os sur une nes virtuelle
 */
public class TestOsWithTestOs {

	public static void main(String[] args) {
		new TestFakeTMCNes(new TestTMCNesOs(), args[0]);
	}
	
}
