package me.pgds.utils.frames;



import java.awt.Color;

import lombok.Getter;
import me.pgds.objects.utils.Text;
import me.pgds.utils.frames.main.Frame;

@Getter
public class MainFrame extends Frame {

	public MainFrame(int id) {
		super(()->{
			
			new Text("PÁGINA EM DESENVOLVIMENTO", Color.red.darker().darker().darker(), Color.white, 1000, 720, 209, 0, 15, false, false).setOpaque(false);
			
		}, id);
	}

}
