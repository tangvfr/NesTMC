package fr.tangv.nestmc;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.bukkit.map.MapPalette;

import fr.tangv.nestmc.palette.MapColor;
import fr.tangv.nestmc.screen.MapBuffer;
import fr.tangv.nestmc.screen.MapDrawable;

public class TestDrawable {
	
	public static void main(String[] args) {
		MapDrawable map = new MapDrawable();
		
		JFrame frame = new JFrame("Test");
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
		
		frame.setSize(MapBuffer.WIDTH*4, MapBuffer.WIDTH*4);
		frame.setResizable(false);
		frame.setContentPane(pan);
		frame.setVisible(true);
		
		testRender(map, pan);
	}
	
	public static void testRender(MapDrawable map, JComponent pan) {
		map.clearScreen(MapColor.BLACK_SHADOW);
		
		System.out.println("height: " + map.getHeight());
		System.out.println("width: " + map.getWidth());
		System.out.println("height text: " + map.getHeightText());
		
		map
		
		pan.repaint();
	}
	
}
