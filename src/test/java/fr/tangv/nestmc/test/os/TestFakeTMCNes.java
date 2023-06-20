package fr.tangv.nestmc.test.os;

import fr.tangv.nestmc.draw.MapBuffer;
import fr.tangv.nestmc.draw.PixeableBuffered;
import fr.tangv.nestmc.nes.NesScreen;
import fr.tangv.nestmc.nes.TMCNes;
import fr.tangv.nestmc.nes.controller.NesController;
import fr.tangv.nestmc.nes.software.NesGui;
import fr.tangv.nestmc.nes.software.NesOs;
import fr.tangv.nestmc.palette.v1_8.McNesPaletteV1_8;
import fr.tangv.nestmc.test.drawable.MapBufferFrame;
import fr.tangv.nestmc.test.emulator.FrameController;

/**
 * @author Tangv - https://tangv.fr
 * Permet de tester l'os de la nes
 */
public class TestFakeTMCNes extends TMCNes {
	
	private volatile boolean alive = true;
	private final byte[] buffer = new byte[256 * 256];
	private final MapBufferFrame frame;
	
	/**
	 * Permet de construire une nouvelle nes de test (écran dans un JFrame)
	 */
	public TestFakeTMCNes(NesOs os, String romFolder) {
		super(new NesGui(
						os, 
						new MapBuffer[] {new MapBuffer(), new MapBuffer(), new MapBuffer(), new MapBuffer()},
						McNesPaletteV1_8.MC_NES_PALETTE,
						romFolder + "/save"
						)
				, os
				);
		
		//screen 256x256
		this.frame = new MapBufferFrame(new PixeableBuffered() {
			@Override
			public byte[] getBuffer() {
				return TestFakeTMCNes.this.buffer;
			}
		}, 256, 3);
		
		//simulate minecraft ticks
		new Thread(() -> {
			while(TestFakeTMCNes.this.alive) {
				TestFakeTMCNes.this.update();
				try {
					Thread.sleep(1000 / 20);//simulate 20 tick per sec
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "Fake minecraft ticks").start();
		
		FrameController.createFrame((NesController) this.getSecondController(), "Second");
		this.frame.getFrame().addKeyListener(new FrameController((NesController) this.getFirstController()));
		this.frame.getFrame().toFront();
	}
	
	private final void copyBitScreenToScreen(PixeableBuffered[] bitScreen) {
		byte[] buf;
		final int length = 128 * 128;
		int i, dc, di;
		
		for (int m = 0; m < 4; m++) {
			buf = bitScreen[m].getBuffer();
			dc = (m % 2) == 0 ? 0 : 128;
			if (m >= 2) {
				dc += length << 1;
			}
			
			for (di = 0; di < length; di += 128) {
				for (i = 0; i < 128; i++) {
					buffer[dc + i] = buf[di + i];
				}
				dc += 256;
			}
		}
	}
	
	@Override
	public void update() {
		//update input
		((NesController) this.getFirstController()).update();
		((NesController) this.getSecondController()).update();
		
		NesOs os = this.getOs();
		//update os
		os.update(this, this.getFirstController(), this.getSecondController(), this.getMixedController());
		NesScreen screen = this.getScreen();
		
		synchronized (screen) {
			//render os on screen
			os.render(this, screen);
			//send packet to player
			if (os.isSend()) {
				this.copyBitScreenToScreen(this.getScreen().getBitScreens());
				this.frame.repaint();
			}
		}
	}

	@Override
	public void closeController(int controllers) {
		System.out.println("kick controller: " + controllers);
	}
	
}
