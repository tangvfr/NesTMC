package fr.tangv.nestmc.draw;

/**
 * @author Tangv - https://tangv.fr
 *	Permet de dessiner sur un buffer representante un rectangle
 */
public class DrawableBuffer extends Drawable implements PixeableBuffered {

    //tableau des couleurs
    private final byte[] buf;

    /**
     * Constructeur qui permet de crée un buffer pour un rectangle
     * @param width la largeur du rectangle que le buffer represent
     * @param height la hauteur du rectangle que le buffer represent
     */
    public DrawableBuffer(int width, int height) {
        super(width, height);
        this.buf = new byte[width * height];
    }

    /**
     * Constructeur qui permet de crée un buffer pour un carré
     * @param width la largeur et hauteur du carré que le buffer represent
     */
    public DrawableBuffer(int width) {
        super(width);
        this.buf = new byte[width * width];
    }

    @Override
    public byte[] getBuffer() {
        return this.buf;
    }

    @Override
    public void setPixel(int x, int y, byte color) {
        this.buf[x + (y * this.getWidth())] = color;
    }

    @Override
    public void clearScreen(byte color) {
        int len = this.buf.length;
        for (int i = 0; i < len; i++) {//parcours de tout les couleurs
            this.buf[i] = color;
        }
    }

}
