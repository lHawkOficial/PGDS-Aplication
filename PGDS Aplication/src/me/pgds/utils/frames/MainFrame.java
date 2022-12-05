package me.pgds.utils.frames;

import lombok.Getter;
import me.pgds.utils.frames.main.Frame;

@Getter
public class MainFrame extends Frame {

	public MainFrame(Runnable runnable, int id) {
		super(runnable, id);
	}
	

}
