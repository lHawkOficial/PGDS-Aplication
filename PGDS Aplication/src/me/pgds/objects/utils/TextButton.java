package me.pgds.objects.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComponent;

import lombok.Getter;

@Getter
public class TextButton {
	
	private Text title,text;
	private Button confirm;
	private java.util.List<JComponent> components = new ArrayList<>();
	
	public TextButton(int x, int y, Text title, Text text, Button confirm, Runnable confirmAction) {
		this.title = title;
		this.text = text;
		this.confirm = confirm;
		
		List list = new List(0, x, y);
		if (title != null) {
			list.add(title);
			components.add(title);
		}
		if (text != null) {
			list.add(text);
			components.add(text);
		}
		if (confirm != null) {
			list.add(confirm);
			components.add(confirm);
			if (confirmAction != null) {
				confirm.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						confirmAction.run();
					}
				});
			}
		}
		list.build();
		
	}
	
	public void remove() {
		if (title != null) title.remove();
		if (text != null) text.remove();
		if (confirm != null) confirm.remove();
	}
	
}
