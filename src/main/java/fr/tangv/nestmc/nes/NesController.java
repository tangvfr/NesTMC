package fr.tangv.nestmc.nes;

import com.grapeshot.halfnes.ui.ControllerInterface;

public class NesController implements ControllerInterface {
	
	/*
	HELD for MENU
	CTRL for MENU
	ZQSD = UP LEFT DOWN RIGTH
	A = START
	ATTACK = SELECT
	SPACE = A
	INTERACT = B
	*/
	public static int BUTTON_SPACE = 0x01;//A
	public static int BUTTON_INTERACT = 0x02;//B
	public static int BUTTON_ATTACK = 0x04;//SELECT
	public static int BUTTON_OPEN_INV = 0x08;//START
	public static int BUTTON_UP = 0x10;//UP
	public static int BUTTON_DOWN = 0x20;//DOWN
	public static int BUTTON_LEFT = 0x40;//LEFT
	public static int BUTTON_RIGHT = 0x80;//RIGHT
	public static int BUTTON_SNEAK = 0x0100;
	public static int BUTTON_HEALD_1 = 0x0200;
	public static int BUTTON_HEALD_2 = 0x0400;
	public static int BUTTON_HEALD_3 = 0x0800;
	public static int BUTTON_HEALD_4 = 0x1000;
	public static int BUTTON_HEALD_5 = 0x2000;
	public static int BUTTON_HEALD_6 = 0x4000;
	public static int BUTTON_HEALD_7 = 0x8000;
	public static int BUTTON_HEALD_8 = 0x10000;
	
	/*etat des boutons a ce cycle*/
	private int nowButtons = 0;
	/*etat des boutons au cycle dernier*/
	private int prevButtons = 0;
	/*si le boutons est cliquer ou non*/
	private int clickButtons = 0;
	/*inspiré de la classe PuppetController*/
    private int latchbyte = 0, outbyte = 0;

    @Override
    public void strobe() {
        //shifts a byte out
        this.outbyte = this.latchbyte & 1;
        this.latchbyte = ((latchbyte >> 1) | 0x100);
    }

    @Override
    public void output(boolean state) {
        this.latchbyte = this.nowButtons & 0xFF;//on garde que les 8 bit pour la nes
    }

    @Override
    public int peekOutput() {
        return this.latchbyte;
    }

    @Override
    public int getbyte() {
        return this.outbyte;
    }
	
	/**
	 * Permet de crée un controlleur de console NES vierge
	 */
	public NesController() {}

	/**
	 * Permet de mettre a jour les valeurs
	 */
	public void update() {
		//calc cliked buttons
		this.clickButtons = ~this.prevButtons & this.nowButtons;
		this.prevButtons = this.nowButtons;
	}
	
	/**
	 * Permet de reset les controles
	 */
	public void resetButtons() {
		this.nowButtons = 0;
		this.prevButtons = 0;
		this.clickButtons = 0;
		this.outbyte = 0;
		this.latchbyte = 0;
	}
	
	/**
	 * Permet de savoir si un bouton a été cliquer
	 * @param button boutton testé
	 * @return true si le bouton vient d'être cliqué
	 */
	public boolean isClicked(int button) {
		return (button & this.clickButtons) != 0;
	}
	
	/**
	 * Permet de savoir si un bouton est enfoncé
	 * @param button boutton testé
	 * @return true si le bouton est enfoncé
	 */
	public boolean isPress(int button) {
		return (button & this.prevButtons) != 0;
	}
	
	/**
	 * Permet de modifier le bouton held appuyer
	 * @param held le held doit etre entre 1 et 8, en dehors a set tout les held à false
	 */
	public void setHeld(int held) {
		this.nowButtons &= 0b1111_11110;//reset all held
		//held
		if (held >= 1 && held <= 8) {
			this.nowButtons |= 1 << held;
		}
	}
	
	/**
	 * Permet de relacher un bouton
	 * @param button bouton relaché
	 */
	public void releaseButton(int button) {
		this.nowButtons &= ~button;
	}
	
	/**
	 * Permet d'enfoncer un bouton
	 * @param button bouton enfoncé
	 */
	public void pressButton(int button) {
		this.nowButtons |= button;
	}
	
}
