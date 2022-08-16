package fr.tangv.nestmc.test.os;

import fr.tangv.nestmc.nes.NesScreen;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.controller.InputController;
import fr.tangv.nestmc.nes.software.NesOs;
import fr.tangv.nestmc.nes.software.img.TMCImageOs;
import fr.tangv.nestmc.nes.software.os.element.Element;
import fr.tangv.nestmc.nes.software.os.element.ImageElement;
import fr.tangv.nestmc.nes.software.os.element.TextElement;
import fr.tangv.nestmc.nes.software.os.element.align.Align;
import fr.tangv.nestmc.nes.software.os.element.border.BasicBorder;
import fr.tangv.nestmc.nes.software.os.element.border.CornerBasicBorder;
import fr.tangv.nestmc.nes.software.os.element.panel.PanelElement;
import fr.tangv.nestmc.nes.software.os.element.panel.ViewElement;
import fr.tangv.nestmc.nes.software.os.element.panel.manager.DiviserElementManager;
import fr.tangv.nestmc.nes.software.os.element.panel.manager.FullerElementManager;

/**
 * @author Tangv - https://tangv.fr
 * Permet de tester les different élément qui permet de construire un os pour nes
 */
public class TestTMCNesOs extends NesOs {

	private static final byte BLACK = (byte) 119;
	private static final byte BACK = (byte) 67;
	private static final byte FRONT = (byte) 64;
	private static final byte WHITE = (byte) 34;
	private static final byte TRANS = (byte) 0;
	
	private final Element ele1;
	private final Element ele2;
	private final Element ele3;
	private final Element ele4;
	private final Element ele5;
	private final TextElement text1;
	private final TextElement text2;
	private final TextElement text3;
	private final TextElement text4;
	private final ImageElement img1;
	private final ImageElement img2;
	private final ImageElement img3;
	private final ImageElement img4;
	private final ImageElement img5;
	private final PanelElement pan;
	private final PanelElement pe;
	private final PanelElement pe2;
	
	private int peEnable;
	
	/**
	 * Permet de construire un os qui test les different element
	 */
	public TestTMCNesOs() {
		this.peEnable = 0;
		
		//Element 1
		this.ele1 = new Element(8, 8, 32, 32, TestTMCNesOs.BACK) {};

		//Element 2
		this.ele2 = new Element(8, 72, 32, 32, TestTMCNesOs.BACK) {};
		this.ele2.setBorder(new BasicBorder(2, TestTMCNesOs.FRONT));

		//Element 3
		this.ele3 = new Element(8, 136, 32, 32, TestTMCNesOs.BACK) {};
		this.ele3.setBorder(new BasicBorder(8, 4, 0, 2, TestTMCNesOs.FRONT));
		
		//Element 4
		this.ele4 = new Element(8, 190, 16, 16, TestTMCNesOs.BACK) {};
		this.ele4.setBorder(new CornerBasicBorder(8, 1, 6, 2, TestTMCNesOs.FRONT, TestTMCNesOs.WHITE));

		//Element 5
		this.ele5 = new Element(8, 220, 16, 16, TestTMCNesOs.BACK) {};
		this.ele5.setBorder(new CornerBasicBorder(3, TestTMCNesOs.FRONT, TestTMCNesOs.WHITE));
		
		//Text 1
		this.text1 = new TextElement(48, 8, 48, 7, TestTMCNesOs.BACK, "Test !", WHITE);

		//Text 2
		this.text2 = new TextElement(48, 32, 48, 16, TestTMCNesOs.BACK, "Center", WHITE);
		this.text2.setHorizontalAlign(Align.CENTER);
		this.text2.setVerticalAlign(Align.CENTER);
		this.text2.setBorder(new BasicBorder(1, TestTMCNesOs.FRONT));
		
		//Text 3
		this.text3 = new TextElement(48, 56, 32, 20, TestTMCNesOs.BACK, "END", WHITE);
		this.text3.setHorizontalAlign(Align.END);
		this.text3.setTextCof((byte) 2);

		//Text 4
		this.text4 = new TextElement(48, 80, 40, 16, TestTMCNesOs.BACK, "HUGE22334455667788", WHITE);
		this.text4.setHorizontalAlign(Align.END);
		this.text4.setVerticalAlign(Align.END);
		
		//Image 1
		this.img1 = new ImageElement(100, 8, 17, 16, TestTMCNesOs.BACK, TMCImageOs.JOYPAD_CONSOLE);
		
		//Image 2
		this.img2 = new ImageElement(100, 40, 40, 32, TestTMCNesOs.TRANS, TMCImageOs.JOYPAD_CONSOLE);
		this.img2.setVerticalAlign(Align.CENTER);
		this.img2.setHorizontalAlign(Align.CENTER);
		this.img2.setImgCof((byte) 2);
		this.img2.setBorder(new BasicBorder(1, TestTMCNesOs.FRONT));
		
		//Image 3
		this.img3 = new ImageElement(100, 80, 40, 32, TestTMCNesOs.BACK, TMCImageOs.JOYPAD_CONSOLE);
		this.img3.setVerticalAlign(Align.END);
		this.img3.setHorizontalAlign(Align.CENTER);

		//Image 4
		this.img4 = new ImageElement(100, 128, 40, 32, TestTMCNesOs.BACK, null);
		this.img4.setBorder(new BasicBorder(1, TestTMCNesOs.WHITE));
		
		//Image 5
		this.img5 = new ImageElement(100, 170, 16, 16, TestTMCNesOs.BACK, TMCImageOs.JOYPAD_CONSOLE);
		this.img5.setBorder(new BasicBorder(1, TestTMCNesOs.FRONT));
		
		//panel
		this.pan = new PanelElement(0, 0, 256, 256, TestTMCNesOs.BLACK);
		this.pan.addElement(this.ele1);
		this.pan.addElement(this.ele2);
		this.pan.addElement(this.ele3);
		this.pan.addElement(this.ele4);
		this.pan.addElement(this.ele5);
		this.pan.addElement(this.text1);
		this.pan.addElement(this.text2);
		this.pan.addElement(this.text3);
		this.pan.addElement(this.text4);
		this.pan.addElement(this.img1);
		this.pan.addElement(this.img2);
		this.pan.addElement(this.img3);
		this.pan.addElement(this.img4);
		this.pan.addElement(this.img5);
		
		//test center
		{
			this.pe = new PanelElement(0, 0, 160, 128, (byte) 63, new FullerElementManager(FullerElementManager.VERTICAL));
			ViewElement ve = new ViewElement(0, 0, 256, 256, TestTMCNesOs.TRANS);
			ve.setHorizontalAlign(Align.CENTER);
			ve.setVerticalAlign(Align.CENTER);
			ve.setView(this.pe);
					
			TextElement te = new TextElement(0, 0, 30, 9, (byte) 83, "Test", (byte) 82);
			te.setHorizontalAlign(Align.CENTER);
			te.setVerticalAlign(Align.CENTER);
			te.setBorder(new BasicBorder(1, TestTMCNesOs.WHITE));
			
			TextElement t2 = new TextElement(0, 0, 50, 7, (byte) 86, "Second", (byte) 82);
			t2.setBorder(new CornerBasicBorder(1, 2, 3, 6, (byte) 102, TRANS));
			TextElement t3 = new TextElement(0, 0, 20, 7, (byte) 87, "Three", (byte) 82);
			
			//test panel
			this.pe.addElement(te, FullerElementManager.MIN_SIZE);
			this.pe.addElement(t2, FullerElementManager.MAX_SIZE);
			this.pe.addElement(t3, FullerElementManager.MIN_SIZE);
		}
		
		//test element diviser 1
		{
			this.pe2 = new PanelElement(0, 0, 128, 128, (byte) 79, new DiviserElementManager(DiviserElementManager.VERTICAL, 2));
			ViewElement ve = new ViewElement(0, 0, 256, 256, TestTMCNesOs.TRANS);
			ve.setHorizontalAlign(Align.END);
			ve.setVerticalAlign(Align.CENTER);
			ve.setView(this.pe2);
			
			TextElement te = new TextElement(0, 0, 30, 9, (byte) 83, "Test", (byte) 82);
			te.setHorizontalAlign(Align.CENTER);
			te.setVerticalAlign(Align.CENTER);
			te.setBorder(new BasicBorder(1, TestTMCNesOs.WHITE));
			
			TextElement t2 = new TextElement(0, 0, 50, 7, (byte) 86, "Second", (byte) 82);
			t2.setBorder(new CornerBasicBorder(1, 2, 3, 6, (byte) 102, TRANS));
			TextElement t3 = new TextElement(0, 0, 20, 7, (byte) 87, "Three", (byte) 82);
			
			//test panel
			this.pe2.addElement(te);
			this.pe2.addElement(t2);
			this.pe2.addElement(t3);
		}
	}
	
	@Override
	public void update(TMCNes nes, InputController firstIn, InputController secondIn, InputController mixedIn) {
		if (mixedIn.isClicked(InputController.HEALD_1)) {
			this.peEnable++;
			
			if (this.peEnable > 2) {
				this.peEnable = 0;
			}
		}
		
		this.pan.update(nes, firstIn, secondIn, mixedIn);
		if (this.peEnable == 1) {
			this.pe.update(nes, firstIn, secondIn, mixedIn);
		}
		if (this.peEnable == 2) {
			this.pe2.update(nes, firstIn, secondIn, mixedIn);
		}
	}

	@Override
	public void render(TMCNes nes, NesScreen screen) {
		this.pan.render(nes, screen);
		this.pan.render(nes, screen);
		if (this.peEnable == 1) {
			this.pe.render(nes, screen);
		}
		if (this.peEnable == 2) {
			this.pe2.render(nes, screen);
		}
	}

	@Override
	public void addMessageBox(String msg) {
		System.out.println("msgbox: " + msg);
	}

}
