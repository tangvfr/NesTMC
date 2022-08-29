package fr.tangv.nestmc.nes.software.os.tmcos;

import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.controller.InputController;
import fr.tangv.nestmc.nes.rom.NesRom;
import fr.tangv.nestmc.nes.rom.RomRepository;
import fr.tangv.nestmc.nes.software.os.element.TextElement;
import fr.tangv.nestmc.nes.software.os.element.align.Aligns;
import fr.tangv.nestmc.nes.software.os.element.focus.FocusElement;

/**
 * @author Tangv - https://tangv.fr
 *
 */
public class RomSelectorElement extends FocusElement {

	private final TMCNesOs os;
	private final RomRepository repo;
	private final TextElement text;
	private int selectedRomId = 0;

	public RomSelectorElement(TMCNesOs os, RomRepository repo, byte textColor, byte unfocusBorderColor, byte focusBorderColor,
			byte unfocusBackgroundColor, byte focusBackgroundColor) {
		super(0, 0, 0, 0, unfocusBorderColor, focusBorderColor, unfocusBackgroundColor, focusBackgroundColor);
		this.repo = repo;
		this.os = os;
		//text
		this.text = new TextElement(0, 0, 0, 0, (byte) 0, "", textColor);
		this.text.setVerticalAlign(Aligns.CENTER);
		this.text.setHorizontalAlign(Aligns.CENTER);
		this.setView(this.text);
		this.showRom();
	}
	
	public void showRom() {
		if (this.repo.isEmpty()) {
			text.setText("[None]");
		} else {
			NesRom rom = this.repo.getRom(this.selectedRomId);
			String str = rom.canSave() ? "[S] " : "[N] ";
			str += rom.getName();
			text.setText(str);
		}
	}
	
	public void playRom(TMCNes nes) {
		NesRom rom = this.repo.getRom(this.selectedRomId);
		
		if (nes.isStart()) {
			nes.loadROM(rom);
		} else {
			nes.start(rom);
		}
		
		this.os.hide(nes);
	}

	@Override
	public void update(TMCNes nes, InputController firstIn, InputController secondIn, InputController mixedIn) {
		super.update(nes, firstIn, secondIn, mixedIn);

		if (this.isFocus()) {
			if (mixedIn.isClicked(InputController.OPEN_INV)) {
				this.playRom(nes);
			} else {
				int ch = 0;
				if (mixedIn.isClicked(InputController.HEALD_4) || mixedIn.isClicked(InputController.LEFT)) {
					ch = -1;
				} else if (mixedIn.isClicked(InputController.HEALD_5) || mixedIn.isClicked(InputController.RIGHT)) {
					ch = 1;
				}
				
				if (mixedIn.isPress(InputController.SPACE)) {
					ch *= 10;
				}
				
				if (ch != 0) {
					int size = this.repo.size();
					this.selectedRomId += ch;
					
					if (this.selectedRomId < 0) {
						this.selectedRomId = 0;
					}
					
					if (this.selectedRomId >= size) {
						this.selectedRomId = size - 1;
					}
					
					this.showRom();
				}
			}
		}
	}

}
