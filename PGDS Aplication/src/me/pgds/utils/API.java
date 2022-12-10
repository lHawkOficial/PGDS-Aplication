package me.pgds.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class API {

	public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
	    try {
	    	Image image = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
	    	BufferedImage img = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
	    	Graphics2D g = img.createGraphics();
	    	g.drawImage(image, 0, 0, null);
	    	g.dispose();
	    	return img;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getData() {
		return new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
	}
	
	public static boolean isInteger(String txt) {
		try {
			Integer.valueOf(txt.replace(",", "."));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isDouble(String txt) {
		try {
			Double.valueOf(txt.replace(",", "."));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
