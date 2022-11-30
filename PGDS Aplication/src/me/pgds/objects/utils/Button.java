package me.pgds.objects.utils;

import java.awt.Color;

import java.awt.Font;

import javax.swing.JButton;

import lombok.Getter;
import me.pgds.utils.WindowCore;
import me.pgds.utils.frames.animations.SelectButton;

@Getter
public class Button extends JButton {

	private static final long serialVersionUID = 1L;

	public Button(String text, Color background, Color foreground, int sizeX, int sizeY, int locX, int locY) {
		setBounds(locX, locY, sizeX, sizeY);
		setFont(new Font("Arial", 0, 15));
		setText(text);
		setForeground(foreground);
		setBackground(background);
		setBorderPainted(false);
		setAutoscrolls(false);
		setVisible(true);
		setHideActionText(true);
		WindowCore core = WindowCore.getFrame();
		core.add(this);
		core.update();
		addMouseListener(new SelectButton(this));
	}
	
}
