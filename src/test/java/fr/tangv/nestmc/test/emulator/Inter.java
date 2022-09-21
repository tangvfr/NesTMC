package fr.tangv.nestmc.test.emulator;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.grapeshot.halfnes.NES;
import com.grapeshot.halfnes.ui.GUIInterface;
import com.grapeshot.halfnes.ui.PuppetKeyListener;
import com.grapeshot.halfnes.video.NesColors;

public class Inter implements GUIInterface {

	private NES nes;
	JPanel pan;
	Color[][] background;
	
	public Inter(PuppetKeyListener c) {
		JFrame frame = new JFrame("Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		background = new Color[256][256];
		for (int y = 0; y < 256; y++) {
			for (int x = 0; x < 256; x++) {
				background[y][x] = Color.black;
			} 
		}
		
		pan = new JPanel() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void paint(Graphics g) {
				for (int y = 0; y < 256; y++) {
					for (int x = 0; x < 256; x++) {
						g.setColor(background[y][x]);
						g.fillRect(x * 4, y * 4, 4, 4);
					} 
				} 
			}
			
		};
		
		frame.addKeyListener(c);
		
		frame.setSize(256*4, 256*4);
		frame.setResizable(false);
		frame.setContentPane(pan);
		frame.setVisible(true);
	}
	
	@Override
	public NES getNes() {
		return this.nes;
	}
	
	@Override
	public void setNES(NES nes) {
		this.nes = nes;
	}

	@Override
	public void loadROMs(String path) {
		
		System.out.println("load roms: "+path);
	}

	@Override
	public void messageBox(String message) {
		System.out.println(message);
	}

	@Override
	public void setFrame(int[] nespixels, int[] bgcolor, boolean dotcrawl) {
		int length = nespixels.length;
		for (int i = 0; i < length; i++) {
			//NesColors.colbytes[(nespixels[i] & 0x1c0) >> 6][nespixels[i] & 0x3f];
			background[i / 256][i % 256] = new Color(NesColors.col[(nespixels[i] & 0x1c0) >> 6][nespixels[i] & 0x3f]);
		}
		pan.repaint();
	}
	
	@Override
	public void render() {
		//none
	}

	@Override
	public void run() {
		//update param
		System.out.println("I run !");
	}

}
