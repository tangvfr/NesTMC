package fr.tangv.nestmc.game;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import net.minecraft.server.v1_8_R3.EntityHorse;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class TestListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		
	}
	
	@EventHandler
	public void onSneak(PlayerToggleSneakEvent e){
		CraftPlayer player = (CraftPlayer) e.getPlayer();
		EntityPlayer ep = player.getHandle();
		PlayerConnection co = ep.playerConnection;
		
		
		
		/*PacketPlayOutAttachEntity packet = new PacketPlayOutAttachEntity();
		ReflectionUtil.setValue("a", packet, target.getEntityId());//player
		ReflectionUtil.setValue("b", packet, mount ? this.idVehicle : -1);//vehicle
		ReflectionUtil.setValue("c", packet, 0);//attacher par laisse
		co.sendPacket(packet);*/
		
		EntityHorse h = new EntityHorse(null);
		h.setPositionRotation(ep.locX, ep.locY, ep.locZ, ep.pitch, ep.yaw);
		co.sendPacket(new PacketPlayOutSpawnEntityLiving(h));
		
		
		
	}
	
}
