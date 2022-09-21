package fr.tangv.nestmc.test.color;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.bukkit.map.MapPalette;

public class ShowColor {

	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame("Show Color Minecraft");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pan = new JPanel() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void paint(Graphics g) {
				ShowColor.fillColorMC(g);
			}
			
		};
		
		frame.setSize(605, 628);
		frame.setResizable(false);
		frame.setContentPane(pan);
		frame.setVisible(true);
		
		BufferedImage img = new BufferedImage(600, 600, BufferedImage.TYPE_4BYTE_ABGR);
		ShowColor.fillColorMC(img.createGraphics());
		ImageIO.write(img, "png", new File("./color_map_minecraft_1.8.png"));
	}
	
	@SuppressWarnings("deprecation")
	public static void fillColorMC(Graphics g) {
		int size = 50;
		int scape = 600 / size;
		for (int i = 0; i <= 143; i++) {
			System.out.println(i);
			Color color;
			if (i >= 4) {
				color = MapPalette.getColor((byte) i);
			} else {
				color = new Color(255, 255, 255, 0);
			}
			g.setColor(color);
			int x = (i % scape) * size;
			int y = (i / scape) * size;
			g.fillRect(x, y, size, size);
			
			//number
			int gray = (color.getBlue() + color.getGreen() + color.getRed()) / 3;
			if (gray > 127) {
				g.setColor(Color.BLACK);
			} else {
				g.setColor(Color.WHITE);
			}
			String number = "#"+i;
			int dx = g.getFontMetrics().stringWidth(number) / 2;
			g.drawString(number, x + 32 - dx, y + 32);
		}
	}

}
