package fr.tangv.nestmc.nes.software.os.element.focus;

import java.util.Iterator;
import java.util.LinkedList;

import fr.tangv.nestmc.draw.DrawableImage;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.controller.InputController;
import fr.tangv.nestmc.nes.software.os.element.Element;
import fr.tangv.nestmc.nes.software.os.element.ImageElement;
import fr.tangv.nestmc.nes.software.os.element.TextElement;
import fr.tangv.nestmc.nes.software.os.element.align.Aligns;
import fr.tangv.nestmc.nes.software.os.element.border.BasicBorder;
import fr.tangv.nestmc.nes.software.os.element.panel.ViewElement;

/**
 * @author Tangv - https://tangv.fr
 * Permet de crée un element qui peut être seelctionnner et executer
 */
public class FocusElement extends ViewElement {

	private final LinkedList<FocusAction> actions = new LinkedList<FocusAction>();
	private final byte unfocusBorderColor;
	private final byte focusBorderColor;
	private final byte unfocusBackgroundColor;
	private final byte focusBackgroundColor;
	private boolean isFocus = false;
	
	/**
	 * Permet de construire un element pouvant être focus
	 * @param x décalage horizontal
	 * @param y décalage vertical
	 * @param width largeur
	 * @param height hauteur
	 * @param background coleur de fond
	 */
	public FocusElement(int x, int y, int width, int height, byte unfocusBorderColor, byte focusBorderColor,
			byte unfocusBackgroundColor, byte focusBackgroundColor) {
		super(x, y, width, height, unfocusBackgroundColor);
		this.unfocusBorderColor = unfocusBorderColor;
		this.focusBorderColor = focusBorderColor;
		this.unfocusBackgroundColor = unfocusBackgroundColor;
		this.focusBackgroundColor = focusBackgroundColor;
		this.setHorizontalAlign(Aligns.CENTER);
		this.setVerticalAlign(Aligns.CENTER);
		this.setBorder(new BasicBorder(1, this.unfocusBorderColor));
	}
	
	@Override
	public void update(TMCNes nes, InputController firstIn, InputController secondIn, InputController mixedIn) {
		super.update(nes, firstIn, secondIn, mixedIn);
		int buttons;
		
		for (FocusAction ac : actions) {//pour tous els actions enregistrer
			buttons = ac.getButtons();
			
			if (mixedIn.isClicked(buttons)) {
				ac.getFocusExecution().execute(buttons, this, mixedIn, nes);
			}
		}
	}

	/**
	 * Permet de definir un texte dans l'object selectionable en créent un TextElement et en le mettant comme view
	 * @param str texte dans l'element
	 * @param color couleur du texte
	 * @param cof taille du texte (multiplicateur)
	 */
	public void setText(String str, byte color, byte cof) {
		TextElement te = new TextElement(0, 0, this.getWidth(), this.getHeight(), (byte) 0, str, color);
		te.setTextCof(cof);
		te.setHorizontalAlign(Aligns.CENTER);
		te.setVerticalAlign(Aligns.CENTER);
		this.setView(te);
	}
	
	/**
	 * Permet de definir une image dans l'object selectionable en créent un ImageElement et en le mettant comme view
	 * @param img image dans l'element
	 * @param cof taille de l'image (multiplicateur)
	 */
	public void setImage(DrawableImage img, byte cof) {
		ImageElement ie = new ImageElement(0, 0, this.getWidth(), this.getHeight(), (byte) 0, img);
		ie.setImgCof(cof);
		ie.setHorizontalAlign(Aligns.CENTER);
		ie.setVerticalAlign(Aligns.CENTER);
		this.setView(ie);
	}
	
	/**
	 * Permet de savoir si l'element est selection
	 * @return true si l'element est selection
	 */
	public boolean isFocus() {
		return this.isFocus;
	}

	/**
	 * Permet de modifier si l'element est selection
	 * @param isFocus true si l'element est selection
	 */
	public void setFocus(boolean isFocus) {
		this.isFocus = isFocus;
		((BasicBorder) this.getBorder()).setBorderColor(isFocus ? this.focusBorderColor : this.unfocusBorderColor);
		this.setBackground(isFocus ? this.focusBackgroundColor : this.unfocusBackgroundColor);
	}
	
	/**
	 * Permet d'ajout une action
	 * @param buttons les bouton pour activer l'action
	 * @param ac le listener a activier lorsque que laction se fait
	 */
	public void addAction(int buttons, FocusExecution ac) {
		this.actions.add(new FocusAction(buttons, ac));
	}
	
	/**
	 * Permet de retirer tout les action qui comme déclengueur
	 * @param buttons les boutons qui déclache l'action
	 */
	public void removeAction(int buttons) {
		Iterator<FocusAction> acs = this.actions.iterator();
		FocusAction ac;
		while (acs.hasNext()) {
			ac = acs.next();
			if (ac.getButtons() == buttons) {
				this.actions.remove(ac);
			}
		}
	}
	
	/**
	 * Permet de retirer tout les action qui comme déclengueur
	 * @param ac le listener a activier lorsque que laction se fait
	 */
	public void removeAction(FocusExecution acc) {
		Iterator<FocusAction> acs = this.actions.iterator();
		FocusAction ac;
		while (acs.hasNext()) {
			ac = acs.next();
			if (ac.getFocusExecution().equals(acc)) {
				this.actions.remove(ac);
			}
		}
	}
	
	@Override
	public void updateSizeAndPosition() {
		Element view = this.getView();
		
		if (view != null) {
			view.setHeight(this.getHeight());
			view.setWidth(this.getWidth());
			view.setX(this.getX());
			view.setY(this.getY());
		}
		
		super.updateSizeAndPosition();
	}
	
}
