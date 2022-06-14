package fr.tangv.nestmc.test.drawable;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.bukkit.map.MapPalette;

import fr.tangv.nestmc.screen.draw.MapBuffer;
import fr.tangv.nestmc.screen.draw.MapBuffered;

public class MapBufferFrame {

	private final JFrame frame;
	private final JPanel pan;
	
	/**
	 * Permet d'ouvrir une frame pour representer une MapBuffered
	 * @param map map a afficher
	 * @param cof taille de un pixel
	 */
	public MapBufferFrame(MapBuffered map, int cof) {
		frame = new JFrame("Drawable Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.pan = new JPanel() {
			private static final long serialVersionUID = 1L;
			
			@SuppressWarnings("deprecation")
			@Override
			public void paint(Graphics g) {
				byte[] buf = map.getBuffer();
				int i = 0;
				
				for (int y = 0; y < MapBuffer.WIDTH; y++) {
					for (int x = 0; x < MapBuffer.WIDTH; x++) {
						g.setColor(MapPalette.getColor(buf[i]));
						g.fillRect(x * cof, y * cof, cof, cof);
						i++;
					} 
				} 
			}
			
		};
		
		frame.setSize(MapBuffer.WIDTH * cof, MapBuffer.WIDTH * cof);
		frame.setResizable(false);
		frame.setContentPane(pan);
		frame.setVisible(true);
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public JPanel getPan() {
		return pan;
	}
	
	public void dispose() {
		this.frame.dispose();
	}
	
	public void repaint() {
		this.pan.repaint();
	}

}
