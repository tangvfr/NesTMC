package fr.tangv.nestmc.test.emulator;

import fr.tangv.nestmc.nes.controller.InputController;
import fr.tangv.nestmc.nes.controller.NesController;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Tangv - https://tangv.fr
 * Permet de simuler un PlayerConnection pour la version virtuelle
 */
public class FrameController implements KeyListener {

	/**
	 * Permet de crée et ouvrir une fenetre avec les fake Player controller en fenetrer
	 * @param control la manette nes
	 * @param name nom du controlleur
	 * @return le fake Player controler dans une fenetre
	 */
	public static FrameController createFrame(NesController control, String name) {
		//keyboard
		FrameController controller = new FrameController(control);
		JFrame frame = new JFrame("Keyboard "+name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(controller);
		frame.setResizable(false);
		frame.setSize(256, 64);
		frame.setVisible(true);
		return controller;
	}
	
	private final NesController control;
	
	/**
	 * Permet de construire un fake player ocntroller
	 * @param control manette nes
	 */
	public FrameController(NesController control) {
		this.control = control;
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	/**
	 * Permet de determiner quel touche du controleur corespond a celle du clavier
	 * @param e evenement de la touche clavier
	 * @return le button de la nes qui corespond
	 */
	private int keyEventToButton(KeyEvent e) {
		int btn;
		switch(e.getKeyCode()) {
			//NES
			case KeyEvent.VK_Z:
				btn = InputController.UP;
				break;
				
			case KeyEvent.VK_S:
				btn = InputController.DOWN;
				break;
				
			case KeyEvent.VK_Q:
				btn = InputController.LEFT;
				break;
				
			case KeyEvent.VK_D:
				btn =  InputController.RIGHT;
				break;
				
			case KeyEvent.VK_SPACE:
				btn =  InputController.SPACE;
				break;
				
			case KeyEvent.VK_CONTROL:
				btn =  InputController.SNEAK;
				break;
				
			case KeyEvent.VK_O:
				btn = InputController.ATTACK;//click
				break;
				
			case KeyEvent.VK_A:
				btn = InputController.OPEN_INV;//click
				break;
				
			case KeyEvent.VK_P:
				btn = InputController.INTERACT;//click
				break;
				
			//HEALD
			case KeyEvent.VK_1:
				btn = InputController.HEALD_1;//click
				break;
				
			case KeyEvent.VK_2:
				btn = InputController.HEALD_2;//click
				break;
				
			case KeyEvent.VK_3:
				btn = InputController.HEALD_3;//click
				break;
				
			case KeyEvent.VK_4:
				btn = InputController.HEALD_4;//click
				break;
				
			case KeyEvent.VK_6:
				btn = InputController.HEALD_5;//click
				break;
				
			case KeyEvent.VK_7:
				btn = InputController.HEALD_6;//click
				break;
				
			case KeyEvent.VK_8:
				btn = InputController.HEALD_7;//click
				break;
				
			case KeyEvent.VK_9:
				btn = InputController.HEALD_8;//click
				break;
				
			default:
				btn = -1;
				break;
		}
		return btn;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int btn = keyEventToButton(e);
		if (btn != -1) {
			this.control.releaseButton(btn);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int btn = keyEventToButton(e);
		if (btn != -1) {
			this.control.pressButton(btn);
		}
	}
	
}
