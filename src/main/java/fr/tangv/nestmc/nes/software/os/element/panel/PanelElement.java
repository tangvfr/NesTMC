package fr.tangv.nestmc.nes.software.os.element.panel;

import java.util.Iterator;
import java.util.LinkedList;

import fr.tangv.nestmc.nes.NesScreen;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.controller.InputController;
import fr.tangv.nestmc.nes.software.os.element.Element;
import fr.tangv.nestmc.nes.software.os.element.panel.manager.ElementManager;

/**
 * @author Tangv - https://tangv.fr
 * Element qui permet de regrouper plusieur autre elements
 */
public class PanelElement extends Element {

	private final LinkedList<ParamPanelElement> elements = new LinkedList<ParamPanelElement>();
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
		
		Iterator<ParamPanelElement> it = this.elements.iterator();
		while (it.hasNext()) {//fait l'update de tous les elements du panel
			it.next().getElement().update(nes, firstIn, secondIn, mixedIn);
		}
	}
	
	@Override
	public void render(TMCNes nes, NesScreen screen) {
		super.render(nes, screen);
		
		for (ParamPanelElement ele : this.elements) {//fait le rendu de tous les elements du panel
			ele.getElement().render(nes, screen);
		}
	}
	
	/**
	 * Permet d'ajouter un element avec comme paramètre 0
	 * Penser a definir votre gestionnaire d'alignement {@link #setManager(ElementManager)}
	 * @param ele element ajouté
	 */
	public void addElement(Element ele) {
		this.addElement(ele, 0);
	}
	
	/**
	 * Permet d'ajouter un element
	 * Penser a definir votre gestionnaire d'alignement {@link #setManager(ElementManager)}
	 * @param ele element ajouté
	 * @param param paramètre d'ajout
	 */
	public void addElement(Element ele, int param) {
		this.elements.add(new ParamPanelElement(ele, param));
		this.updateSizeAndPosition();
	}
	
	/**
	 * Permet d'enlever un element
	 * @param ele element supprimé
	 * @return true si l'element a bien été suprimé
	 * @deprecated peut provoqué des alignements incorrecte
	 */
	@SuppressWarnings("unlikely-arg-type")
	public boolean removeElement(Element ele) {
		boolean rm = this.elements.remove(ele);
		this.updateSizeAndPosition();
		return rm;
	}
	
	/**
	 * Permet d'obtenir le nombre d'element
	 * @return le nombre d'element
	 */
	public int countElement() {
		return this.elements.size();
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
		this.updateSizeAndPosition();
	}
	
	@Override
	public void updateSizeAndPosition() {
		if (this.manager != null) {
			this.manager.align(this.elements, this);
		}
		
		for (ParamPanelElement ele : this.elements) {
			ele.getElement().updateSizeAndPosition();
		}
	}
	
}
