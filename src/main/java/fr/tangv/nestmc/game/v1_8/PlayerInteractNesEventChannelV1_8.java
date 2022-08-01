/**
 * 
 */
package fr.tangv.nestmc.game.v1_8;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.tangv.nestmc.game.McNes;
import fr.tangv.nestmc.game.McNesManager;
import fr.tangv.nestmc.game.event.PlayerInteractNesEvent;
import fr.tangv.nestmc.util.ReflectionUtil;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity.EnumEntityUseAction;

/**
 * @author Tangv - https://tangv.fr
 * Permet d'attraper les interaction avec les itemframe des écran des nes qui n'existe pas sur le serveur
 */
public class PlayerInteractNesEventChannelV1_8 extends ChannelDuplexHandler {

	/**
	 * joueur a qui est associer le channel
	 */
	private final Player player;
	/*
	 * le gestionnaire de console
	 * */
	private final McNesManager<?> manager;
	
	/**
	 * Permet de construire l'attrapeur d'interaction avec les écran de nes pour un joueur
	 * @param player le joueur
	 * @param manager le gestionnaire de console du serveur
	 */
	public PlayerInteractNesEventChannelV1_8(McNesManager<?> manager, Player player) {
		this.manager = manager;
		this.player = player;
	}
	
	@Override
	public void channelRead(ChannelHandlerContext chx, Object ob) throws Exception {
		if (ob instanceof PacketPlayInUseEntity) {//ajouter cela pour interagir avec le pannel "primaire de la NES"
			 PacketPlayInUseEntity use = (PacketPlayInUseEntity) ob;
			 EnumEntityUseAction ac = use.a();
			 int idE = (int) ReflectionUtil.getValue(use, "a");
			 //System.out.println("ac: "+ac.name()+" id:"+idE);
			 //test with screens
			 McNes<?> nes = this.manager.getNes(idE);
			 if (nes != null) {
				 //call event
				 Bukkit.getPluginManager().callEvent(new PlayerInteractNesEvent(
						 player,
						 nes,
						 ac == EnumEntityUseAction.ATTACK
						 ));
				 return;
			 }
		}
		
		super.channelRead(chx, ob);
	}
	
}
