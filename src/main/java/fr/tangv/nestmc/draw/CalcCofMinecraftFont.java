package fr.tangv.nestmc.draw;

import org.bukkit.map.MinecraftFont;

/**
 * @author Tangv - https://tangv.fr
 * Permet de calculer la taille des texte avec un coficient 
 */
public abstract class CalcCofMinecraftFont {
	
	/**
	 * Permet de retourner la largueur des textes en prennant en compte le cof
	 * @param text texte dont on souhaite la largueur
	 * @param cof coficient du texte
	 * @return la largueur du texte en fonction du cof
	 */
	public static int getWidthText(String text, byte cof) {
		return MinecraftFont.Font.getWidth(text) * cof;
  	}
	
	/**
	 * Permet de retourner la largueur d'un caratère en prennant en compte le cof
	 * @param ch caratère dont on souhaite savoir la largueur
	 * @param cof coficient du texte
	 * @return la largueur du texte en fonction du cof
	 */
	public static int getWidthChar(char ch, byte cof) {
		return MinecraftFont.Font.getChar(ch).getWidth() * cof;
  	}
  
	/**
	 * Permet de retourner la hauteur des textes en prennant en compte le cof
	 * @param cof coficient du texte
	 * @return hauteur du texte en fonction du cof
	 */
	public static int getHeightText(byte cof) {
		return MinecraftFont.Font.getHeight() * cof;
	}
	
}
