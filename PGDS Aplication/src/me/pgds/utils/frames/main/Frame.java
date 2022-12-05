package me.pgds.utils.frames.main;

import lombok.Getter;
import me.pgds.objects.managers.Manager;
import me.pgds.objects.utils.Button;
import me.pgds.utils.WindowCore;

@Getter
public class Frame implements FrameImplements {

	private Runnable runnable;
	private Button buttonSelected;
	private int id;
	
	public Frame(Runnable runnable, int buttonSelected) {
		this.runnable = runnable;
		this.id = buttonSelected;
		Manager.get().getFrames().add(this);
	}
	
	@Override
	public Frame run() {
		WindowCore core = WindowCore.getFrame();
		core.setSelected(this);
		new Thread(()-> {
			if (core.getTimer() != null) {
				core.getTimer().cancel();
				core.setTimer(null);
			}
			core.clear();
			core.defaultIcons();
			runnable.run();
			if (id >= 0 && id < core.getButtons().size()) {
				buttonSelected = core.getButtons().get(id);
				buttonSelected.select();
			}
			core.update();
		}).run();
		return this;
	}
	
}
