package fr.tangv.nestmc.nes.software.os.element.panel.manager;

import java.util.List;

import org.apache.commons.lang.Validate;

import fr.tangv.nestmc.nes.software.os.element.Element;
import fr.tangv.nestmc.nes.software.os.element.border.Border;
import fr.tangv.nestmc.nes.software.os.element.panel.ParamPanelElement;

/**
 * @author Tangv - https://tangv.fr
 * Permet d'aligner des elements de façon uniforme
 */
public class DiviserElementManager implements ElementManager {
	
	/*
	 * Orientations
	 */
	public static final boolean HORIZONTAL = true;
	public static final boolean VERTICAL = false;
	
	private final boolean horizontal;//sense d'alignement
	private int space;//espac entre les element
	
	/**
	 * Permet de construire un aligneur divisseur
	 * @param horizontal true pour un aligneur horizontal, false pour un aligneur vertical
	 * @param space espacement entre les différent element
	 */
	public DiviserElementManager(boolean horizontal, int space) {
		Validate.isTrue(space >= 0, "Sapce can't be negative !", space);
		this.horizontal = horizontal;
		this.space = space;
	}
	
	/**
	 * Permet de construire un aligneur divisseur avec aucun esapacement
	 * @param horizontal true pour un aligneur horizontal, false pour un aligneur vertical
	 */
	public DiviserElementManager(boolean horizontal) {
		this(horizontal, 0);
	}
	
	@Override
	public void align(List<ParamPanelElement> list, Element container) {
		if (this.horizontal) {
			this.horizontalAlign(list, container);
		} else {
			this.verticalAlign(list, container);
		}
	}
	
	private void horizontalAlign(List<ParamPanelElement> list, Element container) {
		int height = container.getHeight();
		int x = container.getX();
		int y = container.getY();
		int countEle = list.size();//nombre d'element
		int rest = container.getWidth();
		int offset = 0;
		Element ele;//element traiter
		Border border;//bordure de l'element traité
		boolean first = true;
		
		//calcule du reste de pixel
		for (ParamPanelElement ppe: list) {
			ele = ppe.getElement();
			border = ele.getBorder();
			
			if (border != null) {
				rest -= border.calcXLength();//retire les constance
			}
		}
		
		if (countEle > 1) {
			rest -= (countEle - 1) * this.space;
		}
		
		//test si il y a la place
		if (rest <= 0) {
			throw new IllegalArgumentException("Container is too small !");
		} else if (countEle != 0) {
			rest /= countEle;//répartition des espacement
		}
		
		//mise a jour de la taille et position des element
		for (ParamPanelElement ppe: list) {
			ele = ppe.getElement();
			border = ele.getBorder();
			ele.setWidth(rest);
			
			//position et décalage
			if (border != null) {
				//second
				ele.setHeight(height - border.calcYLength());
				ele.setY(y + border.getTopBorder());
				//primary
				offset += border.getLeftBorder();
				ele.setX(x + offset);
				offset += ele.getWidth() + border.getRightBorder();
			} else {
				//second
				ele.setHeight(height);
				ele.setY(y);
				//primary
				ele.setX(x + offset);
				offset += ele.getWidth();
			}
			
			offset += this.space;
		}
	}
	
	private void verticalAlign(List<ParamPanelElement> list, Element container) {
		int width = container.getWidth();
		int x = container.getX();
		int y = container.getY();
		int countEle = list.size();//nombre d'element
		int rest = container.getHeight();
		int offset = 0;
		Element ele;//element traiter
		Border border;//bordure de l'element traité
		boolean first = true;
		
		//calcule du reste de pixel
		for (ParamPanelElement ppe: list) {
			if (first) {
				first = false;
			} else {
				rest -= this.space;
			}
			
			ele = ppe.getElement();
			border = ele.getBorder();
			
			if (border != null) {
				rest -= border.calcYLength();//retire les constance
			}
		}
		
		//test si il y a la place
		if (rest <= 0) {
			throw new IllegalArgumentException("Container is too small !");
		} else if (countEle != 0) {
			rest /= countEle;//répartition des espacement
		}
		
		//mise a jour de la taille et position des element
		for (ParamPanelElement ppe: list) {
			ele = ppe.getElement();
			border = ele.getBorder();
			ele.setHeight(rest);
			
			//position et décalage
			if (border != null) {
				//second
				ele.setWidth(width - border.calcXLength());
				ele.setX(x + border.getLeftBorder());
				//primary
				offset += border.getTopBorder();
				ele.setY(y + offset);
				offset += ele.getHeight() + border.getLeftBorder();
			} else {
				//second
				ele.setWidth(width);
				ele.setX(x);
				//primary
				ele.setY(y + offset);
				offset += ele.getHeight();
			}
			
			offset += this.space;
		}
	}

	/**
	 * Permet de récupérer l'espace entre les element
	 * @return l'espace entre les element
	 */
	public int getSpace() {
		return this.space;
	}

	/**
	 * Permet de modifier l'espace entre les element
	 * @param space le nouvlle espace entre les element
	 */
	public void setSpace(int space) {
		this.space = space;
	}
	
}
