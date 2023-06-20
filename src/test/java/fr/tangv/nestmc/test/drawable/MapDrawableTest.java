package fr.tangv.nestmc.test.drawable;

import fr.tangv.nestmc.draw.DrawableBuffer;
import fr.tangv.nestmc.palette.tool.DrawableTest;

public class MapDrawableTest {

	public static void main(String[] args) {
		DrawableBuffer dbuf = new DrawableBuffer(128, 192);
		DrawableTest.testDrawable(dbuf);
		MapBufferFrame frame = new MapBufferFrame(dbuf, dbuf.getWidth(), 4);
		frame.repaint();
	}
	
}
