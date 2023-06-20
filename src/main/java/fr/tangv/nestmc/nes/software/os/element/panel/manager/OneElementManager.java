package fr.tangv.nestmc.nes.software.os.element.panel.manager;

import fr.tangv.nestmc.nes.software.os.element.Element;
import fr.tangv.nestmc.nes.software.os.element.border.Border;
import fr.tangv.nestmc.nes.software.os.element.panel.ParamPanelElement;

import java.util.List;

/**
 * @author Tangv - https://tangv.fr
 *
 */
public class OneElementManager implements ElementManager {

	@Override
	public void align(List<ParamPanelElement> elements, Element container) {
		if (elements.size() >= 1) {
			Element ele = elements.get(0).getElement();
			int width = container.getWidth();
			int height = container.getHeight();
			int x = container.getX();
			int y = container.getY();
			//set border
			Border border = ele.getBorder();
			if (border != null) {
				ele.setWidth(width - border.calcXLength());
				ele.setX(x + border.getLeftBorder());
				ele.setHeight(height - border.calcYLength());
				ele.setY(y + border.getTopBorder());
			} else {
				ele.setWidth(width);
				ele.setX(x);
				ele.setHeight(height);
				ele.setY(y);
			}
		}
	}

}
