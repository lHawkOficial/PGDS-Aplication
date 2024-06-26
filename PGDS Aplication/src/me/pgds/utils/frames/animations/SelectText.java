package me.pgds.utils.frames.animations;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

import lombok.Getter;

@Getter
public class SelectText implements MouseListener {
	
	private JComponent component;
	
	private Dimension size;
	private Color background, foreground;
	private Font font;
	
	public SelectText(JComponent component) {
		this.component = component;
		this.background = component.getBackground();
		this.foreground = component.getForeground();
		this.size = component.getSize();
		this.font = component.getFont();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		component.setBackground(component.getBackground().darker());
		component.setForeground(component.getForeground().brighter());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		component.setForeground(foreground);
		component.setBackground(background);
	}

}
