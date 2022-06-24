package fr.tangv.nestmc.palette.tool;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.bukkit.map.MapPalette;

/**
 * @author tangv
 * Permet de trouvé les couleurs de map qui corespondes au mieux a celles de la palette nes
 * Classe de teste
 */
public class MatchNesColorAndMapColor {

	public static void main(String[] args) throws IOException {
		File pal = new File("test.pal");
		if (!pal.exists())
			pal.createNewFile();
		
		FileOutputStream out = new FileOutputStream(pal);
		
		byte ip = 0;//indices de la palette nes
		for (byte[] rgb : NesPalette.NES_PALETTE) {//pour tout les couleur de la palette de la nes
			//icm indices de coleur de la map
			@SuppressWarnings("deprecation")
			byte icm = MapPalette.matchColor(
					Byte.toUnsignedInt(rgb[0]),
					Byte.toUnsignedInt(rgb[1]),
					Byte.toUnsignedInt(rgb[2])
					);
			
			System.out.printf(
					"0x%s = %d\n", 
					ReadPalNesColor.byteToHex2Char(ip),
					Byte.toUnsignedInt(icm)
					);
			
			//export vers le pal
			@SuppressWarnings("deprecation")
			Color c = MapPalette.getColor(icm);
			out.write(new byte[] {
					(byte) c.getRed(),
					(byte) c.getGreen(),
					(byte) c.getBlue(),
			});
			
			ip++;
		}
		
		out.close();
	}

}
