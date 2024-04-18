package me.pgds.utils.frames.main;



import java.awt.event.KeyEvent;

import me.pgds.objects.managers.Manager;
import me.pgds.utils.WindowCore;

public class KeyListener implements java.awt.event.KeyListener {

	private long wait_time;
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if (System.currentTimeMillis()-wait_time >= 10) {
			wait_time = System.currentTimeMillis();
		} else return;
		int id = WindowCore.getFrame().getSelected().getId();
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			WindowCore.getFrame().clear();
			Frame frame = Manager.get().getFrame(id+1);
			if (frame == null) frame = Manager.get().getFrame(0);
			frame.run();
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			WindowCore.getFrame().clear();
			Frame frame = Manager.get().getFrame(id-1);
			if (frame == null) frame = Manager.get().getFrame(WindowCore.getFrame().getButtons().size()-1);
			if (frame!=null) frame.run();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

}
