package fr.tangv.nestmc.nes.software.os.tmcos;

import fr.tangv.nestmc.draw.DrawableImage;
import fr.tangv.nestmc.nes.NesScreen;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.software.TMCNesRendered;
import fr.tangv.nestmc.nes.software.img.TMCImageOs;
import fr.tangv.nestmc.nes.software.os.color.NesInfoColors;
import org.apache.commons.lang3.Validate;

/**
 * @author Tangv - https://tangv.fr
 * Permet de dessiner l'écran emulé de la nes, une nes en bas de l'écran de l'os, et de donner l'information de si elle est alumé ou non et si les controlleur sont occuppé
 */
public class NesInfoRender implements TMCNesRendered {

    private NesInfoColors colors;

    /**
     * Permet de construire un rendeur d'info de nes
     * @param turnOn couleur de la led de la nes allumé
     * @param turnOff couleur de la led de la nes éteint
     * @param screenBlack couleur de l'écran éteint
     * @param cableBlackColor couleur du cable d'une manette
     * @param plugBlackColor couleur de la prise d'une manette
     */
    public NesInfoRender(byte turnOn, byte turnOff, byte screenBlack, byte cableBlackColor, byte plugBlackColor) {
        this(new NesInfoColors(turnOn, turnOff, screenBlack, cableBlackColor, plugBlackColor));
    }

    /**
     * Permet de construire un rendeur d'info de nes
     * @param colors liste de couleurs nécessaires au rendeur
     */
    public NesInfoRender(NesInfoColors colors) {
        this.colors = colors;
    }

    /**
     * Permet de calculer la courbure du cable d'une manette pour le lier à l'image de la nes
     * @param x le décalage par rapport à l'origine du cable sur la manette
     * @return la hauteur du cable en fonction du déplacement horizontale
     */
    private int calcYCable(double x) {
        Validate.isTrue(x >= 0, "x can't be negative", x);
        return (int) ((Math.log(1 + (4 * x)) * 1.6) - (0.3 * x));
    }

    @Override
    public void render(TMCNes nes, NesScreen screen) {
        //var nes
        DrawableImage nesImg = TMCImageOs.NES_CONSOLE;
        final int NES_X = (NesScreen.WIDTH / 2) - (nesImg.getWidth() / 2);
        final int NES_Y = NesScreen.NES_HEIGHT + 1;

        //var joypad
        DrawableImage joyImg = TMCImageOs.JOYPAD_CONSOLE;
        final int JOY_Y = NesScreen.SCREEN_HEIGHT - joyImg.getHeight() - 2;
        final int JOY_SPACE = 8;
        final int JOY_1_X = NES_X - JOY_SPACE - joyImg.getWidth();
        final int JOY_2_X = NES_X + nesImg.getWidth() + JOY_SPACE;

        //var etat
        byte statColor;
        if (nes.isStart()) {
            screen.drawNesScreen();
            //clear screen
            screen.setColor(this.colors.getScreenBlack());
            screen.fillRect(
                    JOY_1_X,
                    NesScreen.NES_HEIGHT,
                    (JOY_2_X + joyImg.getWidth()) - JOY_1_X,
                    NesScreen.SCREEN_HEIGHT - NesScreen.NES_HEIGHT
            );
            //etat
            statColor = this.colors.getTurnOn();
        } else {
            //clear screen
            screen.clearScreen(this.colors.getScreenBlack());
            //etat
            statColor = this.colors.getTurnOff();
        }

        //draw nes
        screen.setCof((byte) 1);
        screen.drawImage(NES_X, NES_Y, nesImg);

        //draw stat led nes
        screen.setColor(statColor);
        screen.drawPoint(NES_X + 5, NES_Y + 10);

        //joypad1
        if (nes.getFirstController().isConnected()) {
            screen.drawImage(JOY_1_X, JOY_Y, joyImg);
            //joypad plug
            screen.setColor(this.colors.getPlugBlackColor());
            screen.fillRect(NES_X + 7, NES_Y + 10, 5, 2);
            //joypad cable
            screen.setColor(this.colors.getCableBlackColor());
            final int JOY_1_ORIGIN_X = JOY_1_X + (joyImg.getWidth() / 2);
            final int JOY_CABLE_1_LEN = NES_X - JOY_1_ORIGIN_X;
            final int JOY_1_ORIGIN_Y = JOY_Y - 1;
            //draw cable
            for (double x = 0; x < JOY_CABLE_1_LEN; x += 0.5) {
                screen.drawPoint(
                        JOY_1_ORIGIN_X + (int) x,
                        JOY_1_ORIGIN_Y - this.calcYCable(x)
                );
            }
        }

        //joypad2
        if (nes.getSecondController().isConnected()) {
            screen.drawImage(JOY_2_X, JOY_Y, joyImg);
            //joypad plug
            screen.setColor(this.colors.getPlugBlackColor());
            screen.fillRect(NES_X + 7 + 6, NES_Y + 10, 5, 2);
            //joypad cable
            screen.setColor(this.colors.getCableBlackColor());
            final int JOY_2_ORIGIN_X = JOY_2_X + (joyImg.getWidth() / 2);
            final int JOY_CABLE_2_LEN = JOY_2_ORIGIN_X - (NES_X + nesImg.getWidth() - 1);
            final int JOY_2_ORIGIN_Y = JOY_Y - 1;
            //draw cable
            for (double x = 0; x < JOY_CABLE_2_LEN; x += 0.5) {
                screen.drawPoint(
                        JOY_2_ORIGIN_X - (int) x,
                        JOY_2_ORIGIN_Y - this.calcYCable(x)
                );
            }
        }
    }

}
