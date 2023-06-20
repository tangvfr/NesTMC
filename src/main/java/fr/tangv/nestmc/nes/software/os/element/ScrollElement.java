package fr.tangv.nestmc.nes.software.os.element;

import fr.tangv.nestmc.nes.NesScreen;
import fr.tangv.nestmc.nes.TMCNes;
import org.apache.commons.lang.Validate;

/**
 * @author Tangv - https://tangv.fr
 * Permet d'afficher une bar de scroll
 */
public class ScrollElement extends Element {

	private int scrollerLength;//pour dessinage hauteur du scroll
	private int scrollerOffset;//pour le décalage du scroll
	private int minScroller = 8;
	
	private int maxScroll = 1;
	private int scroll;
	
	private byte color1;
	private byte color2;
	
	/**
	 * Permet de construire un element qui montre une bar de scroll
	 * @param x décalage horizontal
	 * @param y décalage vertical
	 * @param width largeur
	 * @param height hauteur
	 * @param background coleur de fond
	 * @param scroll index du scroll
	 * @param maxScroll maxiume exclu de scroll possible
	 * @param color1 couleur de la premier bar du scroller
	 * @param color2 couleur de la deuxieme bar du scroller
	 */
	public ScrollElement(int x, int y, int width, int height, byte background, int scroll, int maxScroll, byte color1, byte color2) {
		super(x, y, width, height, background);
		this.setMaxScroll(maxScroll);
		this.setScroll(scroll);
		this.scroll = scroll;
		this.color1 = color1;
		this.color2 = color2;
	}

	@Override
	public void render(TMCNes nes, NesScreen screen) {
		super.render(nes, screen);

		int w = this.getWidth();
		int x = this.getX();
		int x2 =  x + w - 1;
		
		int oy = this.getY() + (this.scroll * this.scrollerOffset);
		
		for (int i = 0; i < this.scrollerLength; i++) {
			screen.setColor((i % 2 == 0) ? this.color1 : this.color2);
			screen.drawLineX(x, oy, x2);
			oy++;
		}
	}
	
	@Override
	public void updateSizeAndPosition() {
		if (this.maxScroll <= 1) {
			this.scrollerLength = this.getHeight();
			this.scrollerOffset = 0;
		} else {
			this.scrollerLength = this.getHeight() / this.maxScroll;
			
			if (this.scrollerLength >= this.minScroller) {
				this.scrollerOffset = this.scrollerLength;
			} else {
				this.scrollerLength = this.minScroller;
				this.scrollerOffset = (this.getHeight() - this.minScroller) / (this.maxScroll - 1);
			}
			
			//pour pas avoir de blanc en bas
			if (this.scrollerOffset > 0) {
				System.out.println(this.getHeight() - (this.scrollerOffset * (this.maxScroll - 1) + this.scrollerLength));
				this.scrollerLength += (this.getHeight() - (this.scrollerOffset * (this.maxScroll - 1) + this.scrollerLength));
			}
		}
	}

	//a refaire en bas
	
	/**
	 * Permet de récupérer le minScroller de ScrollElement.java
	 * @return le minScroller de ScrollElement.java
	 */
	public int getMinScroller() {
		return this.minScroller;
	}

	/**
	 * Permet de modifier le minScroller de ScrollElement.java
	 * @param minScroller le nouveau minScroller de ScrollElement.java
	 */
	public void setMinScroller(int minScroller) {
		this.minScroller = minScroller;
		this.updateSizeAndPosition();
	}

	/**
	 * Permet de récupérer le maxScroll de ScrollElement.java
	 * @return le maxScroll de ScrollElement.java
	 */
	public int getMaxScroll() {
		return this.maxScroll;
	}

	/**
	 * Permet de modifier le maxScroll de ScrollElement.java
	 * @param maxScroll le nouveau maxScroll de ScrollElement.java
	 */
	public void setMaxScroll(int maxScroll) {
		Validate.isTrue(maxScroll >= 0, "Max scroll value is too low !", maxScroll);
		this.maxScroll = maxScroll;
		this.updateSizeAndPosition();
	}

	/**
	 * Permet de récupérer le scroll de ScrollElement.java
	 * @return le scroll de ScrollElement.java
	 */
	public int getScroll() {
		Validate.isTrue(scroll >= 0 && scroll < this.maxScroll, "Invalid scroll value !", scroll);
		return this.scroll;
	}

	/**
	 * Permet de modifier le scroll de ScrollElement.java
	 * @param scroll le nouveau scroll de ScrollElement.java
	 */
	public void setScroll(int scroll) {
		this.scroll = scroll;
	}
	
	/**
	 * Permet d'incrementer le scroll de 1
	 */
	public void incScroll() {
		this.incScroll(1);
	}
	
	/**
	 * Permet d'incrementer le scroll
	 * @param inc incrmentation
	 */
	public void incScroll(int inc) {
		Validate.isTrue(inc > 0, "Incrment <= 0 !", inc);
		this.scroll += inc;
		if (this.scroll >= this.maxScroll) {
			this.scroll = this.maxScroll - 1;
		}
	}
	
	/**
	 * Permet de decrementer le scroll de 1
	 */
	public void decScroll() {
		this.decScroll(1);
	}
	
	/**
	 * Permet de decrementer le scroll
	 * @param dec decrementation
	 */
	public void decScroll(int dec) {
		Validate.isTrue(dec > 0, "Decrement <= 0 !", dec);
		this.scroll -= dec;
		if (this.scroll < 0) {
			this.scroll = 0;
		}
	}

	/**
	 * Permet de récupérer le color1 de ScrollElement.java
	 * @return le color1 de ScrollElement.java
	 */
	public byte getColor1() {
		return this.color1;
	}

	/**
	 * Permet de modifier le color1 de ScrollElement.java
	 * @param color1 le nouveau color1 de ScrollElement.java
	 */
	public void setColor1(byte color1) {
		this.color1 = color1;
	}

	/**
	 * Permet de récupérer le color2 de ScrollElement.java
	 * @return le color2 de ScrollElement.java
	 */
	public byte getColor2() {
		return this.color2;
	}

	/**
	 * Permet de modifier le color2 de ScrollElement.java
	 * @param color2 le nouveau color2 de ScrollElement.java
	 */
	public void setColor2(byte color2) {
		this.color2 = color2;
	}
	
}
