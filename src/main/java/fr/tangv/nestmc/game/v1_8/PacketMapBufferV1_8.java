package fr.tangv.nestmc.game.v1_8;

import java.util.ArrayList;

import fr.tangv.nestmc.game.McNesManager;
import fr.tangv.nestmc.game.PacketMapBuffer;
import fr.tangv.nestmc.util.ReflectionUtil;
import net.minecraft.server.v1_8_R3.MapIcon;
import net.minecraft.server.v1_8_R3.PacketPlayOutMap;

/**
 * @author tangv
 * Permet de crée un map buffer envoyable
 */
public class PacketMapBufferV1_8 extends PacketMapBuffer<PacketPlayOutMap> {

	/*le packet pour mettre a jour la map*/
	private final PacketPlayOutMap packet;
	
	/**
	 * Constructeur qui permet de créé un map buffer envoyable
	 * 
	 * @param manager le gestionaire des maps
	 */
	public PacketMapBufferV1_8(McNesManager manager) {
		super(manager);
		this.packet = new PacketPlayOutMap(super.getIdMap(), (byte) 0, new ArrayList<MapIcon>(), new byte[0], 0, 0, 0, 0);
		ReflectionUtil.setValue("f", this.packet, 128);
		ReflectionUtil.setValue("g", this.packet, 128);
		ReflectionUtil.setValue("h", this.packet, this.getBuffer());
	}

	@Override
	public PacketPlayOutMap getPacket() {
		return this.packet;
	}
	
}
