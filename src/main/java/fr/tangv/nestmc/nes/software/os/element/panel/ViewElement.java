package fr.tangv.nestmc.nes.software.os.element.panel;

import fr.tangv.nestmc.nes.NesScreen;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.controller.InputController;
import fr.tangv.nestmc.nes.software.os.element.AlignedElement;
import fr.tangv.nestmc.nes.software.os.element.Element;

/**
 * @author Tangv - https://tangv.fr
 * Permet d'aligner un element d'une certaine façon
 */
public class ViewElement extends AlignedElement {

	private Element view;

	/**
	 * Permet de construire un afficheur d'un element
	 * @param x décalage horizontal
	 * @param y décalage vertical
	 * @param width largeur
	 * @param height hauteur
	 * @param background coleur de fond
	 * @param view l'element afficher
	 */
	public ViewElement(int x, int y, int width, int height, byte background, Element view) {
		super(x, y, width, height, background);
		this.view = view;
	}
	
	/**
	 * Permet de construire un afficheur d'un element avec aucun element afficher
	 * @param x décalage horizontal
	 * @param y décalage vertical
	 * @param width largeur
	 * @param height hauteur
	 * @param background coleur de fond
	 */
	public ViewElement(int x, int y, int width, int height, byte background) {
		this(x, y, width, height, background, null);
	}
	
	@Override
	public void update(TMCNes nes, InputController firstIn, InputController secondIn, InputController mixedIn) {
		super.update(nes, firstIn, secondIn, mixedIn);
		
		if (this.view != null) {
			this.view.update(nes, firstIn, secondIn, mixedIn);
		}
	}
	
	@Override
	public void render(TMCNes nes, NesScreen screen) {
		super.render(nes, screen);
		
		if (this.view != null) {
			this.view.render(nes, screen);
		}
	}
	
	/**
	 * Permet de récupérer l'element afficher
	 * @return l'element afficher
	 */
	public Element getView() {
		return this.view;
	}

	/**
	 * Permet de modifier l'element afficher
	 * @param view le nouveau element afficher
	 */
	public void setView(Element view) {
		this.view = view;
		this.updateSizeAndPosition();
	}
	
	@Override
	public void updateSizeAndPosition() {
		if (this.view != null) {
			this.view.setX(this.getX() + this.getHorizontalAlign().calcOffset(
					this.getWidth(),
					this.view.getWidth()
					));
			
			this.view.setY(this.getY() + this.getVerticalAlign().calcOffset(
					this.getHeight(),
					this.view.getHeight()
					));
			
			this.view.updateSizeAndPosition();
		}
	}
	
}
