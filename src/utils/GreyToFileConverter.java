package utils;

import java.awt.Color;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;

public class GreyToFileConverter {
	
	//Main de test
	public static void main(String[] args){
		greyToFile("map3",5);
	}

	public static void greyToFile(String nom,int divider){
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("ressources/env/maps/"+nom+".png"));
		} catch (IOException e) {
			System.out.println("Fichier carte non trouvé, celle-ci doit être au format png.");
			e.printStackTrace();
			System.exit(1);
		}
		
		// Vérification conformité carte
		if(img.getWidth() != img.getHeight()){
			System.out.println("Erreur : La carte n'est pas carré.\nFin exécution.");
			System.exit(1);
		}

		if(!((img.getWidth() & (img.getWidth() - 1)) == 0)){
			System.out.println("Erreur : La carte ne possède pas une taille qui est une puissance de deux.\nFin exécution.");
			System.exit(1);
		}
		
		int[][] pixels = new int[img.getWidth()][img.getHeight()];
		////////////////////////////////////

		
		for( int i = 0; i < img.getWidth(); i++ )
		    for( int j = 0; j < img.getHeight(); j++ )
		        pixels[i][j] =  (255 - new Color(img.getRGB( i, j )).getRed()) / divider;
		
		try{
		    PrintWriter writer = new PrintWriter("ressources/env/maps/"+nom+".txt", "UTF-8");
		    writer.println(img.getHeight());
			for( int i = 0; i < img.getWidth(); i++ ){
				writer.print(pixels[i][0]);
			    for( int j = 1; j < img.getHeight(); j++ ){
					writer.print(':');
					writer.print(pixels[i][j]);
			    }
			    writer.println();
		    }

		    writer.close();
		} catch (IOException e) {
			System.out.println("Impossible de créer le fichier");
			e.printStackTrace();
			System.exit(1);
		}
		

	}
	
}
