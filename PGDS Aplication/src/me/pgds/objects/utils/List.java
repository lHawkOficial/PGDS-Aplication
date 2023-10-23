package me.pgds.objects.utils;

import java.util.ArrayList;

import javax.swing.JComponent;

import lombok.Getter;

@Getter
public class List {

	private java.util.List<JComponent> objects = new ArrayList<>();
	private int locX,locY, size;
	
	public List(int size, int locX, int locY, JComponent... components) {
		this.locX = locX;
		this.locY = locY;
		this.size = size;
		for(JComponent component : components) {
			add(component);
		}
	}
	
	public List(int size, int locX, int locY) {
		this.locX = locX;
		this.locY = locY;
		this.size = size;
	}
	
	public void add(JComponent component) {
		objects.add(component);
	}
	
	public void addAll(java.util.List<JComponent> component) {
		objects.addAll(component);
	}
	
	public List build() {
		if (!objects.isEmpty()) {
			int y = 0;
			for (int i = 0; i < objects.size(); i++) {
				JComponent component = objects.get(i);
				component.setLocation(locX, y==0 ? locY : locY+y+(i*size));
				y+=component.getSize().getHeight();
			}
		}
		return this;
	}
	
}
