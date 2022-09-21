package fr.tangv.nestmc.nes.controller;

import com.grapeshot.halfnes.ui.ControllerInterface;

public class NesController implements ControllerInterface, InputController {
	
	/*etat des boutons a ce cycle*/
	private int nowButtons = 0;
	/*etat des boutons au cycle dernier*/
	private int prevButtons = 0;
	/*si le boutons est cliquer ou non*/
	private int clickButtons = 0;
	/*inspiré de la classe PuppetController*/
    private int latchbyte = 0, outbyte = 0;

    @Override
    public synchronized void strobe() {
        //shifts a byte out
        this.outbyte = this.latchbyte & 1;
        this.latchbyte = ((latchbyte >> 1) | 0x100);
    }

    @Override
    public synchronized void output(boolean state) {
        this.latchbyte = this.nowButtons & 0xFF;//on garde que les 8 bit pour la nes
    }

    @Override
    public synchronized int peekOutput() {
        return this.latchbyte;
    }

    @Override
    public synchronized int getbyte() {
        return this.outbyte;
    }
	
	/**
	 * Permet de crée un controlleur de console NES vierge
	 */
	public NesController() {}

	/**
	 * Permet de mettre a jour les valeurs (clicked buttons & press buttons)
	 */
	public synchronized void update() {
		//calc cliked buttons
		this.clickButtons = ~this.prevButtons & this.nowButtons;
		this.prevButtons = this.nowButtons;
	}
	
	/**
	 * Permet de reset les controles
	 */
	public synchronized void resetButtons() {
		this.nowButtons = 0;
		this.prevButtons = 0;
		this.clickButtons = 0;
		this.outbyte = 0;
		this.latchbyte = 0;
	}
	
	@Override
	public synchronized boolean isClicked(int button) {
		return (button & this.clickButtons) == button;
	}
	
	@Override
	public synchronized boolean isPress(int button) {
		return (button & this.prevButtons) == button;
	}
	
	/**
	 * Permet de modifier le bouton held appuyer
	 * @param held le held doit etre entre 1 et 8, en dehors tout les held seront défini à false
	 */
	public synchronized void setHeld(int held) {
		this.nowButtons &= 0b0000_0000_1_1111_1111;//reset all held
		//held
		if (held >= 1 && held <= 8) {
			this.nowButtons |= 1 << held + 8;
		}
	}
	
	/**
	 * Permet de relacher un bouton
	 * @param button bouton relaché
	 */
	public synchronized void releaseButton(int button) {
		this.nowButtons &= ~button;
	}
	
	/**
	 * Permet d'enfoncer un bouton
	 * @param button bouton enfoncé
	 */
	public synchronized void pressButton(int button) {
		this.nowButtons |= button;
	}
	
}
