package fr.tangv.nestmc.nes.software.os.element.focus;

import fr.tangv.nestmc.draw.DrawableImage;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.controller.InputController;
import fr.tangv.nestmc.nes.software.os.color.FocusColors;
import fr.tangv.nestmc.nes.software.os.element.Element;
import fr.tangv.nestmc.nes.software.os.element.ImageElement;
import fr.tangv.nestmc.nes.software.os.element.align.Aligns;
import fr.tangv.nestmc.nes.software.os.element.border.BasicBorder;
import fr.tangv.nestmc.nes.software.os.element.border.Border;
import fr.tangv.nestmc.nes.software.os.element.border.ColoredBorder;
import fr.tangv.nestmc.nes.software.os.element.border.RoundBorder;
import fr.tangv.nestmc.nes.software.os.element.panel.ViewElement;
import fr.tangv.nestmc.nes.software.os.element.text.TextElement;

import java.util.Iterator;
import java.util.LinkedList;

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
	 * @param unfocusBorderColor la couleur du bord quand deselectionner
	 * @param focusBorderColor la couleur du bord quand selectionner
	 * @param unfocusBackgroundColor la couleur du fond quand deselectionner
	 * @param focusBackgroundColor la couleur du fond quand selectionner
	 * @param round l'arrondie
	 */
	public FocusElement(int x, int y, int width, int height, byte unfocusBorderColor, byte focusBorderColor,
			byte unfocusBackgroundColor, byte focusBackgroundColor, int round) {
		super(x, y, width, height, unfocusBackgroundColor);
		this.unfocusBorderColor = unfocusBorderColor;
		this.focusBorderColor = focusBorderColor;
		this.unfocusBackgroundColor = unfocusBackgroundColor;
		this.focusBackgroundColor = focusBackgroundColor;
		this.setHorizontalAlign(Aligns.CENTER);
		this.setVerticalAlign(Aligns.CENTER);
		//l'arrondie
		Border border;
		if (round > 0) {
			this.setRound(round);
			border = new RoundBorder(1, this.unfocusBorderColor, round);
		} else {
			border = new BasicBorder(1, this.unfocusBorderColor);
		}
		this.setBorder(border);
	}
	
	/**
	 * Permet de construire un element pouvant être focus
	 * @param x décalage horizontal
	 * @param y décalage vertical
	 * @param width largeur
	 * @param height hauteur
	 * @param color les couleurs pour les elements selectionnables
	 * @param round l'arrondie
	 */
	public FocusElement(int x, int y, int width, int height, FocusColors color, int round) {
		this(	x,
				y,
				width,
				height,
				color.getUnfocusBorder(),
				color.getFocusBorder(),
				color.getUnfocusBackground(),
				color.getFocusBackground(),
				round
				);
	}

	/**
	 * Permet de construire un element pouvant être focus
	 * @param x décalage horizontal
	 * @param y décalage vertical
	 * @param width largeur
	 * @param height hauteur
	 * @param unfocusBorderColor la couleur du bord quand deselectionner
	 * @param focusBorderColor la couleur du bord quand selectionner
	 * @param unfocusBackgroundColor la couleur du fond quand deselectionner
	 * @param focusBackgroundColor la couleur du fond quand selectionner
	 */
	public FocusElement(int x, int y, int width, int height, byte unfocusBorderColor, byte focusBorderColor,
						byte unfocusBackgroundColor, byte focusBackgroundColor) {
		this(	x,
				y,
				width,
				height,
				unfocusBorderColor,
				focusBorderColor,
				unfocusBackgroundColor,
				focusBackgroundColor,
				0
		);
	}

	/**
	 * Permet de construire un element pouvant être focus
	 * @param x décalage horizontal
	 * @param y décalage vertical
	 * @param width largeur
	 * @param height hauteur
	 * @param color les couleurs pour les elements selectionnables
	 */
	public FocusElement(int x, int y, int width, int height, FocusColors color) {
		this(	x,
				y,
				width,
				height,
				color,
				0
		);
	}

	/**
	 * Permet de construire un element pouvant être focus avec toutes les dimensions et placement a 0
	 * @param color les couleurs pour les elements selectionnables
	 * @param round l'arrondie
	 */
	public FocusElement(FocusColors color, int round) {
		this(	0,
				0,
				0,
				0,
				color.getUnfocusBorder(),
				color.getFocusBorder(),
				color.getUnfocusBackground(),
				color.getFocusBackground(),
				round
		);
	}

	/**
	 * Permet de construire un element pouvant être focus avec toutes les dimensions, placement et round a 0
	 * @param color les couleurs pour les elements selectionnables
	 */
	public FocusElement(FocusColors color) {
		this(color, 0);
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
		((ColoredBorder) this.getBorder()).setBorderColor(isFocus ? this.focusBorderColor : this.unfocusBorderColor);
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
