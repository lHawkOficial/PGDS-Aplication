package me.pgds;

import lombok.Getter;

import me.pgds.utils.WindowCore;

public class Core {
	
	@Getter private static WindowCore window;
	
	public static void main(String[] args) {
		window = new WindowCore().init();
	}
	
}
