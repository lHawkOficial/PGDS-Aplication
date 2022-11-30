package me.pgds;

import java.awt.Color;

import me.pgds.objects.utils.Button;
import me.pgds.utils.WindowCore;
import me.pgds.utils.frames.MainFrame;

public class Core {
	
	public static void main(String[] args) {
		new WindowCore().init();
		new MainFrame(()->{
			int size = 0;
			for (int i = 0; i < 27; i++) {
				Button button = new Button("Botão " + i, Color.YELLOW.darker().darker(), Color.white, 200, 25, 2, 2+size+i);
				size+=button.getHeight();
			}
		}).run();
	}
	
}
