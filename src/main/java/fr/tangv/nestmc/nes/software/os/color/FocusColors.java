package fr.tangv.nestmc.nes.software.os.color;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tangv - https://tangv.fr
 * Object qui possède la liste des couleurs pour la construction d'elements selectionnable
 */
public class FocusColors implements ConfigurationSerializable {

	private final byte unfocusBorder;
	private final byte focusBorder;
	private final byte unfocusBackground;
	private final byte focusBackground;
	
	/**
	 * Permet de construire un set de couleur pour des element selectionnable
	 * @param unfocusBorder la couleur du bord quand deselectionner
	 * @param focusBorder la couleur du bord quand selectionner
	 * @param unfocusBackground la couleur du fond quand deselectionner
	 * @param focusBackground la couleur du fond quand selectionner
	 */
	public FocusColors(byte unfocusBorder, byte focusBorder, byte unfocusBackground, byte focusBackground) {
		this.unfocusBorder = unfocusBorder;
		this.focusBorder = focusBorder;
		this.unfocusBackground = unfocusBackground;
		this.focusBackground = focusBackground;
	}
	
	/**
	 * Permet de récupérer la couleur du bord quand deselectionner
	 * @return la couleur du bord quand deselectionner
	 */
	public byte getUnfocusBorder() {
		return this.unfocusBorder;
	}
	/**
	 * Permet de récupérer la couleur du bord quand selectionner
	 * @return la couleur du bord quand selectionner
	 */
	public byte getFocusBorder() {
		return this.focusBorder;
	}
	/**
	 * Permet de récupérer la couleur du fond quand deselectionner
	 * @return la couleur du fond quand deselectionner
	 */
	public byte getUnfocusBackground() {
		return this.unfocusBackground;
	}
	/**
	 * Permet de récupérer la couleur du fond quand selectionner
	 * @return la couleur du fond quand selectionner
	 */
	public byte getFocusBackground() {
		return this.focusBackground;
	}

	@Override
	public Map<String, Object> serialize() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("unfocus_border", this.unfocusBorder);
		map.put("focus_border", this.focusBorder);
		map.put("unfocus_background", this.unfocusBackground);
		map.put("focus_background", this.focusBackground);
		return map;
	}
	
	public static FocusColors deserialize(Map<String, Object> map) {
		return new FocusColors(
				(byte) map.getOrDefault("unfocus_border", 0),
				(byte) map.getOrDefault("focus_border", 0),
				(byte) map.getOrDefault("unfocus_background", 0),
				(byte) map.getOrDefault("focus_background", 0)
				);
	}
	
}
