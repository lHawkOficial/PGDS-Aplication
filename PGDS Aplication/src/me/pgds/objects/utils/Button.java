package me.pgds.objects.utils;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import lombok.Getter;
import me.pgds.utils.WindowCore;
import me.pgds.utils.frames.animations.SelectButton;

@Getter
public class Button extends JButton {

	private static final long serialVersionUID = 940968903697459643L;
	private Boolean selected = false;
	
	public Button(String text, Color background, Color foreground, int sizeX, int sizeY, int locX, int locY, int sizeFont) {
		presets(text, background, foreground, sizeX, sizeY, locX, locY, sizeFont,WindowCore.getFrame());
	}
	
	public Button(String text, Color background, Color foreground, int sizeX, int sizeY, int locX, int locY, int sizeFont, ActionListener action) {
		presets(text, background, foreground, sizeX, sizeY, locX, locY, sizeFont,WindowCore.getFrame());
		addActionListener(action);
	}
	
	public Button(String text, Color background, Color foreground, int sizeX, int sizeY, int locX, int locY, int sizeFont, JFrame frame) {
		presets(text, background, foreground, sizeX, sizeY, locX, locY, sizeFont,frame);
	}
	
	public Button(String text, Color background, Color foreground, int sizeX, int sizeY, int locX, int locY, int sizeFont, ActionListener action, JFrame frame) {
		presets(text, background, foreground, sizeX, sizeY, locX, locY, sizeFont,frame);
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
	
	private void presets(String text, Color background, Color foreground, int sizeX, int sizeY, int locX, int locY, int sizeFont,JFrame frame) {
		frame.add(this);
		setBounds(locX, locY, sizeX, sizeY);
		setFont(new Font("Arial", 0, sizeFont));
		setText(text);
		if (foreground != null) setForeground(foreground);
		if (background != null) setBackground(background);
		setBorderPainted(false);
		setAutoscrolls(false);
		setHideActionText(true);
		setVisible(true);
		addMouseListener(new SelectButton(this));
	}
	
}
