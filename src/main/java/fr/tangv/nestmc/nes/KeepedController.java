package fr.tangv.nestmc.nes;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

import fr.tangv.nestmc.util.ReflectionUtil;
import net.minecraft.server.v1_8_R3.PacketPlayOutAttachEntity;
import net.minecraft.server.v1_8_R3.PlayerConnection;

/**
 * @author tangv
 * Permet de convertir les packet et actions entrante d'un joueur vers un controller (manette) de nes exploitable 
 */
public class KeepedController {

	//controler (manette) a la qu'elle on est associe
	private final NesController controller;
	//id de l'entiter sur laquelle on va saseoir
	private final int idVehicle;
	//joueur sur qui on va récupéré les infos
	private final CraftPlayer target;
	//connection du joueur
	private final PlayerConnection co;
	
	/**
	 * Construteur de contrustruire un récupérateur d'entré d'un joueur
	 * @param target joueur sur qui on va récupéré les infos
	 * @param idVehicle id de l'entiter sur laquelle on va saseoir
	 * @param controller controler (manette) a la qu'elle on est associe
	 */
	public KeepedController(CraftPlayer target, int idVehicle, NesController controller) {
		this.target = target;
		this.idVehicle = idVehicle;
		this.controller = controller;
		this.co = this.target.getHandle().playerConnection;
	}
	
	public void mount(boolean mount) {
		PacketPlayOutAttachEntity packet = new PacketPlayOutAttachEntity();
		ReflectionUtil.setValue("a", packet, target.getEntityId());//player
		ReflectionUtil.setValue("b", packet, mount ? this.idVehicle : -1);//vehicle
		ReflectionUtil.setValue("c", packet, 0);//attacher par laisse
		co.sendPacket(packet);
	}
	
	
}
