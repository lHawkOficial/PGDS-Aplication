package me.pgds.imgs;

import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class ImageAPI {
	
	public static BufferedImage get(String name) {
		InputStream is = ImageAPI.class.getResourceAsStream("icons/"+name);
		BufferedImage img;
		try {
			img = ImageIO.read(is);
			return img;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
