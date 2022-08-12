package fr.tangv.nestmc.nes.software.os.element.panel;

import java.util.Iterator;
import java.util.LinkedList;

import fr.tangv.nestmc.nes.NesScreen;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.controller.InputController;
import fr.tangv.nestmc.nes.software.os.element.Element;

/**
 * @author Tangv - https://tangv.fr
 * Element qui permet de regrouper plusieur autre elements
 */
public class PanelElement extends Element {

	private final LinkedList<Element> elements = new LinkedList<Element>();
	private ElementManager manager = null;
	
	/**
	 * Permet de construire un regroupeur d'element
	 * @param x décalage horizontal
	 * @param y décalage vertical
	 * @param width largeur
	 * @param height hauteur
	 * @param background coleur de fond
	 */
	public PanelElement(int x, int y, int width, int height, byte background) {
		super(x, y, width, height, background);
	}
	
	/**
	 * Permet de construire un regroupeur d'element
	 * @param x décalage horizontal
	 * @param y décalage vertical
	 * @param width largeur
	 * @param height hauteur
	 * @param background coleur de fond
	 * @param manager le gestionaire d'ajencement des elements
	 */
	public PanelElement(int x, int y, int width, int height, byte background, ElementManager manager) {
		super(x, y, width, height, background);
		this.manager = manager;
	}
	
	@Override
	public void update(TMCNes nes, InputController firstIn, InputController secondIn, InputController mixedIn) {
		super.update(nes, firstIn, secondIn, mixedIn);
		
		Iterator<Element> it = this.elements.iterator();
		while (it.hasNext()) {//fait l'update de tous les elements du panel
			it.next().update(nes, firstIn, secondIn, mixedIn);
		}
	}
	
	@Override
	public void render(TMCNes nes, NesScreen screen) {
		super.render(nes, screen);
		
		for (Element ele : this.elements) {//fait le rendu de tous les elements du panel
			ele.render(nes, screen);
		}
	}
	
	/**
	 * Permet d'ajouter un element
	 * @param ele element ajouté
	 */
	public void addElement(Element ele) {
		this.elements.add(ele);
		if (this.manager != null) {
			//this.manager.
		}
	}
	
	/**
	 * Permet d'enlever un element
	 * @param ele element supprimé
	 */
	public void removeElement(Element ele) {
		this.elements.remove(ele);
		if (this.manager != null) {
			//this.manager.
		}
	}

	/**
	 * Permet de récupérer le gestionaire d'ajencement des elements
	 * @return le gestionaire d'ajencement des elements
	 */
	public ElementManager getManager() {
		return this.manager;
	}
	
	/**
	 * Permet de modifier le gestionaire d'ajencement des elements
	 * @return le nouveau gestionaire d'ajencement des elements
	 */
	public void setManager(ElementManager manager) {
		this.manager = manager;
	}
	
}
