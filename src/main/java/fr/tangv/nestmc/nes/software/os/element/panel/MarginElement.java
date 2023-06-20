package fr.tangv.nestmc.nes.software.os.element.panel;

import fr.tangv.nestmc.nes.software.os.element.Element;
import fr.tangv.nestmc.nes.software.os.element.border.Border;
import fr.tangv.nestmc.nes.software.os.element.border.EmptyBorder;
import org.apache.commons.lang.Validate;

/**
 * @author Tangv - https://tangv.fr
 * Element qui contient un autre pour lui faire une marge
 */
public class MarginElement extends ViewElement {
	
	protected int topMargin;
	protected int bottomMargin;
	protected int leftMargin;
	protected int rightMargin;
	
	/**
	 * Permet de construire une marge sans marge
	 * @param view element a qui on applique la marge
	 */
	public MarginElement(Element view) {
		this(view, 0);
	}
	
	/**
	 * Permet de construire une marge
	 * @param view element a qui on applique la marge
	 * @param margin l'épaisseur des marges
	 */
	public MarginElement(Element view, int margin) {
		this(view, margin, margin, margin, margin);
	}
	
	/**
	 * Permet de construire une marge
	 * @param view element a qui on applique la marge
	 * @param marginX marges horizontaux de l'element
	 * @param marginY marges verticaux de l'element
	 */
	public MarginElement(Element view, int marginX, int marginY) {
		this(view, marginY, marginY, marginX, marginX);
	}
	
	/**
	 * Permet de construire une marge
	 * @param view element a qui on applique la marge
	 * @param topMargin marge du haut de l'element
	 * @param bottomMargin marge du bas de l'element
	 * @param leftMargin marge gauche de l'element
	 * @param rightMargin marge droit de l'element
	 */
	public MarginElement(Element view, int topMargin, int bottomMargin, int leftMargin, int rightMargin) {
		super(view.getX(), view.getY(), view.getWidth(), view.getHeight(), (byte) 0, view);
		//test border
		Border bord = view.getBorder();
		if (bord != null) {
			this.setBorder(new EmptyBorder(bord.getTopBorder(), bord.getBottomBorder(), bord.getLeftBorder(), bord.getRightBorder()));
		}
		//border calc
		Validate.notNull(view, "View is null !");
		this.setMargin(topMargin, bottomMargin, leftMargin, rightMargin);
	}


	/**
	 * Permet de modifier les marges de l'element
	 * @param margin la nouvelle épaisseur des marges l'element
	 */
	public void setMargin(int margin) {
		this.topMargin = margin;
		this.bottomMargin = margin;
		this.leftMargin = margin;
		this.rightMargin = margin;
		this.updateSizeAndPosition();
	}
	
	/**
	 * Permet de modifier l'épaisseur des marges de l'element
	 * @param topMargin la nouvelle épaisseur de la marge du haut de l'element
	 * @param bottomMargin la nouvelle épaisseur de la marge du bas de l'element
	 * @param leftMargin la nouvelle épaisseur de la marge gauche de l'element
	 * @param rightMargin la nouvelle épaisseur de la marge droit de l'element
	 */
	public void setMargin(int topMargin, int bottomMargin, int leftMargin, int rightMargin) {
		this.topMargin = topMargin;
		this.bottomMargin = bottomMargin;
		this.leftMargin = leftMargin;
		this.rightMargin = rightMargin ;
		this.updateSizeAndPosition();
	}

	/**
	 * Permet de modifier les marges de l'element
	 * @param marginX la nouvelle épaisseur des marges horizontaux de l'element
	 * @param marginY la nouvelle épaisseur des marges verticaux de l'element
	 */
	public void setMargin(int xMargin, int yMargin) {
		this.topMargin = yMargin;
		this.bottomMargin = yMargin;
		this.leftMargin = xMargin;
		this.rightMargin = xMargin;
		this.updateSizeAndPosition();
	}
	
	/**
	 * Permet de récupérer l'épaisseur de la marge du haut de l'element
	 * @return l'épaisseur de la marge du haut de l'element
	 */
	public int getTopMargin() {
		return this.topMargin;
	}

	/**
	 * Permet de modifier l'épaisseur de la marge du haut de l'element
	 * @param topMargin la nouvelle épaisseur du haut du bas de l'element
	 */
	public void setTopMargin(int topMargin) {
		this.topMargin = topMargin;
		this.updateSizeAndPosition();
	}


	/**
	 * Permet de récupérer l'épaisseur de la marge du bas de l'element
	 * @return l'épaisseur de la marge du bas de l'element
	 */
	public int getBottomMargin() {
		return this.bottomMargin;
	}

	/**
	 * Permet de modifier l'épaisseur de la marge du bas de l'element
	 * @param bottomMargin la nouvelle épaisseur de la marge du bas de l'element
	 */
	public void setBottomMargin(int bottomMargin) {
		this.bottomMargin = bottomMargin;
		this.updateSizeAndPosition();
	}


	/**
	 * Permet de récupérer l'épaisseur de la marge gauche de l'element
	 * @return l'épaisseur de la marge gauche de l'element
	 */
	public int getLeftMargin() {
		return this.leftMargin;
	}

	/**
	 * Permet de modifier l'épaisseur de la marge gauche de l'element
	 * @param leftMargin la nouvelle épaisseur de la marge gauche de l'element
	 */
	public void setLeftMargin(int leftMargin) {
		this.leftMargin = leftMargin;
		this.updateSizeAndPosition();
	}


	/**
	 * Permet de récupérer l'épaisseur de la marge droit de l'element
	 * @return l'épaisseur de la marge droit de l'element
	 */
	public int getRightMargin() {
		return this.rightMargin;
	}

	/**
	 * Permet de modifier l'épaisseur de la marge droit de l'element
	 * @param rightMargin la nouvelle épaisseur de la marge droit de l'element
	 */
	public void setRightMargin(int rightMargin) {
		this.rightMargin = rightMargin;
		this.updateSizeAndPosition();
	}
	
	@Override
	public void updateSizeAndPosition() {
		Element view = this.getView();
		if (view != null) {
			view.setX(this.getX() + this.leftMargin);
			view.setY(this.getY() + this.topMargin);
			view.setWidth(this.getWidth() - this.leftMargin - this.rightMargin);
			view.setHeight(this.getHeight() - this.topMargin - this.bottomMargin);
			view.updateSizeAndPosition();
		}
	}
	
}
