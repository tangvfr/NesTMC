package fr.tangv.nestmc.palette.tool;

import org.bukkit.map.MapPalette;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Tangv - https://tangv.fr
 * Permet de trouvé les couleurs de map qui corespondes au mieux a celles de la palette d'un fichier pal
 * Classe de teste
 */
public class MatchPalNesToPalMap {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		File pal = new File("test.pal");
		if (!pal.exists())
			pal.createNewFile();
		
		FileOutputStream out = new FileOutputStream(pal);
		InputStream in = ClassLoader.getSystemResourceAsStream("choix.pal");
		
		if (in == null || in.available() != 192) {
			throw new IOException("Fichier pal invalide");
		} else {
			for (byte i = 0; i < 64; i++) {//pour tout les couleur de la palette de la nes
				//icm indices de coleur de la map
				@SuppressWarnings("deprecation")
				byte icm = MapPalette.matchColor(
						in.read(), 
						in.read(),
						in.read()
						);
				
				System.out.printf(
						"0x%s = %d\n", 
						ReadPalNesColor.byteToHex2Char(i),
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
			}
			
			in.close();
			out.close();
		}
	}
	
}
