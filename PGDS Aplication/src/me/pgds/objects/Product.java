package me.pgds.objects;

import lombok.Getter;
import me.pgds.objects.managers.Manager;

@Getter
public class Product {

	private String name,
	desc;
	private int id;
	
	public Product(String name, String desc) {
		this.name = name;
		this.desc = desc;
		this.id = Manager.get().getProducts().size();
		Manager.get().getProducts().add(this);
	}
	
	public void delete() {
		
	}
	
}
