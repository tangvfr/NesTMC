package fr.tangv.nestmc.test;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.bukkit.map.MapPalette;

import fr.tangv.nestmc.palette.MapColor;
import fr.tangv.nestmc.screen.MapBuffer;
import fr.tangv.nestmc.screen.MapDrawable;

public class DrawableTest {
	
	public static void main(String[] args) {
		MapDrawable map = new MapDrawable();
		
		JFrame frame = new JFrame("Drawable Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pan = new JPanel() {
			private static final long serialVersionUID = 1L;
			
			@SuppressWarnings("deprecation")
			@Override
			public void paint(Graphics g) {
				byte[] buf = map.getBuffer();
				int i = 0;
				
				for (int y = 0; y < MapBuffer.WIDTH; y++) {
					for (int x = 0; x < MapBuffer.WIDTH; x++) {
						g.setColor(MapPalette.getColor(buf[i]));
						g.fillRect(x * 4, y * 4, 4, 4);
						i++;
					} 
				} 
			}
			
		};
		
		frame.setSize(MapBuffer.WIDTH * 4, MapBuffer.WIDTH * 4);
		frame.setResizable(false);
		frame.setContentPane(pan);
		frame.setVisible(true);
		
		testDrawable(map);
		pan.repaint();
	}
	
	public static void testDrawable(MapDrawable map) {
		map.clearScreen(MapColor.BLACK_SHADOW);
		
		System.out.println("height: " + map.getHeight());
		System.out.println("width: " + map.getWidth());
		System.out.println("height text: " + map.getHeightText());
		System.out.println("width text 'he': " + map.getWidthText("hs"));
		
		//setcolor
		map.setColor(MapColor.EMERALD_LIGTH);
		
		map.drawPoint(1, 1);
		//test draw Y
		map.drawLineY(3, 1, 6);
		map.drawLineY(5, 10, 6);
		//test draw X
		map.drawLineX(7, 1, 12);
		map.drawLineX(12, 3, 8);
		
		//test drawLine
		map.drawLine(12, 10, 1, 16);
		
		//setcolor
		map.setColor(MapColor.LAVA_LIGTH);
	
		//test draw circle
		map.drawCircle(24, 8, 7);
		map.fillCircle(40, 8, 7);
		
		//setcolor
		map.setColor(MapColor.DIAMOND_LIGTH);
		
		//test draw rect
		map.drawRect(50, 1, 3, 3);
		map.drawRect(53, 4, 4, 4);
		map.drawRect(57, 8, 5, 5);
		map.fillRect(64, 1, 8, 16);
		
		//setcolor
		map.setColor((byte) MapColor.WATER_DARK);
		
		//test draw in rect
		map.fillRect(60, 1, 3, 3);
		map.drawRect(64, 1, 5, 5);
		map.drawPoint(66, 3);
		map.drawPoint(69, 3);
		map.drawPoint(71, 3);
		
		//setcolor
		map.setColor(MapColor.GOLD_LIGTH);
		
		//test draw char
		map.drawChar(8, 16, 'A');
		map.drawChar(16, 16, 'B');
		map.drawChar(24, 16, 'c');
		map.drawChar(32, 16, 'd');
		
		//test font
		map.setCof((byte) 2);//set cof 2
		map.drawChar(8, 24, '@');
		map.setCof((byte) 3);//set cof 3
		map.drawChar(24, 24, '{');
		map.setCof((byte) 4);//set cof 4
		map.drawChar(48, 24, '}');
		System.out.println("height 4 text: " + map.getHeightText());
		System.out.println("height 4 text 'he': " + map.getWidthText("he"));
		map.setCof((byte) 5);//set cof 5
		map.drawChar(73, 1, '$');
		
		//test buf
		map.setCof((byte) 1);//reset cof
		byte[] miniBuf = new byte[] {//5x3
				(byte) 78, (byte) 79, (byte) 78, (byte) 79, (byte) 79,
				(byte) 79, (byte) 78, (byte) 78, (byte) 78, (byte) 79,
				(byte) 79, (byte) 79, (byte) 78, (byte) 79, (byte) 78,
		};
		map.drawBuffer(76, 10, miniBuf, 5, 3);
		
		//test buffer 480
		byte[] testBuf = new byte[] {//30x16
			(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 130, (byte) 130, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 126, (byte) 0, (byte) 0, (byte) 131, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 130, (byte) 130, (byte) 131, (byte) 131, (byte) 131, (byte) 130, (byte) 126, (byte) 130, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 126, (byte) 0, (byte) 0, (byte) 131, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 130, (byte) 126, (byte) 126, (byte) 126, (byte) 130, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 126, (byte) 0, (byte) 0, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 130, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 126, (byte) 126, (byte) 126, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 130, (byte) 130, (byte) 126, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 130, (byte) 130, (byte) 130, (byte) 130, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 130, (byte) 130, (byte) 130, (byte) 130, (byte) 130, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 130, (byte) 130, (byte) 130, (byte) 130, (byte) 130, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 131, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, 
		};
		map.drawBuffer(90, 8, testBuf, 30, 16);
		//test buffer font 2
		map.setCof((byte) 2);
		map.drawBuffer(65, 33, testBuf, 30, 16);
		
		//reset cof
		map.setCof((byte) 1);
		
		map.setColor(MapColor.EMERALD_NORMAL);
		map.drawLineX(0, 64, 64);
		//test drawText
		map.setColor(MapColor.SNOW_LIGTH);
		map.drawText(0, 64, "Hello, world !");
		//test cof 2
		map.setCof((byte) 2);
		map.setColor(MapColor.LAVA_NORMAL);
		map.drawText(0, 72, "Hello, world !");
		
		//cof null
		map.setCof((byte) 0);
		map.drawText(0, 0, "Hello, world !");
		
		//reset cof
		map.setCof((byte) 1);
		
		//test all char
		map.setColor(MapColor.SAND_NORMAL);
		map.drawText(0, 88, "! \"#$%&'()*+,-./01234567");
		map.drawText(0, 96, "89:;<=>?@LMNOPhijkl{|}~'[");
		map.drawText(0, 104, "\\]^_@àçéÉëê`è");
		
		//all character
		//" !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_'abcdefghijklmnopqrstuvwxyz{|}~\u837f\u83c7\u83fc\u83e9\u83e2\u83e4\u83e0\u83e5\u83e7\u83ea\u83eb\u83e8\u83ef\u83ee\u83ec\u83c4\u83c5\u83c9\u83e6\u83c6\u83f4\u83f6\u83f2\u83fb\u83f9\u83ff\u83d6\u83dc\u83f8£\u83d8\u83d7\u0191\u83e1\u83ed\u83f3\u83fa\u83f1\u83d1ªº¿®¬½¼¡«»"
	}
	
}
