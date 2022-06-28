package fr.tangv.nestmc.game.v1_8;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import fr.tangv.nestmc.game.controller.PlayerController;
import fr.tangv.nestmc.nes.controller.InputController;
import fr.tangv.nestmc.nes.controller.NesController;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.EntityHorse;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.PacketPlayInEntityAction;
import net.minecraft.server.v1_8_R3.PacketPlayInEntityAction.EnumPlayerAction;
import net.minecraft.server.v1_8_R3.PacketPlayInHeldItemSlot;
import net.minecraft.server.v1_8_R3.PacketPlayInSteerVehicle;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity.EnumEntityUseAction;
import net.minecraft.server.v1_8_R3.PacketPlayOutAttachEntity;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment;
import net.minecraft.server.v1_8_R3.PacketPlayOutHeldItemSlot;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.Vector3f;

/**
 * @author Tangv - https://tangv.fr
 * Permet de lier un joueur a un controlleur (une manette) pour la version 1.8.8
 */
public class PlayerControllerV1_8 extends PlayerController {

	/*NMS du joueur de la manette*/
	private EntityPlayer player;
	
	/*entité sur laquelle est assie le player*/
	private EntityHorse vehicle;
	/*entité pour obtenir les attack et interct entity*/
	private EntityArmorStand aim;
	/*entoté de deco de siege*/
	private EntityArmorStand sofa;
	
	/**
	 * Construteur de contrustruire un récupérateur d'entré d'un joueur
	 * @param player joueur sur qui on va récupéré les infos
	 * @param controller controler (manette) a la qu'elle on est associe
	 */
	public PlayerControllerV1_8(Player player, NesController controller) {
		super(player, controller);
		this.player = ((CraftPlayer) player).getHandle();
	}

	/**
	 * Permet de récupéré les touche du joueur
	 */
	@Override
	public void channelRead(ChannelHandlerContext chx, Object ob) throws Exception {
		if (ob instanceof PacketPlayInSteerVehicle) {//vehicle input
			PacketPlayInSteerVehicle sv = (PacketPlayInSteerVehicle) ob;
			
			//Positive to the left
			this.setFloatButton(sv.a(), InputController.LEFT, InputController.RIGHT);
			//Positive forward
			this.setFloatButton(sv.b(), InputController.UP, InputController.DOWN);
			//jump
			this.setBooleanButton(sv.c(), InputController.SPACE);
			//unmount
			this.setBooleanButton(sv.d(), InputController.SNEAK);
			
			return;
		} else if (ob instanceof PacketPlayInEntityAction) {//open inventory
			PacketPlayInEntityAction ea = (PacketPlayInEntityAction) ob;
			
			if (ea.b() == EnumPlayerAction.OPEN_INVENTORY) {//open inventory
				this.getController().pressButton(InputController.OPEN_INV);
			}
			return;
		} else if (ob instanceof PacketPlayInUseEntity) {//attack and interact
			PacketPlayInUseEntity ue = (PacketPlayInUseEntity) ob;
			//int id = (int) ReflectionUtil.getValue("a", ob);
			//test is good entity
			//if (id == this.vehicle.getId() || id == this.aim.getId() || id == this.sofa.getId()) {
			//this is enable when on vehicle
			if (ue.a() != EnumEntityUseAction.ATTACK) {
				this.getController().pressButton(InputController.ATTACK);
			} else {
				this.getController().pressButton(InputController.INTERACT);
			}
			return;
		} else if (ob instanceof PacketPlayInHeldItemSlot) {//held
			PacketPlayInHeldItemSlot hi = (PacketPlayInHeldItemSlot) ob;
			
			int held = hi.a();
			if (held != 4) {//different du millieu
				if (held < 4) {
					held++;
				}
				this.getController().setHeld(held);
			}
			
			//reset du held
			this.player.playerConnection.sendPacket(new PacketPlayOutHeldItemSlot(4));
			return;
		}
		
		//other
		super.channelRead(chx, ob);
	}
	
	/**
	 * Permet de definir un bouton en fonction d'une valeur
	 * @param value valeur true ou false
	 * @param btn bouton qui sera presser si true
	 */
	private void setBooleanButton(boolean value, int btn) {
		NesController nc = this.getController();
		if (value) {//true
			nc.pressButton(btn);
		} else {//false
			nc.releaseButton(btn);
		}
	}
	
	/**
	 * Permet de definir deux bouton en fonction d'une valeur
	 * @param value valeur négative, positive ou null
	 * @param btnPos bouton qui sera presser si positive
	 * @param btnNeg bouton qui sera presser si negative
	 */
	private void setFloatButton(float value, int btnPos, int btnNeg) {
		NesController nc = this.getController();
		if (value > 0) {//+
			nc.pressButton(btnPos);
			nc.releaseButton(btnNeg);
		} else if (value < 0) {//-
			nc.releaseButton(btnPos);
			nc.pressButton(btnNeg);
		} else {//null
			nc.releaseButton(btnPos);
			nc.releaseButton(btnNeg);
		}
	}

	@Override
	public void updateController() {
		NesController nc = this.getController();
		nc.update();
		//release les bouton qui le fond pas eux meme
		nc.setHeld(0);
		nc.releaseButton(InputController.OPEN_INV);
		nc.releaseButton(InputController.ATTACK);
		nc.releaseButton(InputController.INTERACT);
	}
	
	@Override
	public void create(boolean isFirst) {
		//variable
		Player player = this.getPlayer();
		double locX = this.player.locX;
		double locY = this.player.locY;
		double locZ = this.player.locZ;
		float yaw = this.player.yaw;
		
		//create vehicle
		this.vehicle = new EntityHorse(null);//try with new EntityHorse(this.player.getWorld());----
		//this.vehicle.setInvisible(true);
		this.vehicle.setHealth(2F);
		this.vehicle.setType(3);
		this.vehicle.onGround = false;
		this.vehicle.setPositionRotation(locX, locY - 1.2, locZ, yaw, 0);
		this.vehicle.f(yaw);//set head rotation
		
		//create aim
		this.aim = new EntityArmorStand(null);
		this.aim.setPosition(locX, locY - 0.2, locZ);
		//this.aim.setInvisible(true);

		//create sofa
		this.sofa = new EntityArmorStand(null);
		this.sofa.setPositionRotation(locX, locY - 0.5, locZ, yaw - 45F, 0F);//45° wool sofa
		this.sofa.setHeadPose(new Vector3f(0F, 0F, 180F));//head to down
		this.sofa.setSmall(true);
		this.sofa.setInvisible(true);//work
		this.sofa.setEquipment(4, new ItemStack(Item.getById(35), 1, isFirst ? 10 : 9));//4 = HEAD, 35 = WOOL, 10 = MAGENTA 9 = CYAN
		
		//only controller player
		player.getInventory().setHeldItemSlot(4);//set held 4
		this.player.playerConnection.sendPacket(new PacketPlayOutSpawnEntityLiving(this.aim));//spawn aim
		//action
		this.getController().resetButtons();
		//handler
		this.player.playerConnection.networkManager.channel.pipeline().addBefore("handler_sofa", "handler_sofa", this);
	}

	@Override
	public void show(Player player) {
		PlayerConnection co = ((CraftPlayer) player).getHandle().playerConnection;
		co.sendPacket(new PacketPlayOutSpawnEntityLiving(this.vehicle));//spawn vehicle
		co.sendPacket(new PacketPlayOutAttachEntity(0, this.player, this.vehicle));//mount controller player
		co.sendPacket(new PacketPlayOutSpawnEntityLiving(this.sofa));//spawn sofa
		co.sendPacket(new PacketPlayOutEntityEquipment(this.sofa.getId(), 4, this.sofa.getEquipment(4)));//set item on sofa 4 = HEAD
	}

	@Override
	public void hide(Player player) {
		PlayerConnection co = ((CraftPlayer) player).getHandle().playerConnection;
		co.sendPacket(new PacketPlayOutAttachEntity(0, this.player, null));//dismount controller player
		co.sendPacket(new PacketPlayOutEntityDestroy(this.vehicle.getId()));//despawn vehicle
		co.sendPacket(new PacketPlayOutEntityDestroy(this.sofa.getId()));//despawn sofa
	}

	@Override
	public void destruct(boolean reasonQuit) {
		//only controller player
		this.player.playerConnection.sendPacket(new PacketPlayOutEntityDestroy(this.aim.getId()));//despawn aim
		//action
		this.getController().resetButtons();
		//handler
		Channel ch = this.player.playerConnection.networkManager.channel;
		if (reasonQuit) {
			ch.eventLoop().submit(() -> {
				ch.pipeline().remove(this);
			});
		} else {
			ch.pipeline().remove(this);
		}
	}
	
}
