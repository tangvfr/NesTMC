package fr.tangv.nestmc.game;

import java.util.ArrayList;

import fr.tangv.nestmc.draw.MapBuffer;
import net.minecraft.server.v1_8_R3.MapIcon;
import net.minecraft.server.v1_8_R3.PacketPlayOutMap;

/**
 * @author tangv
 * Permet de crée une map envoyable
 */
public class PacketMapBuffer extends MapBuffer {

	private final int idMap;
	private final PacketPlayOutMap packet;
	
	//controleers   with or whitout items
	/*
	 * action right click and left click
	*/
	/*Held Item Change    2 direction
	Sent when the player changes the slot selection

	Packet ID	State	Bound To	Field Name	Field Type	Notes
	0x09	Play	Server	Slot	Short	The slot which the player has selected (0–8)
*/
	/*
	 /*
	 Steer Vehicle
		Packet ID	State	Bound To	Field Name	Field Type	Notes
		0x0C	Play	Server	Sideways	Float	Positive to the left of the player
		Forward	Float	Positive forward
		Flags	Unsigned Byte	Bit mask. 0x1: jump, 0x2: unmount
	 
	 */
	/*
	 * Entity Action
Sent by the client to indicate that it has performed certain actions: sneaking (crouching), sprinting, exiting a bed, jumping with a horse, and opening a horse's inventory while riding it.

Packet ID	State	Bound To	Field Name	Field Type	Notes
0x0B	Play	Server	Entity ID	VarInt	Player ID
Action ID	VarInt	The ID of the action, see below
Action Parameter	VarInt	Only used by Horse Jump Boost, in which case it ranges from 0 to 100. In all other cases it is 0.
Action ID can be one of the following values:

ID	Action
0	Start sneaking
1	Stop sneaking
2	Leave bed
3	Start sprinting
4	Stop sprinting
5	Jump with horse
6	Open ridden horse inventory
Leave Bed is only sent when the "Leave Bed" button is clicked on the sleep GUI, not when waking up due today time.

Open ridden horse inventory is only sent when pressing the inventory key on a horse - all other methods of opening a horse's inventory (involving right-clicking or shift-right-clicking it) do not use this packet.
	 */
	
	
	/**
	 * Constructeur qui permet
	 * 
	 * @param idMap id damage de la map mise à jour
	 */
	public PacketMapBuffer(int idMap) {
		super();
		this.idMap = idMap;
		this.packet = new PacketPlayOutMap(idMap, (byte) 0, new ArrayList<MapIcon>(), this.getBuffer(), 0, 0, 128, 128);
	}

	public PacketPlayOutMap getPacket() {
		return packet;
	}

	public int getIdMap() {
		return idMap;
	}

}
