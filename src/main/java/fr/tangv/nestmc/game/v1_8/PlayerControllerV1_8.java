package fr.tangv.nestmc.game.v1_8;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import fr.tangv.nestmc.game.controller.PlayerController;
import fr.tangv.nestmc.nes.controller.InputController;
import fr.tangv.nestmc.nes.controller.NesController;
import fr.tangv.nestmc.util.ReflectionUtil;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.EntityHorse;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayInEntityAction;
import net.minecraft.server.v1_8_R3.PacketPlayInEntityAction.EnumPlayerAction;
import net.minecraft.server.v1_8_R3.PacketPlayInHeldItemSlot;
import net.minecraft.server.v1_8_R3.PacketPlayInSteerVehicle;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import net.minecraft.server.v1_8_R3.PacketPlayOutAttachEntity;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity.EnumEntityUseAction;
import net.minecraft.server.v1_8_R3.PacketPlayOutHeldItemSlot;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;

/**
 * @author tangv
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destruct(boolean reasonQuit) {
		// TODO Auto-generated method stub
		
	}
	
}
