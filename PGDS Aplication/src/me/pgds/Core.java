package me.pgds;

import me.pgds.utils.WindowCore;

public class Core {
	
	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		System.out.println("Iniciando o aplicativo...");
		new WindowCore().init();
		System.out.println("Aplicativo iniciado em " + (System.currentTimeMillis()-time) + "ms!");
	}
	
}
