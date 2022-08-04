package fr.tangv.nestmc.nes.controller;

public interface InputController {

	/*NES BUTTONS*/
	public static int SPACE = 0x01;//A
	public static int INTERACT = 0x02;//B
	public static int OPEN_INV = 0x04;//SELECT
	public static int ATTACK = 0x08;//START
	public static int UP = 0x10;//UP
	public static int DOWN = 0x20;//DOWN
	public static int LEFT = 0x40;//LEFT
	public static int RIGHT = 0x80;//RIGHT
	/*ADICTIONAL BUTTONS*/
	public static int SNEAK = 0x0100;
	public static int HEALD_1 = 0x0200;
	public static int HEALD_2 = 0x0400;
	public static int HEALD_3 = 0x0800;
	public static int HEALD_4 = 0x1000;
	public static int HEALD_5 = 0x2000;
	public static int HEALD_6 = 0x4000;
	public static int HEALD_7 = 0x8000;
	public static int HEALD_8 = 0x10000;

	/**
	 * Permet de savoir si un bouton a été cliquer
	 * @param button boutton testé
	 * @return true si le bouton vient d'être cliqué
	 */
	public boolean isClicked(int button);
	
	/**
	 * Permet de savoir si un bouton est enfoncé
	 * @param button boutton testé
	 * @return true si le bouton est enfoncé
	 */
	public boolean isPress(int button);
	
}
