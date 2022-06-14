package fr.tangv.nestmc.screen;

import java.util.ArrayList;

import fr.tangv.nestmc.draw.MapBuffer;
import net.minecraft.server.v1_8_R3.MapIcon;
import net.minecraft.server.v1_8_R3.PacketPlayOutMap;

/**
 * @author tangv
 * Permet de crée une map envoyable
 */
public class PacketMapBuffer extends PacketPlayOutMap {
	
	private final PacketPlayOutMap packet;
	private final MapBuffer buf;
	private final int idMap;
	
	/**
	 * Constructeur qui permet
	 * 
	 * @param idMap id damage de la map mise à jour
	 */
	public PacketMapBuffer(int idMap) {
		this.idMap = idMap;
		this.buf = new MapBuffer();
		this.packet = new PacketPlayOutMap(idMap, (byte) 0, new ArrayList<MapIcon>(), this.buf.getBuffer(), 0, 0, 128, 128);
	}

	public PacketPlayOutMap getPacket() {
		return packet;
	}

	public MapBuffer getBuf() {
		return buf;
	}

	public int getIdMap() {
		return idMap;
	}

}
