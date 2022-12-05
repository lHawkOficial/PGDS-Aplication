package me.pgds.objects;

import java.util.ArrayList;

import javax.swing.JComponent;

import lombok.Getter;

@Getter
public class List {

	private java.util.List<JComponent> objects = new ArrayList<>();
	private int locX,locY;
	
	public List(int locX, int locY, JComponent... components) {
		this.locX = locX;
		this.locY = locY;
		for(JComponent component : components) {
			add(component);
		}
	}
	
	public List(int locX, int locY) {
		this.locX = locX;
		this.locY = locY;
	}
	
	public void add(JComponent component) {
		objects.add(component);
	}
	
	public List build() {
		if (!objects.isEmpty()) {
			int y = 0;
			for (int i = 0; i < objects.size(); i++) {
				JComponent component = objects.get(i);
				component.setLocation(locX, y==0 ? locY:locY+y+i);
				y+=component.getSize().getHeight();
			}
		}
		return this;
	}
	
}
