package fr.tangv.nestmc.nes.software.os.color;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

/**
 * @author Tangv - https://tangv.fr
 * Object qui possède la liste des couleurs pour la construction d'un gui
 */
public class GuiColors implements ConfigurationSerializable {
	
	private final byte title;
	private final byte text;
	private final byte border;
	private final byte background;
	private final FocusColors button;
	
	/**
	 * Permet de construire 
	 * @param title la couleur des titres dans le gui
	 * @param text la couleur des textes dans le gui
	 * @param border la couleur du bord du gui
	 * @param background la couleur du fond du gui
	 * @param button les couleurs pour les elements selectionnables
	 */
	public GuiColors(byte title, byte text, byte border, byte background, FocusColors button) {
		this.title = title;
		this.text = text;
		this.border = border;
		this.background = background;
		this.button = button;
	}

	/**
	 * Permet de récupérer la couleur des titres dans le gui
	 * @return la couleur des titres dans le gui
	 */
	public byte getTitle() {
		return this.title;
	}

	/**
	 * Permet de récupérer la couleur des textes dans le gui
	 * @return la couleur des textes dans le gui
	 */
	public byte getText() {
		return this.text;
	}

	/**
	 * Permet de récupérer la couleur du bord du gui
	 * @return la couleur du bord du gui
	 */
	public byte getBorder() {
		return this.border;
	}

	/**
	 * Permet de récupérer la couleur du fond du gui
	 * @return la couleur du fond du gui
	 */
	public byte getBackground() {
		return this.background;
	}

	/**
	 * Permet de récupérer les couleurs pour les elements selectionnables
	 * @return les couleurs pour les elements selectionnables
	 */
	public FocusColors getButton() {
		return this.button;
	}
	
	@Override
	public Map<String, Object> serialize() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("title", title);
		map.put("text", text);
		map.put("border", border);
		map.put("background", background);
		map.put("button", button);
		return map;
	}
	
	public static GuiColors deserialize(Map<String, Object> map) {
		return new GuiColors(
				(byte) map.getOrDefault("title", 0),
				(byte) map.getOrDefault("text", 0),
				(byte) map.getOrDefault("border", 0),
				(byte) map.getOrDefault("background", 0),
				(FocusColors) map.getOrDefault("button", null)
				);
	}
	
}
