/**
 * 
 */
package fr.tangv.nestmc.nes.software.os.tmcos;

import fr.tangv.nestmc.game.McNes;
import fr.tangv.nestmc.nes.NesScreen;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.controller.InputController;
import fr.tangv.nestmc.nes.rom.RomRepository;
import fr.tangv.nestmc.nes.software.NesOs;
import fr.tangv.nestmc.nes.software.os.element.TextElement;
import fr.tangv.nestmc.nes.software.os.element.align.Aligns;
import fr.tangv.nestmc.nes.software.os.element.border.BasicBorder;
import fr.tangv.nestmc.nes.software.os.element.focus.FocusElement;
import fr.tangv.nestmc.nes.software.os.element.panel.PanelElement;
import fr.tangv.nestmc.nes.software.os.element.panel.ViewElement;
import fr.tangv.nestmc.nes.software.os.element.panel.manager.FullerElementManager;
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
	
	/**
	 * Permet de construire 
	 */
	public TMCNesOs(RomRepository repo) {
		//color
		byte text = (byte) 58;
		byte border = (byte) 66;
		byte back = (byte) 67;
		byte unsel = (byte) 64;
		byte sel = text;
		byte selBack = unsel;
		byte trans = (byte) 0;
		
		this.ve = new ViewElement(0, 0, 256, 256, trans);
		this.ve.setHorizontalAlign(Aligns.CENTER);
		this.ve.setVerticalAlign(Aligns.CENTER);
		
		shutdown = new FocusElement(0, 0, 0, 0, unsel, sel, back, selBack);
		shutdown.setText("Shutdown", (byte) 0, (byte) 2);
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
		
		close = new FocusElement(0, 0, 0, 0, unsel, sel, back, selBack);
		close.setText("Close", text, (byte) 2);
		close.addAction(InputController.OPEN_INV, 
				(int buttons, FocusElement ele, InputController input, TMCNes nes) -> {
					if (ele.isFocus()) {
						TMCNesOs.this.hide(nes);
					}
				}
			);
		
		exit = new FocusElement(0, 0, 0, 0, unsel, sel, back, selBack);
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
		this.sel = new RomSelectorElement(this, repo, text, unsel, sel, back, selBack);
		//this.list.setImage(TMCImageOs.JOYPAD_CONSOLE, (byte) 2);
		
		//panel
		this.panel = new PanelElement(0, 0, 128, 128, back, new FullerElementManager(FullerElementManager.VERTICAL));
		this.panel.setBorder(new BasicBorder(2, border));
		TextElement menu = new TextElement(0, 0, 0, 0, trans, "Menu", border, (byte) 3);
		menu.setHorizontalAlign(Aligns.CENTER);
		menu.setVerticalAlign(Aligns.CENTER);
		this.panel.addElement(menu, FullerElementManager.MAX_SIZE);
		this.panel.addElement(this.sel, FullerElementManager.MAX_SIZE);
		this.panel.addElement(this.shutdown, FullerElementManager.MAX_SIZE);
		this.panel.addElement(this.exit, FullerElementManager.MAX_SIZE);
		this.panel.addElement(this.close, FullerElementManager.MAX_SIZE);
		this.close.setFocus(true);
		
		this.ve.setView(this.panel);
		
		//update all
		this.ve.updateSizeAndPosition();
	}
	
	public boolean isShow() {
		return this.show;
	}
	
	public void show(TMCNes nes) {
		this.show = true;
		((TextElement) this.shutdown.getView()).setTextColor(nes.isStart() ? (byte) 18 : (byte) 0);
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
		
		if (show) {
			this.ve.update(nes, firstIn, secondIn, mixedIn);
			
			if (mixedIn.isClicked(InputController.UP) || mixedIn.isClicked(InputController.DOWN)) {
				if (this.sel.isFocus()) {
					this.sel.setFocus(false);
					this.shutdown.setFocus(true);
				} else if (this.shutdown.isFocus()) {
					this.shutdown.setFocus(false);
					this.exit.setFocus(true);
				} else if (this.exit.isFocus()) {
					this.exit.setFocus(false);
					this.close.setFocus(true);
				} else {//close is selcted
					this.close.setFocus(false);
					this.sel.setFocus(true);
				}
			}
		}
	}

	@Override
	public void render(TMCNes nes, NesScreen screen) {
		if (nes.isStart()) {
			screen.drawNesScreen();
			screen.setColor(MapColorV1_8.GREEN_LIGTH);
		} else {
			screen.clearScreen((byte) 119);
			screen.setColor(MapColorV1_8.RED_LIGTH);
		}
		screen.fillRect(4, 244, 248, 8);
	
		if (show) {
			this.ve.render(nes, screen);
		}
		
	}

}
