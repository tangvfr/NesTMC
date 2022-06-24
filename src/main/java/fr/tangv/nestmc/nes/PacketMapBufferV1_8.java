package fr.tangv.nestmc.nes;

import java.util.ArrayList;

import fr.tangv.nestmc.draw.MapBuffer;
import net.minecraft.server.v1_8_R3.MapIcon;
import net.minecraft.server.v1_8_R3.PacketPlayOutMap;

/**
 * @author tangv
 * Permet de crée une map envoyable
 */
public class PacketMapBufferV1_8 extends MapBuffer {
	
	private final int idMap;
	private final PacketPlayOutMap packet;
	
	/**
	 * Constructeur qui permet
	 * 
	 * @param idMap id damage de la map mise à jour
	 */
	public PacketMapBufferV1_8(NesManager manager) {
		this.idMap = manager.nextIdMap();
		this.packet = new PacketPlayOutMap(idMap, (byte) 0, new ArrayList<MapIcon>(), this.getBuffer(), 0, 0, 128, 128);
	}

	/*
	 * Permet de récupéré le paquet pour mettre a jour la map
	 * @return paquet pour mettre a jour la map
	 */
	public PacketPlayOutMap getPacket() {
		return packet;
	}
	
	/**
	 * Permet de récupéré l'id de la map
	 * @return id de la map
	 */
	public int getIdMap() {
		return idMap;
	}

}
