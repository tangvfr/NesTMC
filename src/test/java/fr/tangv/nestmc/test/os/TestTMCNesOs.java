package fr.tangv.nestmc.test.os;

import fr.tangv.nestmc.nes.NesScreen;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.controller.InputController;
import fr.tangv.nestmc.nes.software.NesOs;
import fr.tangv.nestmc.nes.software.os.element.Element;
import fr.tangv.nestmc.nes.software.os.element.border.BasicBorder;

/**
 * @author Tangv - https://tangv.fr
 * Permet de tester les different élément qui permet de construire un os pour nes
 */
public class TestTMCNesOs extends NesOs {

	private static final byte BLACK = (byte) 119;
	private static final byte BACK = (byte) 67;
	private static final byte FRONT = (byte) 64;
	
	private final Element ele1;
	private final Element ele2;
	private final Element ele3;
	
	/**
	 * Permet de construire un os qui test les different element
	 */
	public TestTMCNesOs() {
		//Element 1
		this.ele1 = new Element(8, 8, 32, 32, TestTMCNesOs.BACK) {};

		//Element 2
		this.ele2 = new Element(8, 72, 32, 32, TestTMCNesOs.BACK) {};
		this.ele2.setBorder(new BasicBorder(2, TestTMCNesOs.FRONT));

		//Element 3
		this.ele3 = new Element(8, 136, 32, 32, TestTMCNesOs.BACK) {};
		this.ele3.setBorder(new BasicBorder(8, 4, 0, 2, TestTMCNesOs.FRONT));
	}
	
	@Override
	public void update(TMCNes nes, InputController firstIn, InputController secondIn, InputController mixedIn) {
		this.ele1.update(nes, firstIn, secondIn, mixedIn);
		this.ele2.update(nes, firstIn, secondIn, mixedIn);
		this.ele3.update(nes, firstIn, secondIn, mixedIn);
	}

	@Override
	public void render(TMCNes nes, NesScreen screen) {
		screen.clearScreen(TestTMCNesOs.BLACK);
		this.ele1.render(nes, screen);
		this.ele2.render(nes, screen);
		this.ele3.render(nes, screen);
	}

	@Override
	public void addMessageBox(String msg) {
		System.out.println("msgbox: " + msg);
	}

}
