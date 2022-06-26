package fr.tangv.nestmc.test.color;

import java.awt.Color;

import org.bukkit.map.MapPalette;

public class PaletteGIMP {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		System.out.println("GIMP Palette\r\n"
				+ "Name: Minecraft\r\n"
				+ "Columns: 12\n"
				+ "# Palette qui corseponde aux couleurs des maps minecraft"
				);
		Color color;
		for (int i = 0; i <= 143; i++) {
			if (i >= 4) {
				color = MapPalette.getColor((byte) i);
				System.out.printf("%d %d %d #n°%d\n", color.getRed(), color.getGreen(), color.getBlue(), i);
			} else {
				System.out.println("0 0 0 #translucence n°"+i);
			}
		}
	}

}
