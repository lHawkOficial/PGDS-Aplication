package me.pgds.objects;

import lombok.Getter;

import lombok.Setter;
import me.pgds.objects.managers.Manager;

@Getter
@Setter
public class Exit {

	private String data, desc, pagamento;
	private int quantidade;
	private double valor,valorUn;
	private Product product;
	private Client client;
	
	public Exit(String data,String desc,String pagamento,double valor) {
		this.data = data;
		this.desc = desc;
		this.pagamento = pagamento;
		this.valor = valor;
		Manager.get().getExits().add(this);
	}
	
}
