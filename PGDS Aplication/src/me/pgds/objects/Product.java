package me.pgds.objects;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import me.pgds.objects.managers.Manager;
import me.pgds.utils.API;
import me.pgds.utils.Save;
import me.pgds.utils.WindowCore;

@Getter
public class Product {

	private String name,
	desc;
	private int id;
	@Setter
	private File file;
	
	public Product(String name, String desc, boolean createfile) {
		this.name = name;
		this.desc = desc;
		this.id = Manager.get().getProducts().size();
		this.file = new File(WindowCore.getFrame().getFolderProducts() + "/"+API.randomString()+".yml");
		if (createfile) {
			if (!file.exists()) {
				try {
					file.createNewFile();
					save();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		Manager.get().getProducts().add(this);
	}
	
	public void save() {
		List<Object> lista = new ArrayList<>();
		lista.add(name);
		lista.add(desc);
		new Save(file, lista);
	}
	
	public static void load(File file) {
		List<Object> lista = Save.load(file);
		if (lista == null) return;
		if (lista.isEmpty()) return;
		Product product = new Product((String)lista.get(0), (String)lista.get(1), false);
		product.setFile(file);
	}
	
	public void delete() {
		Manager.get().getProducts().remove(this);
		file.delete();
	}
	
}
