package me.pgds.objects;

import lombok.Getter;
import lombok.Setter;
import me.pgds.objects.managers.Manager;

@Getter
@Setter
public class Entry {

	private String data, desc, pagamento;
	private int quantidade;
	private double valor,valorUn;
	private Product product;
	private Client client;
	
	public Entry(String data,String desc,String pagamento,double valor) {
		this.data = data;
		this.desc = desc;
		this.pagamento = pagamento;
		this.valor = valor;
		Manager.get().getEntrys().add(this);
	}
	
}
