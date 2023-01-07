package me.pgds.objects;

import lombok.Getter;
import me.pgds.objects.managers.Manager;

@Getter
public class Client {

	private String name, cpf, cnpj;
	private String numero1, numero2;
	private int id;
	
	public Client(String name, String numero1, String numero2, String cpf, String cnpj) {
		this.name = name;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.numero1 = numero1.length() == 0 ? "não tem" : numero1;
		this.numero2 = numero2.length() == 0 ? "não tem" : numero2;
		this.id = Manager.get().getClients().size();
		Manager.get().getClients().add(this);
	}
	
	public void delete() {
		
	}
	
}
