package me.pgds.objects.utils;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import lombok.Getter;
import me.pgds.utils.WindowCore;
import me.pgds.utils.frames.animations.SelectButton;

@Getter
public class Button extends JButton {

	private static final long serialVersionUID = 940968903697459643L;
	private Boolean selected = false;
	
	public Button(String text, Color background, Color foreground, int sizeX, int sizeY, int locX, int locY, int sizeFont) {
		presets(text, background, foreground, sizeX, sizeY, locX, locY, sizeFont);
	}
	
	public Button(String text, Color background, Color foreground, int sizeX, int sizeY, int locX, int locY, int sizeFont, ActionListener action) {
		presets(text, background, foreground, sizeX, sizeY, locX, locY, sizeFont);
		addActionListener(action);
	}
	
	public void remove() {
		WindowCore.getFrame().remove(this);
		WindowCore.getFrame().update();
	}
	
	public void select() {
		if (selected) return;
		selected = true;
		for(MouseListener listtener : getMouseListeners()) {
			removeMouseListener(listtener);
		}
		setBackground(getBackground().darker().darker());
		setForeground(getForeground().brighter());
	}
	
	private void presets(String text, Color background, Color foreground, int sizeX, int sizeY, int locX, int locY, int sizeFont) {
		WindowCore core = WindowCore.getFrame();
		core.add(this);
		setBounds(locX, locY, sizeX, sizeY);
		setFont(new Font("Arial", 0, sizeFont));
		setText(text);
		setForeground(foreground);
		setBackground(background);
		setBorderPainted(false);
		setAutoscrolls(false);
		setHideActionText(true);
		setVisible(true);
		addMouseListener(new SelectButton(this));
	}
	
}
