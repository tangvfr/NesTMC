package fr.tangv.nestmc.test.color;

import org.bukkit.map.MapPalette;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;

public class PalettePAL {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
		FileOutputStream out = new FileOutputStream("./PalettePALminecraft1.8.pal");
		Color color;
		for (int i = 0; i <= 143; i++) {
			if (i >= 4) {
				color = MapPalette.getColor((byte) i);
				out.write(color.getRed());
				out.write(color.getGreen());
				out.write(color.getBlue());
			} else {
				out.write(0);
				out.write(0);
				out.write(0);
			}
		}
		out.close();
	}
	
}
