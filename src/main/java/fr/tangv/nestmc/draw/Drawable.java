package fr.tangv.nestmc.draw;

import fr.tangv.nestmc.palette.v1_8.MapColorV1_8;
import org.apache.commons.lang.Validate;
import org.bukkit.map.MapFont.CharacterSprite;
import org.bukkit.map.MinecraftFont;

/**
 * @author Tangv - https://tangv.fr
 * Permet de dessiner
 */
public abstract class Drawable implements Pixelable {

	//largeur
	private final int SIZE_X;
	//hauteur
	private final int SIZE_Y;
	//coleur dessiné
	private byte color = MapColorV1_8.TRANSPARENT_DARK;
	//coefficient multiplicateur de buffer et de texte
	private byte cof = 1;
	
	/**
	 * Construit un Drawable de size x size avec un coef de 1
	 * @param size taille carré du drawable
	 */
	public Drawable(int size) {
		this.SIZE_X = size;
		this.SIZE_Y = size;
	}
	
	/**
	 * Construit un Drawable de sizeX x sizeY avec un coef de 1
	 * @param width taille en largeur du drawable
	 * @param height taille en hauteur du drawable
	 */
	public Drawable(int width, int height) {
		this.SIZE_X = width;
		this.SIZE_Y = height;
	}
	
	/**
	 * Méthode qui renvoie la largueur du dessin
	 * @return la largueur du dessin
	 */
	public int getWidth() {
		return this.SIZE_X;
	}
	
	/**
	 * Méthode qui renvoie la hauteur du dessin
	 * @return la hauteur du dessin
	 */
	public int getHeight() {
		return this.SIZE_Y;
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
		return this.color;
	}
	
	/**
	 * Permet de définir le coefficient multiplicateur de buffer et de texte
	 * @param cof coefficient multiplicateur de buffer et de texte
	 */
	public void setCof(byte cof) {
		this.cof = cof;
	}
	
	/**
	 * Permet de recupéré le coefficient multiplicateur de buffer et de texte
	 * @return le coefficient multiplicateur de buffer et de texte
	 */
	public byte getCof() {
		return this.cof;
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
	 * Permet de tracer une ligne entre deux points inclus
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
            	this.drawPoint(x, y);
                if (balance >= 0) {
                    y += incy;
                    balance -= dx;
                }
                balance += dy;
                x += incx;
            }
            this.drawPoint(x, y);
        } else {
            dx <<= 1;
            balance = dx - dy;
            dy <<= 1;

            while (y != y2) {
            	this.drawPoint(x, y);
                if (balance >= 0) {
                    x += incx;
                    balance -= dy;
                }
                balance += dx;
                y += incy;
            }
            this.drawPoint(x, y);
        }

        return;
    }
	
	/**
	 * Permet de tracer une ligne entre deux points inclus horizontalement
	 * @param x1 coordonnée du premier point en partant de la gauche
	 * @param y1 coordonnée du premier point en partant du haut
	 * @param x2 coordonnée du deuxieme point en partant de la gauche
	 */
	public void drawLineX(int x1, int y1, int x2) {
		if (x1 > x2) {//échange les valeur pour pouvoir tracé
			int tmp = x1;
			x1 = x2;
			x2 = tmp;
		}	
		
		//dessine la ligne
        for (int x = x1; x <= x2; x++) {
        	this.drawPoint(x, y1);
        }
    }
	
	/**
	 * Permet de tracer une colonne entre deux points inclus verticalement
	 * @param x1 coordonnée du premier point en partant de la gauche
	 * @param y1 coordonnée du premier point en partant du haut
	 * @param y2 coordonnée du deuxieme point en partant du haut
	 */
	public void drawLineY(int x1, int y1, int y2) {
		if (y1 > y2) {//échange les valeur pour pouvoir tracé
			int tmp = y1;
			y1 = y2;
			y2 = tmp;
		}
		
		//dessine la colonne
        for (int y = y1; y <= y2; y++) {
        	this.drawPoint(x1, y);
        }
    }
	
	/**
	 * Permet de tracer les contours d'un rectangle
	 * @param x1 coordonnée inital du rectangle en partant de la gauche
	 * @param y1 coordonnée inital du rectangle en partant du haut
	 * @param width largueur du rectangle
	 * @param height hauteur du rectangle
	 */
	public void drawRect(int x1, int y1, int width, int height) {
        if (width > 0 && height > 0) {
        	int drawX = x1 + width - 1;//arret dessiner en x 
        	int drawY = y1 + height - 1;//arret dessiner en y
        	
        	this.drawLineX(x1, y1, drawX - 1);
        	this.drawLineY(drawX, y1, drawY - 1);
        	this.drawLineX(x1 + 1, drawY, drawX);
        	this.drawLineY(x1, y1 + 1, drawY);
        }
    }
	
	
	
	/**
	 * Permet de tracer rectangle plein
	 * @param x1 coordonnée inital du rectangle en partant de la gauche
	 * @param y1 coordonnée inital du rectangle en partant du haut
	 * @param width largueur du rectangle
	 * @param height hauteur du rectangle
	 */
	public void fillRect(int x1, int y1, int width, int height) {
		 if (width > 0 && height > 0) {
			int drawX = x1 + width - 1;//arret dessiner en x 
			int drawY = y1 + height - 1;//arret dessiner en y
			
		    for (int y = y1; y <= drawY; y++) {//dessine les colonne
		    	for (int x = x1; x <= drawX; x++) {//dessine la ligne de la colonne
		    		this.drawPoint(x, y);//dessine le point
		    	}
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
		Validate.isTrue(r >= 0, "r can't be negative", r);

		int x = 0;
	    int y = r;
	    int d = r - 1;
	    
	    while(y >= x)
	    {
	    	this.drawPoint(xc + x, yc + y);
	    	this.drawPoint(xc + y, yc + x);
	    	this.drawPoint(xc - x, yc + y);
	    	this.drawPoint(xc - y, yc + x);
	    	this.drawPoint(xc + x, yc - y);
	    	this.drawPoint(xc + y, yc - x);
	    	this.drawPoint(xc - x, yc - y);
	    	this.drawPoint(xc - y, yc - x);
	        
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
		Validate.isTrue(r >= 0, "r can't be negative", r);

		int x = 0;
		int y = r;
		int d = r - 1;
		
		while(y >= x)
		{
			this.drawLineY(xc + x, yc - y, yc + y);
			this.drawLineY(xc + y, yc - x, yc + x);
			this.drawLineY(xc - x, yc - y, yc + y);
			this.drawLineY(xc - y, yc - x, yc + x);
			
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
	 * Permet de tracer les bord d'un rectangle arrondi
	 * @param x1 coordonnée initiale du rectangle en partant de la gauche
	 * @param y1 coordonnée initiale du rectangle en partant du haut
	 * @param r rayon du cercle
	 * @param width largueur du rectangle
	 * @param height hauteur du rectangle
	 */
	public void drawAroundRect(int x1, int y1, int width, int height, int r) {
		Validate.isTrue(r >= 0, "r can't be negative", r);
		Validate.isTrue(r <= width, "r can't be > width", r);
		Validate.isTrue(r <= height, "r can't be > height", r);

		//round
		int xs = x1 + r;//x to start round
		int ys = y1 + r;//y to start round
		int xm = x1 + width - 1;//x max
		int ym = y1 + height - 1;//y max
		int xe = xm - r;//x to end round
		int ye = ym - r;//y to end round

		//border line
		this.drawLineX(xs + 1, y1, xe - 1);
		this.drawLineX(xs + 1, ym, xe - 1);
		this.drawLineY(x1, ys + 1, ye - 1);
		this.drawLineY(xm , ys + 1, ye - 1);

		//test
		int x = 0;
		int y = r;
		int d = r - 1;

		while(y >= x)
		{
			this.drawPoint(xe + x, ye + y);
			this.drawPoint(xe + y, ye + x);
			this.drawPoint(xs - x, ye + y);
			this.drawPoint(xs - y, ye + x);
			this.drawPoint(xe + x, ys - y);
			this.drawPoint(xe + y, ys - x);
			this.drawPoint(xs - x, ys - y);
			this.drawPoint(xs - y, ys - x);

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
	 * Permet de tracer un rectangle arrondi plein
	 * @param x1 coordonnée initiale du rectangle en partant de la gauche
	 * @param y1 coordonnée initiale du rectangle en partant du haut
	 * @param r rayon du cercle
	 * @param width largueur du rectangle
	 * @param height hauteur du rectangle
	 */
	public void fillAroundRect(int x1, int y1, int width, int height, int r) {
		Validate.isTrue(r >= 0, "r can't be negative", r);
		Validate.isTrue(r <= width, "r can't be > width", r);
		Validate.isTrue(r <= height, "r can't be > height", r);

		//round
		int xs = x1 + r;//x to start round
		int ys = y1 + r;//y to start round
		int xm = x1 + width - 1;//x max
		int ym = y1 + height - 1;//y max
		int xe = xm - r;//x to end round
		int ye = ym - r;//y to end round

		//inside
		this.fillRect(xs + 1, y1, width - (r * 2) - 2, height);

		//test
		int x = 0;
		int y = r;
		int d = r - 1;

		while(y >= x)
		{
			this.drawLineY(xe + x, ys - y, ye + y);
			this.drawLineY(xe + y, ys - x, ye + x);
			this.drawLineY(xs - x, ys - y, ye + y);
			this.drawLineY(xs - y, ys - x, ye + x);

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

	
	/*
	 * Méthodes qui prennes en compte le coeficient multiplicateur
	*/
	
	/**
	 * Permet de dessiner un carré en fonction de size
	 * @param ox origine x
	 * @param oy origine y
	 * @param x	decalage en x pas de size
	 * @param y decalage en y pas de size
	 * @param size cof de size, taille du carré
	 */
	private void drawFontSquare(int ox, int oy, int x, int y, int size) {
		int endX = ox + ((x + 1) * size);
		int endY = oy + ((y + 1) * size);
		
		for (int yd = oy + (y * size); yd < endY; yd++) {//desine la hauteur du carré du pixel
			for (int xd = ox + (x * size); xd < endX; xd++) {//desine la largueur du carré du pixel
				this.drawPoint(xd, yd);
			}
		}
	}
	
	/**
	 * Méthode qui permet de desiner l'image d'un buffer, attention change la couleur et depends du cof, regarder {@link #setCof(byte)}
	 * @param ox coordonnée inital de l'image en partant de la gauche
	 * @param oy coordonnée inital de l'image en partant du haut
	 * @param img image sous forme de buffer
	 * @param widthBuf largeur de l'image
	 * @param heightBuf hauteur de l'image
	 */
	public void drawBuffer(int ox, int oy, byte[] img, int widthBuf, int heightBuf) {
		if ((widthBuf * heightBuf) != img.length) {
			throw new IllegalArgumentException("Image buffer don't match with gave width and gave height !");
		}
		byte size = this.cof;//taille des carré
		int i = 0;//indice du buffer
		byte color;
		
		for (int y = 0; y < heightBuf; y++) { //parcour en hauteur l'image
			for (int x = 0; x < widthBuf; x++) {//parcour en largeur l'image
				color = img[i];
				if (color < 0 || color >= 4) {//test si la couleur est transparente
					this.setColor(color);
					this.drawFontSquare(ox, oy, x, y, size);
				}
				i++;
			}
		}
	}
	
	/**
	 * Méthode qui permet de desiner une image, attention change la couleur et depends du cof, regarder {@link #setCof(byte)}
	 * @param ox coordonnée inital de l'image en partant de la gauche
	 * @param oy coordonnée inital de l'image en partant du haut
	 * @param img image desinable
	 */
	public void drawImage(int ox, int oy, DrawableImage img) {
		this.drawBuffer(ox, oy, img.getBuf(), img.getWidth(), img.getHeight());
	}
	
	/**
	 * Méthode qui permet de dessiner un caractère, regarder {@link #setCof(byte)}
	 * @param ox coordonnée du debut du caractère en partant de la gauche
	 * @param oy coordonnée du haut du caractère en partant du haut
	 * @param ch caractère a dessiner
	 * @return la largeur que le caractère dessiné prend
	*/
	public int drawChar(int ox, int oy, char ch) {
		CharacterSprite charS = MinecraftFont.Font.getChar(ch);
		int width = charS.getWidth();
		int height = charS.getHeight();
		byte size = this.cof;//taille des carré
		
		for (int y = 0; y < height; y++) { //parcour les lignes du caratère
			for (int x = 0; x < width; x++) {//parcour les colonnes des lignes du caratère
				if (charS.get(y, x)) {
					this.drawFontSquare(ox, oy, x, y, size);
				}
			}
		}
		
		return width * this.cof;
	}
	
	/**
	 * Méthode qui permet de dessiner une chaîne de caractères, regarder {@link #setCof(byte)}
	 * @param ox coordonnée du debut du texte en partant de la gauche
	 * @param oy coordonnée du haut du texte en partant du haut
	 * @param text texte qui sera dessiné
	 */
	public void drawText(int ox, int oy, String text) {
		byte size = this.cof;
		int x = ox;//decal x pour desinner chaque caratère
		int length = text.length();
		
		for (int i = 0; i < length; i++) {//parcours les caratères de text
			x += size + this.drawChar(x, oy, text.charAt(i));//dessine le caratère et decal pour le prochain
		}
	}
	
	/**
	 * Permet de retourner la largueur des textes en prennant en compte le cof
	 * @param text texte dont on souhaite la largueur
	 * @return la largueur du texte en focntion du cof
	 */
	public int getWidthText(String text) {
		return CalcCofMinecraftFont.getWidthText(text, this.cof);
  	}
  
	/**
	 * Permet de retourner la largueur d'un caratère en prennant en compte le cof
	 * @param ch caratère dont on souhaite savoir la largueur
	 * @return la largueur du texte en fonction du cof
	 */
	public int getWidthChar(char ch) {
		return CalcCofMinecraftFont.getWidthChar(ch, this.cof);
  	}
	
	/**
	 * Permet de retourner la hauteur des textes en prennant en compte le cof
	 * @return hauteur du texte en fonction du cof
	 */
	public int getHeightText() {
		return CalcCofMinecraftFont.getHeightText(this.cof);
	}
	
}
