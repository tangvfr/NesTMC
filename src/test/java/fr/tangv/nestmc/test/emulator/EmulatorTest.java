package fr.tangv.nestmc.test.emulator;

import java.util.prefs.Preferences;

import com.grapeshot.halfnes.NES;
import com.grapeshot.halfnes.PrefsSingleton;
import com.grapeshot.halfnes.ui.PuppetController;

public class EmulatorTest {

	public static void main(String[] args) {
		PuppetKeyListener c1 = new PuppetKeyListener();
		NES nes = new NES(new Inter(c1));
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
	}

}
