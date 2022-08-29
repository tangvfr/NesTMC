/**
 * 
 */
package fr.tangv.nestmc.nes.software.os.tmcos;

import fr.tangv.nestmc.game.McNes;
import fr.tangv.nestmc.nes.NesScreen;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.controller.InputController;
import fr.tangv.nestmc.nes.software.NesOs;
import fr.tangv.nestmc.nes.software.img.TMCImageOs;
import fr.tangv.nestmc.nes.software.os.element.TextElement;
import fr.tangv.nestmc.nes.software.os.element.align.Aligns;
import fr.tangv.nestmc.nes.software.os.element.border.BasicBorder;
import fr.tangv.nestmc.nes.software.os.element.focus.FocusElement;
import fr.tangv.nestmc.nes.software.os.element.panel.PanelElement;
import fr.tangv.nestmc.nes.software.os.element.panel.ViewElement;
import fr.tangv.nestmc.nes.software.os.element.panel.manager.FullerElementManager;

/**
 * @author Tangv - https://tangv.fr
 * Sytème d'exploitation des nes pour permet au joueur d'intergir avec la console
 */
public class TMCNesOs extends NesOs {
	
	private boolean show = false;
	private ViewElement ve;
	private FocusElement close;
	private FocusElement exit;
	private FocusElement list;
	private PanelElement panel;
	
	/**
	 * Permet de construire 
	 */
	public TMCNesOs() {
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
		
		
		
		close = new FocusElement(0, 0, 0, 0, unsel, sel, back, selBack);
		close.setText("Close", text, (byte) 2);
		close.addAction(InputController.SPACE, 
				(int buttons, FocusElement ele, InputController input, TMCNes nes) -> {
					if (ele.isFocus()) {
						show = false;
					}
				}
			);
		
		exit = new FocusElement(0, 0, 0, 0, unsel, sel, back, selBack);
		exit.setText("Exit", text, (byte) 2);
		exit.addAction(InputController.SPACE, 
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
		this.list = new FocusElement(0, 0, 0, 0, unsel, sel, back, selBack);
		//this.list.addAction(InputController., null);a
		//this.list = new PanelElement(0, 0, 0, 0, back, new FullerElementManager(FullerElementManager.HORIZONTAL));
		//add selector nes roms
		this.list.setImage(TMCImageOs.JOYPAD_CONSOLE, (byte) 2);
		
		//panel
		this.panel = new PanelElement(0, 0, 128, 128, back, new FullerElementManager(FullerElementManager.VERTICAL));
		this.panel.setBorder(new BasicBorder(2, border));
		TextElement menu = new TextElement(0, 0, 0, 0, trans, "Menu", border, (byte) 3);
		menu.setHorizontalAlign(Aligns.CENTER);
		menu.setVerticalAlign(Aligns.CENTER);
		this.panel.addElement(menu, FullerElementManager.MAX_SIZE);
		this.panel.addElement(list, FullerElementManager.MAX_SIZE);
		this.panel.addElement(exit, FullerElementManager.MAX_SIZE);
		this.panel.addElement(close, FullerElementManager.MAX_SIZE);
		this.close.setFocus(true);
		
		this.ve.setView(this.panel);
		
		//update all
		this.ve.updateSizeAndPosition();
	}
	
	@Override
	public void addMessageBox(String msg) {
		System.out.println("msgbox: "+msg);
	}
	
	@Override
	public void update(TMCNes nes, InputController firstIn, InputController secondIn, InputController mixedIn) {
		if (mixedIn.isClicked(InputController.SNEAK)) {
			this.show = !this.show;
		}
		
		if (show) {
			this.ve.update(nes, firstIn, secondIn, mixedIn);
			
			if (mixedIn.isClicked(InputController.OPEN_INV)) {
				if (this.list.isFocus()) {
					this.list.setFocus(false);
					this.exit.setFocus(true);
				} else if (this.exit.isFocus()) {
					this.exit.setFocus(false);
					this.close.setFocus(true);
				} else {//close is selcted
					this.close.setFocus(false);
					this.list.setFocus(true);
				}
			}
		}
	}

	@Override
	public void render(TMCNes nes, NesScreen screen) {
		screen.drawNesScreen();
		if (show) {
			this.ve.render(nes, screen);
		}
	}

}
