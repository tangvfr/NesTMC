/**
 * 
 */
package fr.tangv.nestmc.game.v1_8;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import fr.tangv.nestmc.draw.FourMapScreen;
import fr.tangv.nestmc.game.McNes;
import fr.tangv.nestmc.game.McNesManager;
import fr.tangv.nestmc.game.controller.PlayerController;
import net.minecraft.server.v1_8_R3.EntityItemFrame;
import net.minecraft.server.v1_8_R3.EnumDirection;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketListenerPlayOut;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntity;
import net.minecraft.server.v1_8_R3.PlayerConnection;

/**
 * @author Tangv - https://tangv.fr
 * une nes dans minecraft pour la 1.8.8
 */
public class McNesV1_8 extends McNes<Packet<PacketListenerPlayOut>> {

	@SuppressWarnings("unchecked")
	private final Packet<PacketListenerPlayOut>[] spawnItemFrames = new Packet[8];
	//id des itemframes pour l'ecran de la nes
	private final int idItemFrames[] = new int[4];

	
	public McNesV1_8(McNesManager<Packet<PacketListenerPlayOut>> manager, Location loc) {
		super(manager, loc);
		//data
		EnumDirection dir = EnumDirection.fromAngle(loc.getYaw());
		PacketMapBufferV1_8[] maps = (PacketMapBufferV1_8[])((FourMapScreen) this.getScreen()).getBitScreens();
		//vector
		Vector left = NesV1_8Util.leftVectorOf(dir);
		Vector up = new Vector(0, 1, 0);
		Vector vec = loc.toVector();
		
		//bas droit
		this.setScreenMap(maps, 3, vec, dir);
		
		//bas gauche
		vec.add(left);
		this.setScreenMap(maps, 2, vec, dir);
		
		//haut droit
		vec.subtract(left);
		vec.add(up);
		this.setScreenMap(maps, 1, vec, dir);
		
		//haut gauche
		vec.add(left);
		this.setScreenMap(maps, 0, vec, dir);
	}
	
	private void setScreenMap(PacketMapBufferV1_8[] maps, int index, Vector vec, EnumDirection dir) {
		EntityItemFrame eif = NesV1_8Util.createMapItemFrame(maps[0].getIdMap(), vec, dir);
		//packet entity
		int i = index * 2;
		this.spawnItemFrames[i] = new PacketPlayOutSpawnEntity(eif, 71, dir.b());
		i++;
		this.spawnItemFrames[i] = new PacketPlayOutEntityMetadata(eif.getId(), eif.getDataWatcher(), true);
		//id entity
		this.idItemFrames[index] = eif.getId();
	}

	/**
	 * Permet de faire spawn a un joueur les écrans de la nes
	 * @param co la connection du joueur
	 */
	private void sendItemsFrame(PlayerConnection co) {
		for (Packet<PacketListenerPlayOut> packet : this.spawnItemFrames) {
			co.sendPacket(packet);
		}
	}

	@Override
	public void openController(PlayerController control) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeController(PlayerController control) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(List<Player> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show(Player player) {
		
		
	}

	@Override
	public void hide(Player player, boolean quit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destruct() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendPacket(Packet<PacketListenerPlayOut> packet) {
		// TODO Auto-generated method stub
		
	}

}
