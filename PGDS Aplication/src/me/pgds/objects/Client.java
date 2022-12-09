package me.pgds.objects;

import lombok.Getter;
import me.pgds.objects.managers.Manager;

@Getter
public class Client {

	private String name;
	private String numero1, numero2;
	private int id;
	
	public Client(String name, String numero1, String numero2) {
		this.name = name;
		this.numero1 = numero1.length() == 0 ? "não tem" : numero1;
		this.numero2 = numero2.length() == 0 ? "não tem" : numero2;
		this.id = Manager.get().getClients().size();
		Manager.get().getClients().add(this);
	}
	
}
