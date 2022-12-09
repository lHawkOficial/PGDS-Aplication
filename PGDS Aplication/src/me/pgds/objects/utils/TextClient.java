package me.pgds.objects.utils;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComponent;

import lombok.Getter;
import me.pgds.objects.Client;
import me.pgds.objects.utils.frame.SelectClient;

@Getter
public class TextClient {
	
	private Text title,text;
	private Button select;
	private java.util.List<JComponent> components = new ArrayList<>();
	private Client selected;
	private SelectClient selectproduct;
	
	public TextClient(int x, int y, Text title, Text text, Button select) {
		this.title = title;
		this.text = text;
		this.select = select;
	
		List list = new List(0, x, y);
		if (title != null) {
			list.add(title);
			components.add(title);
		}
		if (text != null) {
			list.add(text);
			components.add(text);
		}
		if (select != null) {
			list.add(select);
			components.add(select);
			TextClient tp = this;
			select.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					selectproduct = new SelectClient(tp);
				}
			});
		}
		list.build();
		updateSelected();
		
	}
	
	public void updateSelected() {
		if (selectproduct != null && selectproduct.getSelected() != null) {
			selected = selectproduct.getSelected();
		}
		text.setText(selected == null ? "N.A" : selected.getName());
	}
	
	public void remove() {
		if (title != null) title.remove();
		if (text != null) text.remove();
		if (select != null) select.remove();
	}
	
}
