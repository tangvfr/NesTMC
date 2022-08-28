/**
 * 
 */
package fr.tangv.nestmc.game.v1_8;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import fr.tangv.nestmc.NesTMC;
import fr.tangv.nestmc.game.McNes;
import fr.tangv.nestmc.game.McNesManager;
import fr.tangv.nestmc.game.PacketMapBuffer;
import fr.tangv.nestmc.game.controller.PlayerController;
import fr.tangv.nestmc.nes.controller.NesController;
import fr.tangv.nestmc.nes.software.NesOs;
import fr.tangv.nestmc.nes.software.os.tmcos.TMCNesOs;
import fr.tangv.nestmc.nes.software.test.GameTestNesOs;
import fr.tangv.nestmc.palette.v1_8.McNesPaletteV1_8;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketListenerPlayOut;
import net.minecraft.server.v1_8_R3.PlayerConnection;

/**
 * @author Tangv - https://tangv.fr
 * Gestionnaire des nes sur le serveur minecraft 1.8
 */
public class McNesManagerV1_8 extends McNesManager<Packet<PacketListenerPlayOut>> {

	/**
	 * Permet de construire un gestionnaire de consoles
	 * @param plugin le plugin du gestionaire
	 * @param config la configuration et les messages des console
	 */
	public McNesManagerV1_8(NesTMC plugin, FileConfiguration config) {
		super(plugin, config);
	}

	@Override
	public PlayerController createPlayerController(Player player, NesController controller, boolean isFirst) {
		PlayerController pc = new PlayerControllerV1_8(player, controller, this.config.getInt("param.pers_key_tick", 2));
		pc.create(isFirst);
		return pc;
	}

	@Override
	public PacketMapBuffer<Packet<PacketListenerPlayOut>> createPacketMapBuffer() {
		return new PacketMapBufferV1_8(this);
	}

	@Override
	public byte[] getPalette() {
		return McNesPaletteV1_8.MC_NES_PALETTE;
	}
	
	@Override
	protected McNes<Packet<PacketListenerPlayOut>> newConsole(Location loc, OfflinePlayer owner) {
		return new McNesV1_8(this, loc, owner);
	}

	@Override
	public NesOs createNesOs() {
		return new GameTestNesOs();
	}

	@Override
	public void playerJoin(Player player) {
		EntityPlayer ep = ((CraftPlayer) player).getHandle();
		ep.playerConnection.networkManager.channel.pipeline().addBefore(
				"packet_handler",
				ep.getName(), 
				new PlayerInteractNesEventChannelV1_8(this, player)
			);
	}
	
	@Override
	public void playerReload(Player player) {
		EntityPlayer ep = ((CraftPlayer) player).getHandle();
		ep.playerConnection.networkManager.channel.pipeline().remove(ep.getName());
	}
	
	@Override
	public void playerQuit(Player player) {
		EntityPlayer ep = ((CraftPlayer) player).getHandle();
		PlayerConnection co = ep.playerConnection;
		co.networkManager.channel.eventLoop().submit(() -> {
			co.networkManager.channel.pipeline().remove(ep.getName());
		});
	}

}
