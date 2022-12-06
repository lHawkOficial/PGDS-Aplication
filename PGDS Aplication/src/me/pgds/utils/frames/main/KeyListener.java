package me.pgds.utils.frames.main;

import java.awt.event.KeyEvent;

import me.pgds.objects.managers.Manager;
import me.pgds.utils.WindowCore;

public class KeyListener implements java.awt.event.KeyListener {
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int id = WindowCore.getFrame().getSelected().getId();
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			Frame frame = Manager.get().getFrame(id+1);
			if (frame == null) frame = Manager.get().getFrame(0);
			frame.run();
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			Frame frame = Manager.get().getFrame(id-1);
			if (frame == null) frame = Manager.get().getFrame(WindowCore.getFrame().getButtons().size()-1);
			if (frame!=null) frame.run();
		}
	}

}
