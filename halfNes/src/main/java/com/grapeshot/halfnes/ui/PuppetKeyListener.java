package com.grapeshot.halfnes.ui;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Tangv - https://tangv.fr
 * Puppet controller with KeyListener
 */
public class PuppetKeyListener extends PuppetController implements KeyListener {

	public static PuppetKeyListener createFrame(String name) {
		//keyboard
		PuppetKeyListener control = new PuppetKeyListener();
		JFrame frame = new JFrame("Keyboard "+name);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addKeyListener(control);
		frame.setResizable(false);
		frame.setSize(256, 64);
		frame.setVisible(true);
		return control;
	}
	
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
