package fr.tangv.nestmc.nes;

/**
 * @author tangv
 * Permet de géré les différente NES sur le serveur
 */
public class NesManager {

	/**
	 * id de map en partant de la fin des id possible
	 */
	private int nextIdMap;

	public void start() {
		this.nextIdMap = Short.MAX_VALUE;
		
	}
	
	public void stop() {
		
	}
	
	/**
	 * Permet de récupéré la prochaine id de map en partant de la fin des id possible
	 * @return l'id de prochaine map
	 */
	public int nextIdMap() {
		int idMap = this.nextIdMap;
		this.nextIdMap--;
		return idMap;
	}
	
}
