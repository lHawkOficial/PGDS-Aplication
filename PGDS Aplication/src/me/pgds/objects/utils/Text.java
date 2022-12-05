package me.pgds.objects.utils;

import java.awt.Color;


import java.awt.Font;
import javax.swing.JTextPane;

import lombok.Getter;
import me.pgds.utils.WindowCore;
import me.pgds.utils.frames.animations.SelectText;

@Getter
public class Text extends JTextPane {

	private static final long serialVersionUID = 940968903697459643L;
	
	public Text(String text, Color background, Color foreground, int sizeX, int sizeY, int locX, int locY, int sizeFont, boolean editable, boolean animation) {
		WindowCore core = WindowCore.getFrame();
		core.add(this);
		setBounds(locX, locY, sizeX, sizeY);
		setFont(new Font("Arial", 0, sizeFont));
		setText(text);
		setEditable(editable);
		if (foreground != null) setForeground(foreground);
		if (background != null) setBackground(background);
		setAutoscrolls(false);
		setVisible(true);
		core.update();
		if (animation) addMouseListener(new SelectText(this));
	}
	
	public void remove() {
		WindowCore.getFrame().remove(this);
		WindowCore.getFrame().update();
	}
	
}
