/**
 * 
 */
package fr.tangv.nestmc.nes.software;

/**
 * @author Tangv - https://tangv.fr
 * Le système d'exploitation d'une NES (menu permettant de géré la nes)
 */
public abstract class NesOs implements TMCNesInteractor {

	/*si les paket doivent etre envoyer, par default true*/
	private boolean send = true;

	/**
	 * Permet de modifier si les packet pour mettre a jour l'écran de la nes doivent etre envoyer
	 * @param send si des packet pour mettre à jour l'écran doivent etre envoyer
	 */
	public void setSend(boolean send) {
		this.send = send;
	}

	/**
	 * Permet de récupérer si les packet pour mettre a jour l'écran de la nes doivent etre envoyer
	 * @return true si des packet pour mettre à jour l'écran doivent etre envoyer
	 */
	public boolean isSend() {
		return this.send;
	}
	
}
