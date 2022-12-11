package me.pgds.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import me.pgds.objects.Entry;

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
	
	public static String randomString() {
		String txt = new String();
		while(true) {
			if (txt.length() >= 10) break;
			loop: {
				for (char l = 'a'; l < 'z'; l++) {
					int chance = new Random().nextInt(100);
					if (chance <= 1) {
						txt += new Random().nextBoolean() ? String.valueOf(l).toUpperCase() : String.valueOf(l).toLowerCase();
						break loop;
					}
				}
			}
		}
		return txt;
	}
	
	public static void initialize() {
		
		WindowCore core = WindowCore.getFrame();
		File folder = new File(core.getFolder() + "/entrys");
		if (folder.exists()) {
			for(File fd : folder.listFiles()) {
				if (!fd.isDirectory()) continue;
				for(File file : fd.listFiles()) {
					if (file.getName().endsWith(".yml")) {
						Entry.load(file);
					}
				}
			}
		}
		
	}
	
}
