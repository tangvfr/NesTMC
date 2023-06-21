package fr.tangv.nestmc.nes.software.os.color;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tangv - https://tangv.fr
 * Object qui possède la liste des couleurs pour la representation des informations visuelles d'une nes
 */
public class NesInfoColors implements ConfigurationSerializable {

    private byte turnOn;
    private byte turnOff;
    private byte screenBlack;
    private byte cableBlackColor;
    private byte plugBlackColor;

    /**
     * Permet de construire un rendeur d'info de nes
     * @param turnOn couleur de la led de la nes allumé
     * @param turnOff couleur de la led de la nes éteint
     * @param screenBlack couleur de l'écran éteint
     * @param cableBlackColor couleur du cable d'une manette
     * @param plugBlackColor couleur de la prise d'une manette
     */
    public NesInfoColors(byte turnOn, byte turnOff, byte screenBlack, byte cableBlackColor, byte plugBlackColor) {
        this.turnOn = turnOn;
        this.turnOff = turnOff;
        this.screenBlack = screenBlack;
        this.cableBlackColor = cableBlackColor;
        this.plugBlackColor = plugBlackColor;
    }

    /**
     * Permet de récupérer la couleur de la led de la nes allumé
     * @return la couleur de la led de la nes allumé
     */
    public byte getTurnOn() {
        return turnOn;
    }

    /**
     * Permet de récupérer la couleur de la led de la nes éteint
     * @return la couleur de la led de la nes éteint
     */
    public byte getTurnOff() {
        return turnOff;
    }

    /**
     * Permet de récupérer la couleur de l'écran éteint
     * @return la couleur de l'écran éteint
     */
    public byte getScreenBlack() {
        return screenBlack;
    }

    /**
     * Permet de récupérer la couleur du cable d'une manette
     * @return la couleur du cable d'une manette
     */
    public byte getCableBlackColor() {
        return cableBlackColor;
    }

    /**
     * Permet de récupérer la couleur de la prise d'une manette
     * @return la couleur de la prise d'une manette
     */
    public byte getPlugBlackColor() {
        return plugBlackColor;
    }

    @Override
    public Map<String, Object> serialize() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("turnOn", this.turnOn);
        map.put("turnOff", this.turnOff);
        map.put("screenBlack", this.screenBlack);
        map.put("cableBlackColor", this.cableBlackColor);
        map.put("plugBlackColor", this.plugBlackColor);
        return map;
    }

    public static NesInfoColors deserialize(Map<String, Object> map) {
        return new NesInfoColors(
                (byte) map.getOrDefault("turnOn", 0),
                (byte) map.getOrDefault("turnOff", 0),
                (byte) map.getOrDefault("screenBlack", 0),
                (byte) map.getOrDefault("cableBlackColor", 0),
                (byte) map.getOrDefault("plugBlackColor", 0)
        );
    }

}
