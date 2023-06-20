package fr.tangv.nestmc.nes.software.os.element.border;

/**
 * @author Tangv - https://tangv.fr
 * Méthodes qui interagise avec la couleur d'une bordure
 */
public interface ColoredBorder {

    /**
     * Permet de récupérer la couleur du bord
     * @return la couleur du bord
     */
    public byte getBorderColor();

    /**
     * Permet de modifier la couleur du bord
     * @param color la nouvelle couleur du bord
     */
    public void setBorderColor(byte color);

}
