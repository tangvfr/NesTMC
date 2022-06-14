package fr.tangv.nestmc.test.screen;

import java.util.prefs.Preferences;

import javax.swing.JFrame;

import com.grapeshot.halfnes.NES;
import com.grapeshot.halfnes.PrefsSingleton;
import com.grapeshot.halfnes.ui.PuppetController;

import fr.tangv.nestmc.screen.draw.MapBuffer;
import fr.tangv.nestmc.screen.nes.NesScreenMap;
import fr.tangv.nestmc.test.drawable.MapBufferFrame;
import fr.tangv.nestmc.test.emulator.PuppetKeyListener;

public class FScreenEmulatorTest {

	public static void main(String[] args) {
		//init maps
		MapBuffer[] maps = new MapBuffer[4];
		MapBufferFrame[] frames = new MapBufferFrame[4];
		
		for (int i = 0; i < 4; i++) {
			maps[i] = new MapBuffer();
			frames[i] = new MapBufferFrame(maps[i], 3);
		}
		
		//keyboard
		PuppetKeyListener c1 = new PuppetKeyListener();
		JFrame frame = new JFrame("Keyboard");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(c1);
		frame.setResizable(false);
		frame.setSize(256, 64);
		frame.setVisible(true);
		
		//init nes
		NES nes = new NES(new NesScreenMap(maps));
	    PuppetController c2 = new PuppetController();
	    nes.setControllers(c1, c2);
	    
	    //pref
	    Preferences prefs = PrefsSingleton.get();
	    prefs.putBoolean("soundEnable", true);
	    prefs.putBoolean("soundFiltering", false);
	    prefs.putInt("outputvol", 0);//to muet game
	
	    nes.loadROM(args[0]);
	    
	    new Thread(() -> {
	       nes.run();
	    }, "Game Thread").start();
	    
	    new Thread(() -> {
			while(nes.runEmulation) {
				for (int i = 0; i < 4; i++) {
					frames[i].repaint();
				}
				try {
					Thread.sleep(1000 / 20);//simulate 20 tick per sec
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "Render MapBufferFrame").start();
	}
	
}
