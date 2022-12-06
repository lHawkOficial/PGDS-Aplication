package me.pgds.objects.managers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.Getter;
import me.pgds.objects.Product;
import me.pgds.utils.WindowCore;
import me.pgds.utils.frames.main.Frame;

@Getter
public class Manager {

	private List<Frame> frames = new ArrayList<>();
	private List<Product> products = new ArrayList<>();
	
	public Frame getFrame(int id) {
		Iterator<Frame> it = frames.iterator();
		while(it.hasNext()) {
			Frame frame = it.next();
			if (frame.getId() == id) return frame;
		}
		return null;
	}
	
	public Product getProduct(int id) {
		Iterator<Product> it = products.iterator();
		while(it.hasNext()) {
			Product product = it.next();
			if (product.getId() == id) return product;
		}
		return null;
	}
	
	public static Manager get() {
		return WindowCore.getFrame().getManager();
	}
	
}
