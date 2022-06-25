package fr.tangv.nestmc.game;

import fr.tangv.nestmc.draw.MapBuffer;

/**
 * @author tangv
 * Permet de crée un map buffer avec l'id de la map qui corespond et le packet pour la mettre a jour
 */
public abstract class PacketMapBuffer<T> extends MapBuffer {
	
	/**
	 * Identifiant de la map qui corepond au buffer
	 */
	private final short idMap;
	
	/**
	 * Constructeur qui permet construire un map buffer avec l'id de la map a laquelle le buffer corespond et le packet pour la mettre a jour
	 * 
	 * @param manager le gestionaire des maps
	 */
	public PacketMapBuffer(McNesManager manager) {
		this.idMap = manager.nextIdMap();
	}
	
	/**
	 * Permet de récupéré l'id de la map
	 * @return id de la map
	 */
	public short getIdMap() {
		return idMap;
	}
	
	/*
	 * Permet de récupéré le paquet pour mettre a jour la map
	 * @return paquet pour mettre a jour la map
	 */
	public abstract T getPacket();

}
