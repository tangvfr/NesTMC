/**
 * 
 */
package fr.tangv.nestmc.nes.software.os.tmcos;

import fr.tangv.nestmc.draw.DrawableImage;
import fr.tangv.nestmc.game.McNes;
import fr.tangv.nestmc.nes.NesScreen;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.controller.InputController;
import fr.tangv.nestmc.nes.rom.RomRepository;
import fr.tangv.nestmc.nes.software.NesOs;
import fr.tangv.nestmc.nes.software.img.TMCImageOs;
import fr.tangv.nestmc.nes.software.os.color.FocusColors;
import fr.tangv.nestmc.nes.software.os.element.ImageElement;
import fr.tangv.nestmc.nes.software.os.element.align.Aligns;
import fr.tangv.nestmc.nes.software.os.element.border.BasicBorder;
import fr.tangv.nestmc.nes.software.os.element.border.RoundBorder;
import fr.tangv.nestmc.nes.software.os.element.focus.FocusElement;
import fr.tangv.nestmc.nes.software.os.element.focus.FocusSelector;
import fr.tangv.nestmc.nes.software.os.element.panel.MarginElement;
import fr.tangv.nestmc.nes.software.os.element.panel.PanelElement;
import fr.tangv.nestmc.nes.software.os.element.panel.PanelElementBuilder;
import fr.tangv.nestmc.nes.software.os.element.panel.ViewElement;
import fr.tangv.nestmc.nes.software.os.element.panel.manager.FullerElementManager;
import fr.tangv.nestmc.nes.software.os.element.panel.manager.OneElementManager;
import fr.tangv.nestmc.nes.software.os.element.text.TextElement;
import fr.tangv.nestmc.palette.v1_8.MapColorV1_8;

/**
 * @author Tangv - https://tangv.fr
 * Sytème d'exploitation des nes pour permet au joueur d'intergir avec la console
 */
public class TMCNesOs extends NesOs {
	
	private boolean show = false;
	private ViewElement ve;
	private FocusElement shutdown;
	private FocusElement close;
	private FocusElement exit;
	private RomSelectorElement sel;
	private PanelElement panel;
	private FocusSelector focus;
	private NesInfoRender nesInfoRender;

	/**
	 * Permet de construire 
	 */
	public TMCNesOs(RomRepository repo) {
		//info nes
		this.nesInfoRender = new NesInfoRender(
				MapColorV1_8.LAVA_LIGTH,
				MapColorV1_8.NETHER_BRICK_LIGTH,
				(byte) 119,
				(byte) 87,
				(byte) 85
		);
		//color
		byte text = (byte) 58;
		byte border = (byte) 66;
		byte back = (byte) 67;
		byte unsel = (byte) 64;
		byte sel = text;
		byte selBack = unsel;
		byte trans = (byte) 0;
		FocusColors focusColors = new FocusColors(unsel, sel, back, selBack);
		int round = 6;

		this.ve = new ViewElement(0, 0, NesScreen.WIDTH, NesScreen.SCREEN_HEIGHT, trans);
		this.ve.setHorizontalAlign(Aligns.CENTER);
		this.ve.setVerticalAlign(Aligns.CENTER);
		
		shutdown = new FocusElement(focusColors, round);
		shutdown.setText("Shutdown", trans, (byte) 2);
		shutdown.addAction(InputController.OPEN_INV, 
				(int buttons, FocusElement ele, InputController input, TMCNes nes) -> {
					if (ele.isFocus()) {
						if (nes.isStart()) {
							nes.stop();
							this.hide(nes);
						}
					}
				}
			);
		
		close = new FocusElement(focusColors, round);
		close.setText("Resume", text, (byte) 2);
		close.addAction(InputController.OPEN_INV, 
				(int buttons, FocusElement ele, InputController input, TMCNes nes) -> {
					if (ele.isFocus()) {
						TMCNesOs.this.hide(nes);
					}
				}
			);
		
		exit = new FocusElement(focusColors, round);
		exit.setText("Exit", text, (byte) 2);
		exit.addAction(InputController.OPEN_INV, 
				(int buttons, FocusElement ele, InputController input, TMCNes nes) -> {
					if (ele.isFocus()) {
						if (nes instanceof McNes<?>) {
							((McNes<?>) nes).closeController(McNes.FIRST_CONTROLLER + McNes.SECOND_CONTROLLER);
						} else {
							System.exit(0);
						}
					}
				}
			);
		
		//liste
		this.sel = new RomSelectorElement(this, repo, text, unsel, sel, back, selBack, round);
		//this.list.setImage(TMCImageOs.JOYPAD_CONSOLE, (byte) 2);
		
		//panel
		this.panel = new PanelElement(0, 0, NesScreen.WIDTH, NesScreen.SCREEN_HEIGHT, trans, new FullerElementManager(FullerElementManager.VERTICAL));
		TextElement menu = new TextElement(0, 0, 0, 0, trans, "Menu", (byte) 25, (byte) 3);
		menu.setHorizontalAlign(Aligns.END);
		menu.setVerticalAlign(Aligns.CENTER);
		ImageElement img = new ImageElement(TMCImageOs.JOYPAD_CONSOLE, trans);
		img.setImgCofAndSize((byte) 2, 0, 0);
		this.panel.addElement(PanelElementBuilder.createLeft(img, menu, trans, 0, 8, 0, null, 0, 0), FullerElementManager.MAX_SIZE);
		this.panel.addElement(new MarginElement(this.sel, 2, 0, 0, 0), FullerElementManager.MAX_SIZE);
		this.panel.addElement(new MarginElement(this.shutdown, 2, 0, 0, 0), FullerElementManager.MAX_SIZE);
		this.panel.addElement(new MarginElement(this.exit, 2, 0, 0, 0), FullerElementManager.MAX_SIZE);
		this.panel.addElement(new MarginElement(this.close, 2, 0, 0, 0), FullerElementManager.MAX_SIZE);
		

		PanelElement window = new PanelElement(0, 0, 128, 128, (byte) 99, new OneElementManager());
		window.setRound(round);
		window.setBorder(new RoundBorder(2, border, round + 2));
		window.addElement(new MarginElement(this.panel, 2));
		
		this.ve.setView(window);
		//focus
		this.focus = new FocusSelector();
		this.focus.addFocus(this.sel);
		this.focus.addFocus(this.shutdown);
		this.focus.addFocus(this.exit);
		this.focus.addFocus(this.close);
		
		//update all
		this.ve.updateSizeAndPosition();
	}
	
	public RomSelectorElement getSelector() {
		return this.sel;
	}
	
	public boolean isShow() {
		return this.show;
	}
	
	public void show(TMCNes nes) {
		this.show = true;

		//shutdown add and remove
		((TextElement) this.shutdown.getView()).setTextColor(nes.isStart() ? (byte) 18 : (byte) 46);
		
		if (nes.isStart() && nes.isRunning()) {
			nes.pause();
		}
	}
	
	public void hide(TMCNes nes) {
		this.show = false;
		if (nes.isStart() && !nes.isRunning()) {
			nes.resume();
		}
	}
	
	@Override
	public void addMessageBox(String msg) {
		System.out.println("msgbox: "+msg);
	}
	
	@Override
	public void update(TMCNes nes, InputController firstIn, InputController secondIn, InputController mixedIn) {
		if (mixedIn.isClicked(InputController.SNEAK)) {
			if (this.isShow()) {
				this.hide(nes);
			} else {//isHide
				this.show(nes);
			}
		}
		
		if (this.show) {
			this.ve.update(nes, firstIn, secondIn, mixedIn);
			
			if (mixedIn.isClicked(InputController.UP)) {
				this.focus.back();
			}
			if (mixedIn.isClicked(InputController.DOWN)) {
				this.focus.next();
			}
		}
		
		//focred exit
		if (firstIn.isClicked(InputController.HEALD_8) && firstIn.isPress(InputController.SPACE)) {
			nes.closeController(McNes.FIRST_CONTROLLER);
		}
		if (secondIn.isClicked(InputController.HEALD_8) && secondIn.isPress(InputController.SPACE)) {
			nes.closeController(McNes.SECOND_CONTROLLER);
		}
	}

	@Override
	public void render(TMCNes nes, NesScreen screen) {
		//draw screen nes
		this.nesInfoRender.render(nes, screen);

		//draw menu
		if (show) {
			this.ve.render(nes, screen);
		}
	}

}
