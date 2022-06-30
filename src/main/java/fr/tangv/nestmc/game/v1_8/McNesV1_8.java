/**
 * 
 */
package fr.tangv.nestmc.game.v1_8;

import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import fr.tangv.nestmc.game.McNes;
import fr.tangv.nestmc.game.McNesManager;
import fr.tangv.nestmc.game.controller.PlayerController;
import net.minecraft.server.v1_8_R3.DataWatcher;
import net.minecraft.server.v1_8_R3.EntityItemFrame;
import net.minecraft.server.v1_8_R3.EnumDirection;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.Items;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_8_R3.PacketPlayOutMap;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntity;

/**
 * @author Tangv - https://tangv.fr
 * une nes dans minecraft pour la v1_8_R3
 */
public class McNesV1_8 extends McNes<PacketPlayOutMap> {

	/**
	 * Permet de construire 
	 * @param manager
	 * @param blocks
	 */
	public McNesV1_8(McNesManager<PacketPlayOutMap> manager, Vector vec) {
		super(manager, blocks);
		
		

		//EnumDirection.fromType2((dir.b() + 1) %4);//vers la droite a tester
		//EnumDirection.fromType2((dir.b() + 3) %4);//vers la gauche a tester
		/*co.sendPacket(new PacketPlayOutSpawnEntity(eif, 71, dir.b()));
		co.sendPacket(new PacketPlayOutEntityMetadata(eif.getId(), dw, true));*/
		
	}
	
	private EntityItemFrame createEntityItemFrameMap(short idMap ,Vector vec, EnumDirection dir) {
		EntityItemFrame eif = new EntityItemFrame(null);
		eif.setPosition(vec.getX(), vec.getY(), vec.getZ());
		eif.setDirection(dir);
		//set item
		DataWatcher dw = eif.getDataWatcher();
		dw.watch(8, new ItemStack(Items.FILLED_MAP, 1, idMap));
	    dw.update(8);
	    return eif;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide(Player player, boolean quit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destruct() {
		// TODO Auto-generated method stub
		
	}

}
