package fr.tangv.nestmc.util.tool;

/**
 * @author tangv
 * Permet de généré nom des couleur des maps minecraft
 */
public class MapColorGenerator {

	//https://minecraft.fandom.com/wiki/Map_item_format#Full_color_tables
	// \t(\d{1,3})\t\t[^\t]*\t(.*)
	// \tpublic static byte $2 = $1;
	
	public static void main(String[] args) {
		final String[] SUF_NAME = {
				"_SHADOW",
				"_NORMAL",
				"_LIGTH",
				"_DARK"
		};
		
		final String[] COLOR_NAME = {
				"TRANSPARENT",
				"SLIME",
				"SAND",
				"COBWEB",
				"LAVA",
				"ICE",
				"IRON",
				"GRASS",
				"SNOW",
				"CLAY",
				"JUNGLE_PLANK",
				"STONE",
				"OAK",
				"WATER",
				"QUARTZ",
				"ORANGE",
				"MAGENTA",
				"LIGHT_BLUE",
				"YELLOW",
				"LIME",
				"PINK",
				"GRAY",
				"LIGHT_GRAY",
				"CYAN",
				"PURPLE",
				"BLUE",
				"BROWN",
				"GREEN",
				"RED",
				"BLACK",
				"GOLD",
				"DIAMOND",
				"LAPIS",
				"EMERALD",
				"SPRUCE_PLANK",
				"NETHER_BRICK",
		};
		
		//generate number
		//public static byte
	}
	
}
