package fr.tangv.nestmc.util;

/**
 * @author tangv
 * Couleurs des maps minecraft en rgb
 */
public class MapColor {
	
	// \t(\d{1,3})\t\t([0-9-]{1,3}), ([0-9-]{1,3}), ([0-9-]{1,3})\t(.*)
	// {(byte) $2, (byte) $3, (byte) $4}, //n°$1 $5
	
	public static final byte[][] MC_COLOR = new byte[][] {
		{(byte) -1, (byte) -1, (byte) -1}, //n°0 Transparent	Not explored
		{(byte) -1, (byte) -1, (byte) -1}, //n°1 Transparent	Not explored
		{(byte) -1, (byte) -1, (byte) -1}, //n°2 Transparent	Not explored
		{(byte) -1, (byte) -1, (byte) -1}, //n°3 Transparent	Not explored
		{(byte) 89, (byte) 125, (byte) 39}, //n°4 Grass, Slime Block
		{(byte) 109, (byte) 153, (byte) 48}, //n°5 Grass, Slime Block
		{(byte) 127, (byte) 178, (byte) 56}, //n°6 Grass, Slime Block
		{(byte) 67, (byte) 94, (byte) 29}, //n°7 Grass, Slime Block
		{(byte) 174, (byte) 164, (byte) 115}, //n°8 Sand, Sandstone, Birch plank, Glowstone, Endstone
		{(byte) 213, (byte) 201, (byte) 140}, //n°9 Sand, Sandstone, Birch plank, Glowstone, Endstone
		{(byte) 247, (byte) 233, (byte) 163}, //n°10 Sand, Sandstone, Birch plank, Glowstone, Endstone
		{(byte) 130, (byte) 123, (byte) 86}, //n°11 Sand, Sandstone, Birch plank, Glowstone, Endstone
		{(byte) 138, (byte) 138, (byte) 138}, //n°12 Bed, Cobweb
		{(byte) 169, (byte) 169, (byte) 169}, //n°13 Bed, Cobweb
		{(byte) 197, (byte) 197, (byte) 197}, //n°14 Bed, Cobweb
		{(byte) 104, (byte) 104, (byte) 104}, //n°15 Bed, Cobweb
		{(byte) 180, (byte) 0, (byte) 0}, //n°16 Lava, TNT, Redstone Block
		{(byte) 220, (byte) 0, (byte) 0}, //n°17 Lava, TNT, Redstone Block
		{(byte) 255, (byte) 0, (byte) 0}, //n°18 Lava, TNT, Redstone Block
		{(byte) 135, (byte) 0, (byte) 0}, //n°19 Lava, TNT, Redstone Block
		{(byte) 112, (byte) 112, (byte) 180}, //n°20 Ice, Packed Ice
		{(byte) 138, (byte) 138, (byte) 220}, //n°21 Ice, Packed Ice
		{(byte) 160, (byte) 160, (byte) 255}, //n°22 Ice, Packed Ice
		{(byte) 84, (byte) 84, (byte) 135}, //n°23 Ice, Packed Ice
		{(byte) 117, (byte) 117, (byte) 117}, //n°24 Iron Block, Iron items, Brewing Stand
		{(byte) 144, (byte) 144, (byte) 144}, //n°25 Iron Block, Iron items, Brewing Stand
		{(byte) 167, (byte) 167, (byte) 167}, //n°26 Iron Block, Iron items, Brewing Stand
		{(byte) 88, (byte) 88, (byte) 88}, //n°27 Iron Block, Iron items, Brewing Stand
		{(byte) 0, (byte) 87, (byte) 0}, //n°28 Leaves, Flowers, Grass
		{(byte) 0, (byte) 106, (byte) 0}, //n°29 Leaves, Flowers, Grass
		{(byte) 0, (byte) 124, (byte) 0}, //n°30 Leaves, Flowers, Grass
		{(byte) 0, (byte) 65, (byte) 0}, //n°31 Leaves, Flowers, Grass
		{(byte) 180, (byte) 180, (byte) 180}, //n°32 Wool, Snow
		{(byte) 220, (byte) 220, (byte) 220}, //n°33 Wool, Snow
		{(byte) 255, (byte) 255, (byte) 255}, //n°34 Wool, Snow
		{(byte) 135, (byte) 135, (byte) 135}, //n°35 Wool, Snow
		{(byte) 115, (byte) 118, (byte) 129}, //n°36 Clay
		{(byte) 141, (byte) 144, (byte) 158}, //n°37 Clay
		{(byte) 164, (byte) 168, (byte) 184}, //n°38 Clay
		{(byte) 86, (byte) 88, (byte) 97}, //n°39 Clay
		{(byte) 129, (byte) 74, (byte) 33}, //n°40 Dirt, Coarse Dirt, Jungle Plank, Granite
		{(byte) 157, (byte) 91, (byte) 40}, //n°41 Dirt, Coarse Dirt, Jungle Plank, Granite
		{(byte) 183, (byte) 106, (byte) 47}, //n°42 Dirt, Coarse Dirt, Jungle Plank, Granite
		{(byte) 96, (byte) 56, (byte) 24}, //n°43 Dirt, Coarse Dirt, Jungle Plank, Granite
		{(byte) 79, (byte) 79, (byte) 79}, //n°44 Stone, Cobblestone, Ores, Acacia Log, *Many others*
		{(byte) 96, (byte) 96, (byte) 96}, //n°45 Stone, Cobblestone, Ores, Acacia Log, *Many others*
		{(byte) 112, (byte) 112, (byte) 112}, //n°46 Stone, Cobblestone, Ores, Acacia Log, *Many others*
		{(byte) 59, (byte) 59, (byte) 59}, //n°47 Stone, Cobblestone, Ores, Acacia Log, *Many others*
		{(byte) 101, (byte) 84, (byte) 51}, //n°48 Oak Plank, Wooden Items, Mushroom (block), Banners, Daylight Sensor
		{(byte) 123, (byte) 103, (byte) 62}, //n°49 Oak Plank, Wooden Items, Mushroom (block), Banners, Daylight Sensor
		{(byte) 143, (byte) 119, (byte) 72}, //n°50 Oak Plank, Wooden Items, Mushroom (block), Banners, Daylight Sensor
		{(byte) 76, (byte) 63, (byte) 38}, //n°51 Oak Plank, Wooden Items, Mushroom (block), Banners, Daylight Sensor
		{(byte) 45, (byte) 45, (byte) 180}, //n°52 Water
		{(byte) 55, (byte) 55, (byte) 220}, //n°53 Water
		{(byte) 64, (byte) 64, (byte) 255}, //n°54 Water
		{(byte) 33, (byte) 33, (byte) 135}, //n°55 Water
		{(byte) 180, (byte) 177, (byte) 172}, //n°56 Quartz, Sea Lantern, Birch Log
		{(byte) 220, (byte) 217, (byte) 211}, //n°57 Quartz, Sea Lantern, Birch Log
		{(byte) 255, (byte) 252, (byte) 245}, //n°58 Quartz, Sea Lantern, Birch Log
		{(byte) 135, (byte) 133, (byte) 129}, //n°59 Quartz, Sea Lantern, Birch Log
		{(byte) 152, (byte) 89, (byte) 36}, //n°60 Orange Wool/Glass/Stained Clay, Pumpkin, Hardened Clay, Acacia Plank
		{(byte) 186, (byte) 109, (byte) 44}, //n°61 Orange Wool/Glass/Stained Clay, Pumpkin, Hardened Clay, Acacia Plank
		{(byte) 216, (byte) 127, (byte) 51}, //n°62 Orange Wool/Glass/Stained Clay, Pumpkin, Hardened Clay, Acacia Plank
		{(byte) 114, (byte) 67, (byte) 27}, //n°63 Orange Wool/Glass/Stained Clay, Pumpkin, Hardened Clay, Acacia Plank
		{(byte) 125, (byte) 53, (byte) 152}, //n°64 Magenta Wool/Glass/Stained Clay
		{(byte) 153, (byte) 65, (byte) 186}, //n°65 Magenta Wool/Glass/Stained Clay
		{(byte) 178, (byte) 76, (byte) 216}, //n°66 Magenta Wool/Glass/Stained Clay
		{(byte) 94, (byte) 40, (byte) 114}, //n°67 Magenta Wool/Glass/Stained Clay
		{(byte) 72, (byte) 108, (byte) 152}, //n°68 Light Blue Wool/Glass/Stained Clay
		{(byte) 88, (byte) 132, (byte) 186}, //n°69 Light Blue Wool/Glass/Stained Clay
		{(byte) 102, (byte) 153, (byte) 216}, //n°70 Light Blue Wool/Glass/Stained Clay
		{(byte) 54, (byte) 81, (byte) 114}, //n°71 Light Blue Wool/Glass/Stained Clay
		{(byte) 161, (byte) 161, (byte) 36}, //n°72 Yellow Wool/Glass/Stained Clay, Sponge, Hay Bale
		{(byte) 197, (byte) 197, (byte) 44}, //n°73 Yellow Wool/Glass/Stained Clay, Sponge, Hay Bale
		{(byte) 229, (byte) 229, (byte) 51}, //n°74 Yellow Wool/Glass/Stained Clay, Sponge, Hay Bale
		{(byte) 121, (byte) 121, (byte) 27}, //n°75 Yellow Wool/Glass/Stained Clay, Sponge, Hay Bale
		{(byte) 89, (byte) 144, (byte) 17}, //n°76 Lime Wool/Glass/Stained Clay, Melon
		{(byte) 109, (byte) 176, (byte) 21}, //n°77 Lime Wool/Glass/Stained Clay, Melon
		{(byte) 127, (byte) 204, (byte) 25}, //n°78 Lime Wool/Glass/Stained Clay, Melon
		{(byte) 67, (byte) 108, (byte) 13}, //n°79 Lime Wool/Glass/Stained Clay, Melon
		{(byte) 170, (byte) 89, (byte) 116}, //n°80 Pink Wool/Glass/Stained Clay
		{(byte) 208, (byte) 109, (byte) 142}, //n°81 Pink Wool/Glass/Stained Clay
		{(byte) 242, (byte) 127, (byte) 165}, //n°82 Pink Wool/Glass/Stained Clay
		{(byte) 128, (byte) 67, (byte) 87}, //n°83 Pink Wool/Glass/Stained Clay
		{(byte) 53, (byte) 53, (byte) 53}, //n°84 Gray Wool/Glass/Stained Clay
		{(byte) 65, (byte) 65, (byte) 65}, //n°85 Gray Wool/Glass/Stained Clay
		{(byte) 76, (byte) 76, (byte) 76}, //n°86 Gray Wool/Glass/Stained Clay
		{(byte) 40, (byte) 40, (byte) 40}, //n°87 Gray Wool/Glass/Stained Clay
		{(byte) 108, (byte) 108, (byte) 108}, //n°88 Light Gray Wool/Glass/Stained Clay
		{(byte) 132, (byte) 132, (byte) 132}, //n°89 Light Gray Wool/Glass/Stained Clay
		{(byte) 153, (byte) 153, (byte) 153}, //n°90 Light Gray Wool/Glass/Stained Clay
		{(byte) 81, (byte) 81, (byte) 81}, //n°91 Light Gray Wool/Glass/Stained Clay
		{(byte) 53, (byte) 89, (byte) 108}, //n°92 Cyan Wool/Glass/Stained Clay
		{(byte) 65, (byte) 109, (byte) 132}, //n°93 Cyan Wool/Glass/Stained Clay
		{(byte) 76, (byte) 127, (byte) 153}, //n°94 Cyan Wool/Glass/Stained Clay
		{(byte) 40, (byte) 67, (byte) 81}, //n°95 Cyan Wool/Glass/Stained Clay
		{(byte) 89, (byte) 44, (byte) 125}, //n°96 Purple Wool/Glass/Stained Clay, Mycelium
		{(byte) 109, (byte) 54, (byte) 153}, //n°97 Purple Wool/Glass/Stained Clay, Mycelium
		{(byte) 127, (byte) 63, (byte) 178}, //n°98 Purple Wool/Glass/Stained Clay, Mycelium
		{(byte) 67, (byte) 33, (byte) 94}, //n°99 Purple Wool/Glass/Stained Clay, Mycelium
		{(byte) 36, (byte) 53, (byte) 125}, //n°100 Blue Wool/Glass/Stained Clay
		{(byte) 44, (byte) 65, (byte) 153}, //n°101 Blue Wool/Glass/Stained Clay
		{(byte) 51, (byte) 76, (byte) 178}, //n°102 Blue Wool/Glass/Stained Clay
		{(byte) 27, (byte) 40, (byte) 94}, //n°103 Blue Wool/Glass/Stained Clay
		{(byte) 72, (byte) 53, (byte) 36}, //n°104 Brown Wool/Glass/Stained Clay, Soul Sand, Dark Oak Plank
		{(byte) 88, (byte) 65, (byte) 44}, //n°105 Brown Wool/Glass/Stained Clay, Soul Sand, Dark Oak Plank
		{(byte) 102, (byte) 76, (byte) 51}, //n°106 Brown Wool/Glass/Stained Clay, Soul Sand, Dark Oak Plank
		{(byte) 54, (byte) 40, (byte) 27}, //n°107 Brown Wool/Glass/Stained Clay, Soul Sand, Dark Oak Plank
		{(byte) 72, (byte) 89, (byte) 36}, //n°108 Green Wool/Glass/Stained Clay, End Portal Frame
		{(byte) 88, (byte) 109, (byte) 44}, //n°109 Green Wool/Glass/Stained Clay, End Portal Frame
		{(byte) 102, (byte) 127, (byte) 51}, //n°110 Green Wool/Glass/Stained Clay, End Portal Frame
		{(byte) 54, (byte) 67, (byte) 27}, //n°111 Green Wool/Glass/Stained Clay, End Portal Frame
		{(byte) 108, (byte) 36, (byte) 36}, //n°112 Red Wool/Glass/Stained Clay, Huge Red Mushroom, Brick, Enchanting Table
		{(byte) 132, (byte) 44, (byte) 44}, //n°113 Red Wool/Glass/Stained Clay, Huge Red Mushroom, Brick, Enchanting Table
		{(byte) 153, (byte) 51, (byte) 51}, //n°114 Red Wool/Glass/Stained Clay, Huge Red Mushroom, Brick, Enchanting Table
		{(byte) 81, (byte) 27, (byte) 27}, //n°115 Red Wool/Glass/Stained Clay, Huge Red Mushroom, Brick, Enchanting Table
		{(byte) 17, (byte) 17, (byte) 17}, //n°116 Black Wool/Glass/Stained Clay, Dragon Egg, Block of Coal, Obsidian
		{(byte) 21, (byte) 21, (byte) 21}, //n°117 Black Wool/Glass/Stained Clay, Dragon Egg, Block of Coal, Obsidian
		{(byte) 25, (byte) 25, (byte) 25}, //n°118 Black Wool/Glass/Stained Clay, Dragon Egg, Block of Coal, Obsidian
		{(byte) 13, (byte) 13, (byte) 13}, //n°119 Black Wool/Glass/Stained Clay, Dragon Egg, Block of Coal, Obsidian
		{(byte) 176, (byte) 168, (byte) 54}, //n°120 Block of Gold, Weighted Pressure Plate (Light)
		{(byte) 215, (byte) 205, (byte) 66}, //n°121 Block of Gold, Weighted Pressure Plate (Light)
		{(byte) 250, (byte) 238, (byte) 77}, //n°122 Block of Gold, Weighted Pressure Plate (Light)
		{(byte) 132, (byte) 126, (byte) 40}, //n°123 Block of Gold, Weighted Pressure Plate (Light)
		{(byte) 64, (byte) 154, (byte) 150}, //n°124 Block of Diamond, Prismarine, Prismarine Bricks, Dark Prismarine, Beacon
		{(byte) 79, (byte) 188, (byte) 183}, //n°125 Block of Diamond, Prismarine, Prismarine Bricks, Dark Prismarine, Beacon
		{(byte) 92, (byte) 219, (byte) 213}, //n°126 Block of Diamond, Prismarine, Prismarine Bricks, Dark Prismarine, Beacon
		{(byte) 48, (byte) 115, (byte) 112}, //n°127 Block of Diamond, Prismarine, Prismarine Bricks, Dark Prismarine, Beacon
		{(byte) 52, (byte) 90, (byte) 180}, //n°128 Lapis Lazuli Block
		{(byte) 63, (byte) 110, (byte) 220}, //n°129 Lapis Lazuli Block
		{(byte) 74, (byte) 128, (byte) 255}, //n°130 Lapis Lazuli Block
		{(byte) 39, (byte) 67, (byte) 135}, //n°131 Lapis Lazuli Block
		{(byte) 0, (byte) 153, (byte) 40}, //n°132 Block of Emerald
		{(byte) 0, (byte) 187, (byte) 50}, //n°133 Block of Emerald
		{(byte) 0, (byte) 217, (byte) 58}, //n°134 Block of Emerald
		{(byte) 0, (byte) 114, (byte) 30}, //n°135 Block of Emerald
		{(byte) 90, (byte) 59, (byte) 34}, //n°136 Podzol, Spruce Plank
		{(byte) 110, (byte) 73, (byte) 41}, //n°137 Podzol, Spruce Plank
		{(byte) 127, (byte) 85, (byte) 48}, //n°138 Podzol, Spruce Plank
		{(byte) 67, (byte) 44, (byte) 25}, //n°139 Podzol, Spruce Plank
		{(byte) 79, (byte) 1, (byte) 0}, //n°140 Netherrack, Quartz Ore, Nether Wart, Nether Brick Items
		{(byte) 96, (byte) 1, (byte) 0}, //n°141 Netherrack, Quartz Ore, Nether Wart, Nether Brick Items
		{(byte) 112, (byte) 2, (byte) 0}, //n°142 Netherrack, Quartz Ore, Nether Wart, Nether Brick Items
		{(byte) 59, (byte) 1, (byte) 0}, //n°143 Netherrack, Quartz Ore, Nether Wart, Nether Brick Items
	};
	
}
