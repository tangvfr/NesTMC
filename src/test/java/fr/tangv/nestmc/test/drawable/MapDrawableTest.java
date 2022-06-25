package fr.tangv.nestmc.test.drawable;

import fr.tangv.nestmc.draw.MapScreen;
import fr.tangv.nestmc.palette.tool.DrawableTest;

public class MapDrawableTest {

	public static void main(String[] args) {
		MapScreen map = new MapScreen();
		DrawableTest.testDrawable(map);
		MapBufferFrame frame = new MapBufferFrame(map, 4);
		frame.repaint();
	}
	
}
