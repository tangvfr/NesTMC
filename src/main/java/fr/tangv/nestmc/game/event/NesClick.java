/**
 * 
 */
package fr.tangv.nestmc.game.event;

import fr.tangv.nestmc.game.McNes;

/**
 * @author Tangv - https://tangv.fr
 * Répresente une interaction sur un écran de nes
 */
public class NesClick<T> {

	private final McNes<T> nes;
	private final boolean left;
	
	/**
	 * Permet de construire une interaction sur un écran de nes
	 * @param nes la nes sur qui a été fait
	 * @param left la partie de l'écran conserner true pour la gauche et fals epour la droite 
	 */
	public NesClick(McNes<T> nes, boolean left) {
		super();
		this.nes = nes;
		this.left = left;
	}

	/**
	 * Permet de récupérer si le clique a été fait sur l'écran de gauche
	 * @return true si c'est sur la partie gauche de l'écran
	 */
	public boolean isLeft() {
		return this.left;
	}

	/**
	 * Permet de récupérer la nes sur laquelle a été fait le clique
	 * @return la nes
	 */
	public McNes<T> getNes() {
		return this.nes;
	}
	
}
