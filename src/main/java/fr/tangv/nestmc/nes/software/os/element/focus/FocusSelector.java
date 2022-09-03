package fr.tangv.nestmc.nes.software.os.element.focus;

import java.util.Iterator;
import java.util.LinkedList;

import org.apache.commons.lang.Validate;

/**
 * @author Tangv - https://tangv.fr
 * Permet de selection a la suite des autre different focus element
 */
public class FocusSelector {

	private final LinkedList<FocusElement> list = new LinkedList<FocusElement>();
	private int selected = -1;
	
	/**
	 * Permet de savoir si un element est present dans le panel
	 * @param ele element tester
	 * @return true si l'element est present
	 */
	public boolean containsElement(FocusElement ele) {
		boolean find = false;
		
		Iterator<FocusElement> it = this.list.iterator();
		while (!find && it.hasNext()) {//parcours tous les elements
			if (it.next().equals(ele)) {
				find = true;
			}
		}
		
		return find;
	}
	
	/**
	 * Permet d'ajouter à la liste des selectionable un element
	 * @param ele element ajouter à la liste
	 */
	public void addFocus(FocusElement ele) {
		Validate.notNull(ele, "Added element can't be NULL !");
		//set on selector
		this.list.add(ele);
		if (this.list.size() == 1) {
			this.updateSelected(0);
		}
	}
	
	/**
	 * Permet d'enlever à la liste des selectionable un element
	 * @param ele element retirer à la liste
	 */
	public void removeFocus(FocusElement ele) {
		Validate.notNull(ele, "Removed element can't be NULL !");
		this.list.remove(ele);
		//set off selector
		if (this.list.isEmpty()) {
			this.updateSelected(-1);
		} else {
			this.updateSelected(0);
		}
	}
	
	/**
	 * Permet de changer l'element selectioner
	 * @param newSel nouvelle id de l'objet selection
	 */
	private void updateSelected(int newSel) {
		//disable old
		if (this.selected > -1) {
			this.list.get(this.selected).setFocus(false);
		}
		//enable new
		if (newSel > -1) {
			this.list.get(newSel).setFocus(true);
		}
		//affectation
		this.selected = newSel;
	}
	
	/**
	 * Permet de selectioner l'element suivant dans la liste, au bout de la liste on reviens au debut
	 */
	public void next() {
		int sel = this.selected;
		//test have element
		if (sel == -1) {
			throw new IllegalArgumentException("None element in selector !");
		}
		sel++;
		if (sel >= this.list.size()) {
			sel = 0;
		}
		
		this.updateSelected(sel);
	}
	
	/**
	 * Permet de selectioner l'element avant dans la liste, au bout de la liste on reviens à la fin
	 */
	public void back() {
		int sel = this.selected;
		//test have element
		if (sel == -1) {
			throw new IllegalArgumentException("None element in selector !");
		}
		sel--;
		if (sel < 0) {
			sel = this.list.size() - 1;
		}
		
		this.updateSelected(sel);
	}
	
}
