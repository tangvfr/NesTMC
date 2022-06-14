package fr.tangv.nestmc.screen.packet;

import java.io.IOException;

import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutMap;

/**
 * @author tangv
 * Permet d'envoyer un packet pour mettre a jour une map
 */
public class PacketMapBuffer extends PacketPlayOutMap {
	
	private byte[] buf;
	private int idMap;
	/*private int updatedWidth = 128;
	private int updatedHeight = 128;	
	private int updatedX = 0;
	private int updatedY = 0;*/
	
	/**
	 * Constructeur qui permet de crée packet custom pour mettre à jour une map
	 * 
	 * @param idMap id damage de la map mise à jour
	 * @param buf buffer de la map
	 */
	public PacketMapBuffer(int idMap, byte[] buf) {
		this.idMap = idMap;
		this.buf = buf;
	}
	
	public int getIdMap() {
		return idMap;
	}
/*
	public int getUpdatedWidth() {
		return updatedWidth;
	}

	public int getUpdatedHeight() {
		return updatedHeight;
	}

	public int getUpdatedX() {
		return updatedX;
	}

	public int getUpdatedY() {
		return updatedY;
	}

	public void setUpdatedWidth(int updatedWidth) {
		this.updatedWidth = updatedWidth;
	}

	public void setUpdatedHeight(int updatedHeight) {
		this.updatedHeight = updatedHeight;
	}

	public void setUpdatedX(int updatedX) {
		this.updatedX = updatedX;
	}

	public void setUpdatedY(int updatedY) {
		this.updatedY = updatedY;
	}
*/
	/**
	 * Ecriture de packet envoyer pour mettre à jour une map minecraft
	 */
	public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
		System.out.println("PacketMapBuffer !!!!");
	    paramPacketDataSerializer.b(this.idMap);
	    paramPacketDataSerializer.writeByte((byte) 2);
	    paramPacketDataSerializer.b(0);
	    paramPacketDataSerializer.writeByte(128);//this.updatedWidth);
	    //if (this.updatedWidth > 0) {
	    paramPacketDataSerializer.writeByte(128);//this.updatedHeight);
	    paramPacketDataSerializer.writeByte(0);//this.updatedX);
	    paramPacketDataSerializer.writeByte(0);//this.updatedY);
	    paramPacketDataSerializer.a(this.buf);
	 }

}
