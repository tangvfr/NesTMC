package fr.tangv.nestmc.game.v1_8;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import fr.tangv.nestmc.game.controller.PlayerController;
import fr.tangv.nestmc.nes.NesController;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayInEntityAction;
import net.minecraft.server.v1_8_R3.PacketPlayInHeldItemSlot;
import net.minecraft.server.v1_8_R3.PacketPlayInSteerVehicle;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;

/**
 * @author tangv
 * Permet de lier un joueur a un controlleur (une manette) pour la version 1.8.8
 */
public class PlayerControllerV1_8 extends PlayerController {

	/*NMS du joueur de la manette*/
	private EntityPlayer player;
	
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
		//vehicle input
		if (ob instanceof PacketPlayInSteerVehicle) {
			/*PacketPlayInSteerVehicle sv = (PacketPlayInSteerVehicle) ob;
			sv.a()//Positive to the left 
			sv.b()//Positive forward
			sv.c()//jump
			sv.d()//unmount
			//Bukkit.getLogger().info("left: "+sv.a()+" forward: "+sv.b()+" jump:"+sv.c()+" unmount: "+sv.d());
			
			return;*/
		}
		//open inventory
		if (ob instanceof PacketPlayInEntityAction) {
			/*PacketPlayInEntityAction ea = (PacketPlayInEntityAction) ob;
			if (ea.b() == EnumPlayerAction.OPEN_INVENTORY) {
				Bukkit.getLogger().info("action: "+ea.b().name());
				return;
			}*/
		}
		//held
		if (ob instanceof PacketPlayInHeldItemSlot) {
			/*PacketPlayInHeldItemSlot hi = (PacketPlayInHeldItemSlot) ob;
			
			Bukkit.getLogger().info("held: "+ (4-hi.a()));
			
			
			this.player.playerConnection.sendPacket(new PacketPlayOutHeldItemSlot(4));
			return;*/
		}
		//attack and interact
		if (ob instanceof PacketPlayInUseEntity) {
			/*PacketPlayInUseEntity ue = (PacketPlayInUseEntity) ob;
			if (ue.a() != EnumEntityUseAction.INTERACT) {
				int id = (int) ReflectionUtil.getValue("a", ob);
				if (id == this.oldd) {
					Bukkit.getLogger().info("useentity: "+ ue.a().name());
					return;
				}
			}*/
		}
		
		//other
		super.channelRead(chx, ob);
	}

	@Override
	public PlayerController getOwner() {
		return this;
	}

	@Override
	public void create(boolean first) {
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
	public void destruct() {
		// TODO Auto-generated method stub
		
	}
	
}
