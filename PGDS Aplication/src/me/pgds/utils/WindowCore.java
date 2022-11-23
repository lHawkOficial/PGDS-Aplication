package me.pgds.utils;

import java.awt.Color;

import javax.swing.JFrame;

import lombok.Getter;
import me.pgds.Core;
import me.pgds.imgs.ImageAPI;

@Getter
public class WindowCore extends JFrame {
	
	private static final long serialVersionUID = -5140442525033186243L;

	public WindowCore() {
		setResizable(false);
		setSize(1280, 720);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.GRAY.darker());
		setTitle("PEGADAS PRÉ-MOLDADOS");
		setIconImage(ImageAPI.get("icon_core.png"));
	}
	
	public WindowCore init() {
		setVisible(true);
		update(getGraphics());
		return this;
	}
	
	public void clear() {
		
	}
	
	public static WindowCore get() {
		return Core.getWindow();
	}
			
}
