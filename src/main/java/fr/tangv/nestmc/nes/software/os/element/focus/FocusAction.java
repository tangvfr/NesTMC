package fr.tangv.nestmc.nes.software.os.element.focus;

/**
 * @author Tangv - https://tangv.fr
 * Permet de regrouper une action et les touche pour l'activer
 */
public class FocusAction {

	private final int buttons;
	private final FocusExecution focusExecution;
	
	/**
	 * Permet de construire une action
	 * @param buttons le(s) button(s) qui active l'action
	 * @param focusExecution l'action à executer
	 */
	public FocusAction(int buttons, FocusExecution focusExecution) {
		this.buttons = buttons;
		this.focusExecution = focusExecution;
	}

	/**
	 * Permet de récupérer le(s) button(s) qui active l'action
	 * @return le(s) button(s) qui active l'action
	 */
	public int getButtons() {
		return this.buttons;
	}

	/**
	 * Permet de récupérer l'action à executer
	 * @return l'action à executer
	 */
	public FocusExecution getFocusExecution() {
		return this.focusExecution;
	}
	
}
