/**
 * 
 */
package fr.tangv.nestmc.game.v1_8;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.util.Vector;

/**
 * @author Tangv - https://tangv.fr
 * Set de fonction utile pour la MCNESV1_8
 */
public class NesV1_8Util {

	/**
	 * Permet d'obtenir le vecteur corespondent a la gauche de la direction
	 * @param face la direction donné
	 * @return le veteur allant a gauche de la direction (direction vers nous)
	 */
	public static Vector leftVectorOf(EnumDirection dir) {
		if (dir == EnumDirection.EAST) return new Vector(0, 0, 1);//NORTH
		if (dir == EnumDirection.SOUTH) return new Vector(-1, 0, 0);//EAST
		if (dir == EnumDirection.WEST) return new Vector(0, 0, -1);//SOUTH
		return new Vector(1, 0, 0);//WEST
	}
	
	/**
	 * Permet de crée un itemFrame avec une map dedans en nms
	 * @param idMap identifiant de la map
	 * @param vec position de l'item frame
	 * @param dir direction de l'item frame
	 * @return l'itemFrame crée
	 */
	public static EntityItemFrame createMapItemFrame(short idMap, Vector vec, EnumDirection dir) {
		EntityItemFrame eif = new EntityItemFrame(null);
		eif.setPosition(vec.getX(), vec.getY(), vec.getZ());
		eif.setDirection(dir);
		//set item
		DataWatcher dw = eif.getDataWatcher();
		dw.watch(8, new ItemStack(Items.FILLED_MAP, 1, idMap));
	    dw.update(8);
	    return eif;
	}
	
}
