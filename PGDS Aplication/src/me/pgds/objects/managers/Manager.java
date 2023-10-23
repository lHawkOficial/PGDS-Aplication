package me.pgds.objects.managers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import me.pgds.objects.Client;
import me.pgds.objects.Entry;
import me.pgds.objects.Exit;
import me.pgds.objects.Product;
import me.pgds.utils.WindowCore;
import me.pgds.utils.frames.main.Frame;

@Getter
@Setter
public class Manager {

	private List<Frame> frames = new ArrayList<>();
	private List<Product> products = new ArrayList<>();
	private List<Client> clients = new ArrayList<>();
	private List<Entry> entrys = new ArrayList<>();
	private List<Exit> exits = new ArrayList<>();
	
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
	
	public Client getClient(int id) {
		Iterator<Client> it = clients.iterator();
		while(it.hasNext()) {
			Client client = it.next();
			if (client.getId() == id) return client;
		}
		return null;
	}
	
	public Client getClient(String name) {
		Iterator<Client> it = clients.iterator();
		while(it.hasNext()) {
			Client client = it.next();
			if (client.getName().equalsIgnoreCase(name)) return client;
		}
		return null;
	}
	
	public Client getClientNumber(String number) {
		Iterator<Client> it = clients.iterator();
		while(it.hasNext()) {
			Client client = it.next();
			if (client.getNumero1().equalsIgnoreCase(number) || client.getNumero2().equalsIgnoreCase(number)) return client;
		}
		return null;
	}
	
	public Client getClientCpf(String cpf) {
		Iterator<Client> it = clients.iterator();
		while(it.hasNext()) {
			Client client = it.next();
			if (client.getCpf().equalsIgnoreCase(cpf)) return client;
		}
		return null;
	}
	
	public static Manager get() {
		return WindowCore.getFrame().getManager();
	}
	
}
