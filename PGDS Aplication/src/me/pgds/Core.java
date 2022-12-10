package me.pgds;

import me.pgds.objects.Client;
import me.pgds.objects.Product;
import me.pgds.utils.WindowCore;

public class Core {
	
	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		System.out.println("Iniciando o aplicativo...");
		new WindowCore().init();
		System.out.println("Aplicativo iniciado em " + (System.currentTimeMillis()-time) + "ms!");
		
		/*
		new Product("bloco", "14x19x39");
		new Product("bloco", "9x19x39");
		new Product("canaleta", "14x19x39");
		new Product("canaleta", "9x19x39");
		
		new Client("luan victor mariano chagas", "(62) 985305035", new String());
		new Client("lujam rodrigues mariano chagas", new String(), new String());
		new Client("fernanda mariano chagas", new String(), new String());
		new Client("francisco lira chagas", new String(), new String());
		new Client("enel engenharia e fornecedora de energia renovavel", new String(), new String());
		*/
		
		/*
		for (int i = 0; i < 35; i++) {
			new Product("produto"+i, "desc"+i);
		}
		
		for (int i = 0; i < 5000; i++) {
			new Client("cliente nº " + i, new String(), new String());
		}
		*/
		
	}
	
}
