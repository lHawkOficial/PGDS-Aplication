package me.pgds.utils.frames.main;

import lombok.Getter;
import me.pgds.utils.WindowCore;

@Getter
public class Frame implements FrameImplements {

	private Runnable runnable;
	
	public Frame(Runnable runnable) {
		this.runnable = runnable;
	}
	
	@Override
	public Frame run() {
		new Thread(()-> {
			WindowCore core = WindowCore.getFrame();
			core.clear();
			core.defaultIcons();
			runnable.run();
			core.update();
		}).run();
		return this;
	}
	
}
