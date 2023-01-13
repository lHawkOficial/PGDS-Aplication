package me.pgds.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import me.pgds.objects.Client;
import me.pgds.objects.Entry;
import me.pgds.objects.Exit;
import me.pgds.objects.Product;

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
	
	public static double formatValue(double valor) {
		NumberFormat formatter = new DecimalFormat("0.00");
		return Double.valueOf(formatter.format(valor).replace(",", "."));
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
		
		File folder = new File(core.getFolder() + "/clients");
		if (folder.exists()) {
			for(File file : folder.listFiles()) {
				if (!file.getName().endsWith(".yml")) continue;
				Client.load(file);
			}
		}
		
		folder = new File(core.getFolder() + "/produts");
		if (folder.exists()) {
			for(File file : folder.listFiles()) {
				if (!file.getName().endsWith(".yml")) continue;
				Product.load(file);
			}
		}
		
		folder = new File(core.getFolder() + "/entrys");
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
		
		folder = new File(core.getFolder() + "/exits");
		if (folder.exists()) {
			for(File fd : folder.listFiles()) {
				if (!fd.isDirectory()) continue;
				for(File file : fd.listFiles()) {
					if (file.getName().endsWith(".yml")) {
						Exit.load(file);
					}
				}
			}
		}
		
	}
	
	public static void relatoryEntrys() {
		WindowCore core = WindowCore.getFrame();
		File folder = new File(core.getFolder() + "/relatorys");
		File file = new File(folder + "/entrada_"+randomString() + ".yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void relatoryExits() {
		WindowCore core = WindowCore.getFrame();
		File folder = new File(core.getFolder() + "/relatorys");
		File file = new File(folder + "/saida_"+randomString() + ".yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void relatoryAll() {
		WindowCore core = WindowCore.getFrame();
		File folder = new File(core.getFolder() + "/relatorys");
		File file = new File(folder + "/todos_"+randomString() + ".yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
