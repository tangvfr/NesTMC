package fr.tangv.nestmc.test.drawable;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.bukkit.map.MapPalette;

import fr.tangv.nestmc.draw.MapBuffered;

public class MapBufferFrame {

	private final JFrame frame;
	private final JPanel pan;
	private final JComponent map;
	
	/**
	 * Permet d'ouvrir une frame pour representer une MapBuffered
	 * @param map map a afficher
	 * @param cof taille de un pixel
	 */
	public MapBufferFrame(MapBuffered map, int cof) {
		final int SIZE = (int) Math.sqrt(map.getBuffer().length);
		frame = new JFrame("Drawable Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.map = new JComponent() {
			private static final long serialVersionUID = 1L;
			
			@SuppressWarnings("deprecation")
			@Override
			public void paint(Graphics g) {
				byte[] buf = map.getBuffer();
				int i = 0;
				
				for (int y = 0; y < SIZE; y++) {
					for (int x = 0; x < SIZE; x++) {
						g.setColor(MapPalette.getColor(buf[i]));
						g.fillRect(x * cof, y * cof, cof, cof);
						i++;
					} 
				} 
			}
		};
		
		//pan
		this.pan = new JPanel();
		this.pan.setLayout(null);
		this.pan.add(this.map);
		
		//map
		Dimension dim = new Dimension(SIZE * cof, SIZE * cof);
		this.map.setSize(dim);
		this.map.setMinimumSize(dim);
		this.map.setMaximumSize(dim);
		this.map.setLocation(32, 0);
				
		//frame
		this.frame.setSize(64 + SIZE * cof, 64 + SIZE * cof);
		this.frame.setResizable(false);
		this.frame.setContentPane(this.pan);
		this.frame.setVisible(true);
	}
	
	public JFrame getFrame() {
		return this.frame;
	}

	public JComponent getMap() {
		return this.map;
	}
	
	public void dispose() {
		this.frame.dispose();
	}
	
	public void repaint() {
		this.pan.repaint();
	}

}
