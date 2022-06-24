package fr.tangv.nestmc.palette.tool;

/**
 * @author tangv
 * Permet de généré nom des couleur des maps minecraft
 */
public class MapColorGeneratorV1_8 {

	//https://minecraft.fandom.com/wiki/Map_item_format#Full_color_tables
	// \t(\d{1,3})\t\t[^\t]*\t(.*)
	// \tpublic static byte $2 = $1;
	
	public static void main(String[] args) {
		final String[] SUF_NAMES = {
				"_SHADOW",
				"_NORMAL",
				"_LIGTH",
				"_DARK"
		};
		
		final String[] COLOR_NAMES = {
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
		int iColor = 0;//numéro de couleur
		for (String colorName : COLOR_NAMES) {//pour chaque nom de couleur
			for (String sufName : SUF_NAMES) {//pour chaque sufix de nom de couleur
				System.out.printf(
						"\tpublic static final byte %s%s = (byte) %d;\n",
						colorName, sufName, iColor
						);
				iColor++;
			}
		}
	}
	
}
