package me.pgds.utils.frames.main;

import lombok.Getter;

@Getter
public class Frame implements FrameImplements {

	private Runnable runnable;
	
	public Frame(Runnable runnable) {
		this.runnable = runnable;
	}
	
	@Override
	public void run() {
		new Thread(()->runnable.run()).run();
	}
	
}
