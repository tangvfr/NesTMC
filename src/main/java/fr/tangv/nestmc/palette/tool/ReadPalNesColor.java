package fr.tangv.nestmc.palette.tool;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author tangv
 * Permet de convertir un fichier pal (palette de couleur NES) vers un tableau
 */
public class ReadPalNesColor {

	//nombre de couleur dans un fichier PAL, nombre de couleur de la NES
	private static final int NUMBER_COLOR = 64;
	
	//Permet de convertir un fichier pal vers un tableau
	public static void main(String[] args) throws IOException {
		InputStream in = ClassLoader.getSystemResourceAsStream("color.pal");
		int size = ReadPalNesColor.NUMBER_COLOR * 3;//taille théorique du fichier
		
		if (in == null || in.available() != size) {
			throw new IllegalArgumentException("Fichier pal invalide");
		} else {//fichier a la bonne taille
			System.out.println("byte[][] color = new byte[][] {");
			for (byte ic = 0; ic < NUMBER_COLOR; ic++) {//parcours des 64 couleur
				System.out.printf(
						"\t{(byte) 0x%s, (byte) 0x%s, (byte) 0x%s}, //0x%s\n",
						ReadPalNesColor.readByteHex(in),
						ReadPalNesColor.readByteHex(in),
						ReadPalNesColor.readByteHex(in),
						ReadPalNesColor.byteToHex2Char(ic)
						);
			}
			System.out.println("};");
		}
	}
	
	/**
	 * Méthode pour lire un byte dans un flux de donées et emmetre une erreur si ce n'est pas possible
	 * @param in flux dans lequel est lu l'octect
	 * @return valeur hexadécimal de l'otect lue sur deux caratère hexa
	 * @throws IOException si la lecture de l'octect n'est pas posssible
	 */
	public static String readByteHex(InputStream in) throws IOException {
		return ReadPalNesColor.byteToHex2Char(ReadPalNesColor.readByte(in));
	}
	
	/**
	 * Méthode qui poermet de convertir un octect vers une string de 2 char
	 * @param d octect converti
	 * @return valeur hexadécimal de l'otect
	 */
	public static String byteToHex2Char(byte d) {
		int b = Byte.toUnsignedInt(d);
		
		//convertion vers hexa a deux caratère
		String hex = "";
		char tmp;
		for (int i = 0; i < 2; i++) {//repete 2 fois
			tmp = (char) (b % 16);
			if (tmp >= 10) {
				tmp += 55;//65 - 10 = A ASCII
			} else {
				tmp += 48;//48 = 0 ASCII
			}
			
			hex = tmp + hex;
			b /= 16;//decal de 16
		}
		
		return hex;
	}
	
	/**
	 * Méthode pour lire un byte dans un flux de donées et emmetre une erreur si ce n'est pas possible
	 * @param in flux dans lequel est lu l'octect
	 * @return octect lue
	 * @throws IOException si la lecture de l'octect n'est pas posssible
	 */
	public static byte readByte(InputStream in) throws IOException {
		int b = in.read();
		if (b == -1) {
			throw new IOException("Stream is finish");
		}
		
		return (byte) b;
	}
	
}
