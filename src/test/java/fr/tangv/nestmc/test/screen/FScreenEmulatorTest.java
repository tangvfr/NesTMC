package fr.tangv.nestmc.test.screen;

import com.grapeshot.halfnes.NES;
import com.grapeshot.halfnes.ui.PuppetController;
import com.grapeshot.halfnes.ui.PuppetKeyListener;

import fr.tangv.nestmc.draw.MapBuffer;
import fr.tangv.nestmc.nes.NesScreenMap;
import fr.tangv.nestmc.palette.v1_8.McNesPaletteV1_8;
import fr.tangv.nestmc.test.drawable.MapBufferFrame;

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
		PuppetKeyListener c1 = PuppetKeyListener.createFrame(null);
		
		//init nes
		NES nes = new NES(new NesScreenMap(maps, McNesPaletteV1_8.MC_NES_PALETTE) {
			@Override
			public void loadROMs(String arg0) {
				System.out.println("load: "+arg0);
			}

			@Override
			public void messageBox(String arg0) {
				System.out.println("sgbox: "+arg0);
			}

			@Override
			public void render() {
				// TODO Auto-generated method stub
			}

			@Override
			public void run() {
				System.out.println("I started");
			}
		});
	    PuppetController c2 = new PuppetController();
	    nes.setControllers(c1, c2);
	    
	    //pref
	    /*Preferences prefs = PrefsSingleton.get();
	    prefs.putBoolean("soundEnable", true);
	    prefs.putBoolean("soundFiltering", false);
	    prefs.putInt("outputvol", 0);//to muet game*/
	
	    nes.loadROM(args[0]);
	    
	    Thread display = new Thread(() -> {
			while(nes.runEmulation) {
				//System.out.println(nes.getFrameTime());
				for (int i = 0; i < 4; i++) {
					frames[i].repaint();
				}
				try {
					Thread.sleep(1000 / 20);//simulate 20 tick per sec
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "Render MapBufferFrame");
	    
	    new Thread(() -> {
	       nes.run();
	    }, "Game Thread").start();
	    display.start();
	}
	
}
