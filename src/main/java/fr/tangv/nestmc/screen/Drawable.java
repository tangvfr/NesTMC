package fr.tangv.nestmc.screen;

import fr.tangv.nestmc.palette.MapColor;

/**
 * @author tangv
 * Permet de dessiner
 */
public abstract class Drawable implements Pixelable {

	//largeur
	private final int SIZE_X;
	//hauteur
	private final int SIZE_Y;
	//coleur dessiné
	private byte color;
	
	/**
	 * Construit un Drawable de size x size
	 * @param size taille carré du drawable
	 */
	public Drawable(int size) {
		this.color = MapColor.TRANSPARENT_DARK;
		this.SIZE_X = size;
		this.SIZE_Y = size;
	}
	
	/**
	 * Construit un Drawable de sizeX x sizeY
	 * @param sizeX taille en largeur du drawable
	 * @param sizeY taille en hauteur du drawable
	 */
	public Drawable(int sizeX, int sizeY) {
		this.color = MapColor.TRANSPARENT_DARK;
		this.SIZE_X = sizeX;
		this.SIZE_Y = sizeY;
	}
	
	/**
	 * Permet de définir la couleur dessiné
	 * @param color couleur déssiné
	 */
	public void setColor(byte color) {
		this.color = color;
	}
	
	/**
	 * Permet de recupéré la couleur déssiné
	 * @return couleur déssiné
	 */
	public byte getColor() {
		return color;
	}

	/**
	 * Permet de déssiné la couleur définé au point (x, y), sans erreur de débordement
	 * @param x coordonnée du point en partant de la gauche
	 * @param y coordonnée du point en partant du haut
	 */
	public void drawPoint(int x, int y) {
		if (x >= 0 && x < this.SIZE_X && y >= 0 && y < this.SIZE_Y)
			this.setPixel(x, y, this.color);
	}
	
	/**
	 * Permet de tracer une ligne entre deux points
	 * @param x1 coordonnée du premier point en partant de la gauche
	 * @param y1 coordonnée du premier point en partant du haut
	 * @param x2 coordonnée du deuxieme point en partant de la gauche
	 * @param y2 coordonnée du deuxieme point en partant du haut
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
        int x, y;
        int dx, dy;
        int incx, incy;
        int balance;

        if (x2 >= x1) {
            dx = x2 - x1;
            incx = 1;
        } else {
            dx = x1 - x2;
            incx = -1;
        }

        if (y2 >= y1) {
            dy = y2 - y1;
            incy = 1;
        } else {
            dy = y1 - y2;
            incy = -1;
        }

        x = x1;
        y = y1;

        if (dx >= dy) {
            dy <<= 1;
            balance = dy - dx;
            dx <<= 1;

            while (x != x2) {
            	drawPoint(x, y);
                if (balance >= 0) {
                    y += incy;
                    balance -= dx;
                }
                balance += dy;
                x += incx;
            }
            drawPoint(x, y);
        } else {
            dx <<= 1;
            balance = dx - dy;
            dy <<= 1;

            while (y != y2) {
                drawPoint(x, y);
                if (balance >= 0) {
                    x += incx;
                    balance -= dy;
                }
                balance += dx;
                y += incy;
            }
            drawPoint(x, y);
        }

        return;
    }
	
	/**
	 * Permet de tracer les contours d'un rectangle
	 * @param x coordonnée inital du rectangle en partant de la gauche
	 * @param y coordonnée inital du rectangle en partant du haut
	 * @param width largueur du rectangle
	 * @param height hauteur du rectangle
	 */
	public void drawRect(int x, int y, int width, int height) {
        if (width < 0 || height < 0) {
            return;
        }
        if (height == 0 || width == 0) {
            drawLine(x, y, x + width, y + height);
        } else {
            drawLine(x, y, x + width - 1, y);
            drawLine(x + width, y, x + width, y + height - 1);
            drawLine(x + width, y + height, x + 1, y + height);
            drawLine(x, y + height, x, y + 1);
        }
    }
	
	/**
	 * Permet de tracer rectangle plein
	 * @param x coordonnée inital du rectangle en partant de la gauche
	 * @param y coordonnée inital du rectangle en partant du haut
	 * @param width largueur du rectangle
	 * @param height hauteur du rectangle
	 */
	public void fillRect(int x, int y, int width, int height) {
		 if (width < 0 || height < 0) {
			 return;
	     }
		 if (height == 0 || width == 0) {
			 drawLine(x, y, x + width, y + height);
		 } else {
			 if (width > height) {
				 for (int i = 0; i < height; i++)
					 drawLine(x, y + i, x + width -1, y + i);
			 } else {
				 for (int i = 0; i < width; i++)
					 drawLine(x + i, y, x + i, y + height - 1);
			 }
	     }
    }
	
	/**
	 * Permet de tracer un cercle
	 * @param xc coordonnée central du cercle en partant de la gauche
	 * @param yc coordonnée central du cercle en partant du haut
	 * @param r rayon du cercle
	 */
	public void drawCircle(int xc, int yc, int r) {
		int x = 0;
	    int y = r;
	    int d = r - 1;
	    
	    while(y >= x)
	    {
	    	drawPoint(xc + x, yc + y);
	        drawPoint(xc + y, yc + x);
	        drawPoint(xc - x, yc + y);
	        drawPoint(xc - y, yc + x);
	        drawPoint(xc + x, yc - y);
	        drawPoint(xc + y, yc - x);
	        drawPoint(xc - x, yc - y);
	        drawPoint(xc - y, yc - x);
	        
	        if (d >= 2*x)
	        {
	            d -= 2*x + 1;
	            x ++;
	        }
	        else if (d < 2 * (r-y))
	        {
	            d += 2*y - 1;
	            y --;
	        }
	        else
	        {
	            d += 2*(y - x - 1);
	            y --;
	            x ++;
	        }
	    }
	}
	
	/**
	 * Permet de tracer un disque
	 * @param xc coordonnée central du disque en partant de la gauche
	 * @param yc coordonnée central du disque en partant du haut
	 * @param r rayon du disque
	 */
	public void fillCircle(int xc, int yc, int r) {
		int x = 0;
		int y = r;
		int d = r - 1;
		
		while(y >= x)
		{
			drawLine(xc + x , yc - y, xc + x , yc + y);
	        drawLine(xc + y , yc - x, xc + y , yc + x);
	        drawLine(xc - x , yc - y, xc - x , yc + y);
	        drawLine(xc - y , yc - x, xc - y , yc + x);
			
			if (d >= 2*x)
			{
				d -= 2*x + 1;
				x ++;
			}
			else if (d < 2 * (r-y))
			{
				d += 2*y - 1;
				y --;
			}
			else
			{
				d += 2*(y - x - 1);
				y --;
				x ++;
			}
		}
	}
	
}
