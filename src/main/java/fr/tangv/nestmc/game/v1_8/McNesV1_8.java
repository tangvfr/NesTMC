/**
 * 
 */
package fr.tangv.nestmc.game.v1_8;

import fr.tangv.nestmc.draw.FourMapScreen;
import fr.tangv.nestmc.game.McNes;
import fr.tangv.nestmc.game.McNesManager;
import fr.tangv.nestmc.game.PacketMapBuffer;
import fr.tangv.nestmc.game.controller.PlayerController;
import fr.tangv.nestmc.nes.TMCNes;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Tangv - https://tangv.fr
 * une nes dans minecraft pour la 1.8.8
 */
public class McNesV1_8 extends McNes<Packet<PacketListenerPlayOut>> {

	@SuppressWarnings("unchecked")
	private final Packet<PacketListenerPlayOut>[] spawnItemFrames = new Packet[8];
	private final PacketPlayOutEntityDestroy despawnItemFrames;
	//id des itemframes pour l'ecran de la nes
	private final int idItemFrames[] = new int[4];
	//liste des joueur qui voie la nes
	private final ConcurrentLinkedQueue<EntityPlayer> viewers;

	/**
	 * Permet de construire une console nes pour la version 1.8
	 * @param manager gestionnaire des nes sur le serveur
	 * @param loc possition de la nes dans le monde
	 * @param owner propriétaire de la console
	 */
	public McNesV1_8(McNesManager<Packet<PacketListenerPlayOut>> manager, Location loc, OfflinePlayer owner) {
		super(manager, loc, owner, manager.createNesOs());
		this.viewers = new ConcurrentLinkedQueue<EntityPlayer>();
		//data
		EnumDirection dir = EnumDirection.fromAngle(loc.getYaw());
		@SuppressWarnings("unchecked")
		PacketMapBuffer<Packet<PacketListenerPlayOut>>[] maps = (PacketMapBuffer<Packet<PacketListenerPlayOut>>[])((FourMapScreen) this.getScreen()).getBitScreens();
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
		
		//despawn packet
		this.despawnItemFrames = new PacketPlayOutEntityDestroy(this.idItemFrames);
	}
	
	/**
	 * Permet d'initialiser une map qui constitue l'ecran de la NES
	 * @param maps list des maps
	 * @param index indice de la partie d'ecran entre 0 et 3 
	 * @param vec location de la map
	 * @param dir direction de la map
	 */
	private void setScreenMap(PacketMapBuffer<Packet<PacketListenerPlayOut>>[] maps, int index, Vector vec, EnumDirection dir) {
		EntityItemFrame eif = NesV1_8Util.createMapItemFrame(maps[index].getIdMap(), vec, dir);
		//packet entity
		int i = index * 2;
		this.spawnItemFrames[i] = new PacketPlayOutSpawnEntity(eif, 71, dir.b());
		i++;
		this.spawnItemFrames[i] = new PacketPlayOutEntityMetadata(eif.getId(), eif.getDataWatcher(), true);
		//id entity
		this.idItemFrames[index] = eif.getId();
	}
	
	/**
	 * Permet de faire apparaître a un joueur les écrans de la nes
	 * @param co la connection du joueur
	 */
	private void spawnItemsFrame(PlayerConnection co) {
		for (Packet<PacketListenerPlayOut> packet : this.spawnItemFrames) {
			co.sendPacket(packet);
		}
	}
	
	/**
	 * Permet de faire disparaître a un joueur les écrans de la nes
	 * @param co la connection du joueur
	 */
	private void despawnItemsFrame(PlayerConnection co) {
		co.sendPacket(this.despawnItemFrames);
	}

	@Override
	public void openController(PlayerController control) {
		for (EntityPlayer ep : this.viewers) {
			control.show(ep.getBukkitEntity());
		}
	}

	@Override
	public void closeController(PlayerController control) {
		for (EntityPlayer ep : this.viewers) {
			control.hide(ep.getBukkitEntity());
		}
	}

	@Override
	public void create(List<Player> list) {
		for (Player player : list) {//on affiche la nes pour les joueur qui son censé la voir
			this.show(player);
		}
	}

	@Override
	public void show(Player player) {
		EntityPlayer ep = ((CraftPlayer) player).getHandle();
		PlayerConnection co = ep.playerConnection;
		//spawn
		this.spawnItemsFrame(co);
		
		//first map view
		synchronized (this.getScreen()) {
			for (PacketMapBuffer<Packet<PacketListenerPlayOut>> packet : this.getMapPackets()) {
				co.sendPacket(packet.getPacket());
			}
		}
		
		//show crontroller
		synchronized (this.getObSync()) {
			//first
			PlayerController pc = this.getFirstPlayer();
			if (pc != null) {
				pc.show(player);
			}
			//second
			pc = this.getSecondPlayer();
			if (pc != null) {
				pc.show(player);
			}
		}
		
		//player add in list
		this.viewers.add(ep);
	}

	@Override
	public boolean canSee(Player player) {
		return this.viewers.contains(((CraftPlayer) player).getHandle());
	}
	
	@Override
	public void hide(Player player, boolean quit) {
		EntityPlayer ep = ((CraftPlayer) player).getHandle();
		//despawn
		this.despawnItemsFrame(ep.playerConnection);
		
		//si le joueur a quitter ou s'eloigne de trop
		int controller = quit ? TMCNes.QUIT : 0;//0 ne ferme aucun controlleur
		synchronized (this.getObSync()) {
			PlayerController pc = this.getFirstPlayer();
			if (pc != null && pc.getPlayer().equals(player)) {
				controller += TMCNes.FIRST_CONTROLLER;
			} else {
				pc = this.getSecondPlayer();
				if (pc != null && pc.getPlayer().equals(player)) {
					controller += TMCNes.SECOND_CONTROLLER;
				}
			}
		}
		this.closeController(controller);
		
		//hide crontroller
		synchronized (this.getObSync()) {
			//first
			PlayerController pc = this.getFirstPlayer();
			if (pc != null) {
				pc.hide(player);
			}
			//second
			pc = this.getSecondPlayer();
			if (pc != null) {
				pc.hide(player);
			}
		}
		
		//player remove in list
		this.viewers.remove(ep);
	}

	@Override
	public void destruct() {
		if (this.isStart()) {
			this.stop();
		}
		EntityPlayer ep;
		//clear all player
		while ((ep = this.viewers.peek()) != null) {//pour tous les joueur qui voie on les hide(hide les suprime)
			this.hide(ep.getBukkitEntity(), false);
		}
	}

	@Override
	public void sendPacket(Packet<PacketListenerPlayOut> packet) {
		for (EntityPlayer ep : this.viewers) {
			ep.playerConnection.sendPacket(packet);
		}
	}

	@Override
	public int haveIdItemFram(int idItemFrame) {
		for (int i = 0; i < 4; i++) {
			if (idItemFrames[i] == idItemFrame) {
				return (i % 2) == 0 ? 1 : 2;
			}
		}
		return 0;
	}

}
