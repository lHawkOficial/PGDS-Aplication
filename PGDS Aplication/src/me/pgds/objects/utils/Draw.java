package me.pgds.objects.utils;

import java.awt.Color;

import me.pgds.utils.WindowCore;

public class Draw {

	public Draw(int locX, int locY, int sizeX, int sizeY, Color color) {
		WindowCore core = WindowCore.getFrame();
		new Text(null, color, color, sizeX, sizeY, locX, locY, 0, false, false);
		core.update();
	}
	
}
