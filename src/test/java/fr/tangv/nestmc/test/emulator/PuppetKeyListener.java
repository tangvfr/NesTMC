package fr.tangv.nestmc.test.emulator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.grapeshot.halfnes.ui.PuppetController;

public class PuppetKeyListener extends PuppetController implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {}
	
	/**
	 * Permet de determiner quel touche du controleur corespond a celle du clavier
	 * @param e evenement de la touche clavier
	 * @return le button de la nes qui corespond
	 */
	private Button keyEventToButton(KeyEvent e) {
		Button btn;
		switch(e.getKeyCode()) {
			case KeyEvent.VK_Z:
				btn = Button.UP;
				break;
				
			case KeyEvent.VK_S:
				btn = Button.DOWN;
				break;
				
			case KeyEvent.VK_Q:
				btn = Button.LEFT;
				break;
				
			case KeyEvent.VK_D:
				btn = Button.RIGHT;
				break;
				
			case KeyEvent.VK_L:
				btn = Button.A;
				break;
				
			case KeyEvent.VK_M:
				btn = Button.B;
				break;
				
			case KeyEvent.VK_O:
				btn = Button.SELECT;
				break;
				
			case KeyEvent.VK_P:
				btn = Button.START;
				break;
				
			default:
				btn = null;
				break;
		}
		return btn;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		Button btn = keyEventToButton(e);
		if (btn != null)
			super.releaseButton(btn);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		Button btn = keyEventToButton(e);
		if (btn != null)
			super.pressButton(btn);
	}
	
}
