package fr.tangv.nestmc.game.v1_8;

import fr.tangv.nestmc.game.McNesManager;
import fr.tangv.nestmc.game.PacketMapBuffer;
import fr.tangv.nestmc.util.ReflectionUtil;
import net.minecraft.server.v1_8_R3.MapIcon;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketListenerPlayOut;
import net.minecraft.server.v1_8_R3.PacketPlayOutMap;

import java.util.ArrayList;

/**
 * @author Tangv - https://tangv.fr
 * Permet de crée un map buffer envoyable
 */
public class PacketMapBufferV1_8 extends PacketMapBuffer<Packet<PacketListenerPlayOut>> {

	/*le packet pour mettre a jour la map*/
	private final PacketPlayOutMap packet;
	
	/**
	 * Constructeur qui permet de créé un map buffer envoyable
	 * 
	 * @param manager le gestionaire des maps
	 */
	public PacketMapBufferV1_8(McNesManager<Packet<PacketListenerPlayOut>> manager) {
		super(manager);
		this.packet = new PacketPlayOutMap(super.getIdMap(), (byte) 0, new ArrayList<MapIcon>(), new byte[0], 0, 0, 0, 0);
		ReflectionUtil.setValue(this.packet, "f", 128);
		ReflectionUtil.setValue(this.packet, "g", 128);
		ReflectionUtil.setValue(this.packet, "h", this.getBuffer());
	}

	@Override
	public Packet<PacketListenerPlayOut> getPacket() {
		return this.packet;
	}
	
}
