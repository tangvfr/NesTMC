package fr.tangv.nestmc.nes.software.os.element.panel.manager;

import java.util.List;

import fr.tangv.nestmc.nes.software.os.element.Element;
import fr.tangv.nestmc.nes.software.os.element.border.Border;
import fr.tangv.nestmc.nes.software.os.element.panel.ParamPanelElement;

/**
 * @author Tangv - https://tangv.fr
 * Permet d'aligner des element en prenent une petit partie en fonction de la taille de l'element ou le faire prendre un max de place dans un même axe
 */
public class FullerElementManager extends ElementManager {

	/**
	 * Fait prendre le maximum de place restante
	 */
	public static final int MAX_SIZE = 0;//par default c'est 0 le paramètre
	/**
	 * Fait prendre la place minium néssesaire
	 */
	public static final int MIN_SIZE = 1;
	
	/*
	 * Orientations
	 */
	public static final boolean HORIZONTAL = true;
	public static final boolean VERTICAL = false;
	
	
	private final boolean horizontal;//sense d'alignement
	
	/**
	 * Permet de construire un aligneur rempliseur
	 * @param horizontal true pour un aligneur horizontal, false pour un aligneur vertical
	 */
	public FullerElementManager(boolean horizontal) {
		this.horizontal = horizontal;
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
		int freeEle = 0;//element en full
		int rest = container.getWidth();
		int offset = 0;
		Element ele;//element traiter
		Border border;//bordure de l'element traité
		
		//calcule du reste de pixel
		for (ParamPanelElement ppe: list) {
			ele = ppe.getElement();
			
			if (ppe.getParam() == FullerElementManager.MIN_SIZE) {//taille constante X
				rest -= ele.getWidth();//retire les constance
			} else {
				freeEle++;
			}
			
			border = ele.getBorder();
			if (border != null) {
				rest -= border.calcXLength();//retire les constance
			}
		}
		
		//test si il y a la place
		if (rest <= 0) {
			throw new IllegalArgumentException("Container is too small !");
		} else if (freeEle != 0) {
			rest /= freeEle;//répartition de l'espace restant
		}
		
		//mise a jour de la taille et position des element
		for (ParamPanelElement ppe: list) {
			ele = ppe.getElement();
			border = ele.getBorder();
			
			if (ppe.getParam() != FullerElementManager.MIN_SIZE) {//taille en fonction du reste
				ele.setWidth(rest);
			}
			
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
		}
	}
	
	private void verticalAlign(List<ParamPanelElement> list, Element container) {
		int width = container.getWidth();
		int x = container.getX();
		int y = container.getY();
		int freeEle = 0;//element en full
		int rest = container.getHeight();
		int offset = 0;
		Element ele;//element traiter
		Border border;//bordure de l'element traité
		
		//calcule du reste de pixel
		for (ParamPanelElement ppe: list) {
			ele = ppe.getElement();
			
			if (ppe.getParam() == FullerElementManager.MIN_SIZE) {//taille constante X
				rest -= ele.getHeight();//retire les constance
			} else {
				freeEle++;
			}
			
			border = ele.getBorder();
			if (border != null) {
				rest -= border.calcYLength();//retire les constance
			}
		}
		
		//test si il y a la place
		if (rest <= 0) {
			throw new IllegalArgumentException("Container is too small !");
		} else if (freeEle != 0) {
			rest /= freeEle;//répartition de l'espace restant
		}
		
		//mise a jour de la taille et position des element
		for (ParamPanelElement ppe: list) {
			ele = ppe.getElement();
			border = ele.getBorder();
			
			if (ppe.getParam() != FullerElementManager.MIN_SIZE) {//taille en fonction du reste
				ele.setHeight(rest);
			}
			
			//position et décalage
			if (border != null) {
				//second
				ele.setWidth(width - border.calcXLength());
				ele.setX(x + border.getLeftBorder());
				//primary
				offset += border.getTopBorder();
				ele.setY(y + offset);
				offset += ele.getHeight() + border.getBottomBorder();
			} else {
				//second
				ele.setWidth(width);
				ele.setX(x);
				//primary
				ele.setY(y + offset);
				offset += ele.getHeight();
			}
		}
	}
	
}
