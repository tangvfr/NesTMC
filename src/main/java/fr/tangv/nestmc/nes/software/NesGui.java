package fr.tangv.nestmc.nes.software;

import fr.tangv.nestmc.draw.MapBuffer;
import fr.tangv.nestmc.nes.NesScreenMap;

public class NesGui extends NesScreenMap {

	public NesGui(MapBuffer[] bitScreens, byte[] colors) {
		super(bitScreens, colors);
	}

	@Override
	public void loadROMs(String rom) {
		//never called method for tmc
	}

	@Override
	public void messageBox(String msg) {
		System.out.println("msgbox: "+msg);
	}

	@Override
	public void render() {
		//never called method for tmc
	}

	@Override
	public void run() {
		//when nes is create
	}

}
