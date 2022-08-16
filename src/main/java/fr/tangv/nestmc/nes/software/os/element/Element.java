package fr.tangv.nestmc.nes.software.os.element;

import fr.tangv.nestmc.nes.NesScreen;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.controller.InputController;
import fr.tangv.nestmc.nes.software.TMCNesInteractor;
import fr.tangv.nestmc.nes.software.os.element.border.Border;

/**
 * @author Tangv - https://tangv.fr
 * Element de base des gui d'os
 */
public abstract class Element implements TMCNesInteractor {

	/**
	 * Permet de tester si une couleur est invisible ou non
	 * @param color la couleur tester
	 * @return true si la couleur est invisible
	 */
	public static boolean colorIsInvisible(byte color) {
		return color >= 0 && color < 4;
	}
	
	private int x;
	private int y;
	private int width;
	private int height;
	private byte background;
	private Border border = null;
	
	/**
	 * Permet de construire un element de base
	 * @param x décalage horizontal
	 * @param y décalage vertical
	 * @param width largeur
	 * @param height hauteur
	 * @param background coleur de fond
	 */
	public Element(int x, int y, int width, int height, byte background) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.background = background;
	}

	@Override
	public void update(TMCNes nes, InputController firstIn, InputController secondIn, InputController mixedIn) {}

	@Override
	public void render(TMCNes nes, NesScreen screen) {
		if (!Element.colorIsInvisible(this.background)) {//le fond n'est pas transparent
			screen.setColor(this.background);
			screen.fillRect(this.x, this.y, this.width, this.height);
		}
		if (this.border != null) {
			this.border.render(this, screen);
		}
	}

	/**
	 * Permet de récupérer le x de l'element
	 * @return le x de l'element
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Permet de récupérer le y de l'element
	 * @return le y de l'element
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Permet de récupérer la largeur de l'element
	 * @return la largeur de l'element
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Permet de récupérer la hauteur de l'element
	 * @return la hauteur de l'element
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Permet de récupérer le fond de l'element
	 * @return le fond de l'element
	 */
	public byte getBackground() {
		return this.background;
	}

	/**
	 * Permet de modifier le x de l'element
	 * @param x le nouveau x de l'element
	 */
	public void setX(int x) {
		this.x = x;
		this.updateSizeAndPosition();
	}

	/**
	 * Permet de modifier le y de l'element
	 * @param y le nouveau y de l'element
	 */
	public void setY(int y) {
		this.y = y;
		this.updateSizeAndPosition();
	}

	/**
	 * Permet de modifier la largeur de l'element
	 * @param width la nouvelle largeur de l'element
	 */
	public void setWidth(int width) {
		this.width = width;
		this.updateSizeAndPosition();
	}

	/**
	 * Permet de modifier la hauteur de l'element
	 * @param height la nouvelle hauteur de l'element
	 */
	public void setHeight(int height) {
		this.height = height;
		this.updateSizeAndPosition();
	}

	/**
	 * Permet de modifier le fond de l'element
	 * @param background le nouveau fond de l'element
	 */
	public void setBackground(byte background) {
		this.background = background;
	}

	/**
	 * Permet de récupérer la bordure de l'element
	 * @return la bordure de l'element, null pour aucune
	 */
	public Border getBorder() {
		return this.border;
	}

	/**
	 * Permet de modifier la bordure de l'element
	 * @param border la nouvelle bordure de l'element, null pour aucune
	 */
	public void setBorder(Border border) {
		this.border = border;
		this.updateSizeAndPosition();
	}
	
	/*
	 * Permet de mettre à jour la taille et la position des elements enfant
	 */
	public abstract void updateSizeAndPosition();

}
