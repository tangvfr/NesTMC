package fr.tangv.nestmc.palette.tool;

import fr.tangv.nestmc.draw.Drawable;
import fr.tangv.nestmc.palette.v1_8.MapColorV1_8;

public class DrawableTest {

	public static void testDrawable(Drawable draw) {
		draw.clearScreen(MapColorV1_8.BLACK_SHADOW);
		
		System.out.println("height: " + draw.getHeight());
		System.out.println("width: " + draw.getWidth());
		System.out.println("height text: " + draw.getHeightText());
		System.out.println("width text 'he': " + draw.getWidthText("hs"));
		
		//setcolor
		draw.setColor(MapColorV1_8.EMERALD_LIGTH);
		
		draw.drawPoint(1, 1);
		//test draw Y
		draw.drawLineY(3, 1, 6);
		draw.drawLineY(5, 10, 6);
		//test draw X
		draw.drawLineX(7, 1, 12);
		draw.drawLineX(12, 3, 8);
		
		//test drawLine
		draw.drawLine(12, 10, 1, 16);
		
		//setcolor
		draw.setColor(MapColorV1_8.LAVA_LIGTH);
	
		//test draw circle
		draw.drawCircle(24, 8, 7);
		draw.fillCircle(40, 8, 7);
		
		//setcolor
		draw.setColor(MapColorV1_8.DIAMOND_LIGTH);
		
		//test draw rect
		draw.drawRect(50, 1, 3, 3);
		draw.drawRect(53, 4, 4, 4);
		draw.drawRect(57, 8, 5, 5);
		draw.fillRect(64, 1, 8, 16);
		
		//setcolor
		draw.setColor((byte) MapColorV1_8.WATER_DARK);
		
		//test draw in rect
		draw.fillRect(60, 1, 3, 3);
		draw.drawRect(64, 1, 5, 5);
		draw.drawPoint(66, 3);
		draw.drawPoint(69, 3);
		draw.drawPoint(71, 3);
		
		//setcolor
		draw.setColor(MapColorV1_8.GOLD_LIGTH);
		
		//test draw char
		draw.drawChar(8, 16, 'A');
		draw.drawChar(16, 16, 'B');
		draw.drawChar(24, 16, 'c');
		draw.drawChar(32, 16, 'd');
		
		//test font
		draw.setCof((byte) 2);//set cof 2
		draw.drawChar(8, 24, '@');
		draw.setCof((byte) 3);//set cof 3
		draw.drawChar(24, 24, '{');
		draw.setCof((byte) 4);//set cof 4
		draw.drawChar(48, 24, '}');
		System.out.println("height 4 text: " + draw.getHeightText());
		System.out.println("height 4 text 'he': " + draw.getWidthText("he"));
		draw.setCof((byte) 5);//set cof 5
		draw.drawChar(73, 1, '$');
		
		//test buf
		draw.setCof((byte) 1);//reset cof
		byte[] miniBuf = new byte[] {//5x3
				(byte) 78, (byte) 79, (byte) 78, (byte) 79, (byte) 79,
				(byte) 79, (byte) 78, (byte) 78, (byte) 78, (byte) 79,
				(byte) 79, (byte) 79, (byte) 78, (byte) 79, (byte) 78,
		};
		draw.drawBuffer(76, 10, miniBuf, 5, 3);
		
		//test buffer 480
		byte[] testBuf = new byte[] {//30x16
			(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 130, (byte) 130, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 126, (byte) 0, (byte) 0, (byte) 131, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 130, (byte) 130, (byte) 131, (byte) 131, (byte) 131, (byte) 130, (byte) 126, (byte) 130, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 126, (byte) 0, (byte) 0, (byte) 131, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 130, (byte) 126, (byte) 126, (byte) 126, (byte) 130, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 126, (byte) 0, (byte) 0, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 130, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 126, (byte) 126, (byte) 126, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 130, (byte) 130, (byte) 126, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 130, (byte) 130, (byte) 130, (byte) 130, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 130, (byte) 130, (byte) 130, (byte) 130, (byte) 130, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 130, (byte) 130, (byte) 130, (byte) 130, (byte) 130, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, 
		};
		draw.drawBuffer(90, 8, testBuf, 30, 16);
		//test buffer font 2
		draw.setCof((byte) 2);
		draw.drawBuffer(65, 33, testBuf, 30, 16);
		
		//reset cof
		draw.setCof((byte) 1);
		
		//setcolor
		draw.setColor((byte) MapColorV1_8.WATER_LIGTH);
		draw.drawLineX(0, 60, 60);
		draw.drawLineX(0, 62, 62);
		
		draw.setColor(MapColorV1_8.EMERALD_NORMAL);
		draw.drawLineX(0, 64, 64);
		//test drawText
		draw.setColor(MapColorV1_8.SNOW_LIGTH);
		draw.drawText(0, 64, "Hello, world !");
		//test cof 2
		draw.setCof((byte) 2);
		draw.setColor(MapColorV1_8.LAVA_NORMAL);
		draw.drawText(0, 72, "Hello, world !");
		
		//cof null
		draw.setCof((byte) 0);
		draw.drawText(0, 0, "Hello, world !");
		
		//reset cof
		draw.setCof((byte) 1);
		
		//test all char
		draw.setColor(MapColorV1_8.SAND_NORMAL);
		draw.drawText(0, 88, "! \"#$%&'()*+,-./01234567");
		draw.drawText(0, 96, "89:;<=>?@LMNOPhijkl{|}~'[");
		draw.drawText(0, 104, "\\]^_@àçéÉëêè");
		
		//all character
		//" !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_'abcdefghijklmnopqrstuvwxyz{|}~\u837f\u83c7\u83fc\u83e9\u83e2\u83e4\u83e0\u83e5\u83e7\u83ea\u83eb\u83e8\u83ef\u83ee\u83ec\u83c4\u83c5\u83c9\u83e6\u83c6\u83f4\u83f6\u83f2\u83fb\u83f9\u83ff\u83d6\u83dc\u83f8£\u83d8\u83d7\u0191\u83e1\u83ed\u83f3\u83fa\u83f1\u83d1ªº¿®¬½¼¡«»"
	}
	
}
