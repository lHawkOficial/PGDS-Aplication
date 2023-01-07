package me.pgds.objects.utils;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

import lombok.Getter;
import lombok.Setter;
import me.pgds.imgs.ImageAPI;
import me.pgds.utils.API;
import me.pgds.utils.RowCreator;
import me.pgds.utils.WindowCore;

@Getter
public class ListComp {	

	private RowCreator rowcreator;
	private Runnable runnable;
	private List<List<JComponent>> comps = new ArrayList<>();
	private int locX, locY, maxObjects, size;
	private Text text, empty, rows;
	private Button left,right;
	private int pagina = 1;
	private boolean created = false;
	private JFrame frame = WindowCore.getFrame();
	@Setter
	private Boolean showRow = true;
	
	public ListComp(int locX, int locY, int size, int maxObjects, Text text, Text empty) {
		this.text = text;
		this.empty = empty;
		this.locX = locX;
		this.locY = locY;
		this.size = size;
		this.maxObjects = maxObjects;
		this.right = new Button(null, Color.black, Color.black, text.getHeight(), text.getHeight(), 0, 0, 17);
		this.left = new Button(null, Color.black, Color.black, text.getHeight(), text.getHeight(), 0, 0, 17);
	}
	
	public ListComp(int locX, int locY, int size, int maxObjects, Text text, Text empty, JFrame frame) {
		this.text = text;
		this.empty = empty;
		this.locX = locX;
		this.locY = locY;
		this.size = size;
		this.maxObjects = maxObjects;
		this.right = new Button(null, Color.black, Color.black, text.getHeight(), text.getHeight(), 0, 0, 17, frame);
		this.left = new Button(null, Color.black, Color.black, text.getHeight(), text.getHeight(), 0, 0, 17, frame);
		this.frame = frame;
	}
	
	public void add(List<JComponent> componentsRow) {
		comps.add(componentsRow);
	}
	
	public ListComp complete() {
		rowcreator = new RowCreator(new ArrayList<>(comps), maxObjects);
		pagina = rowcreator.getPaginaAtual();
		for(List<JComponent> components : comps) {
			for(JComponent component : components) {
				component.setVisible(false);
			}
		}
		right.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!rowcreator.containsPagina(rowcreator.getPaginaAtual()+1)) return;
				rowcreator.setPaginaAtual(rowcreator.getPaginaAtual()+1);
				build();
				updateRow();
			}
		});
		left.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!rowcreator.containsPagina(rowcreator.getPaginaAtual()-1)) return;
				rowcreator.setPaginaAtual(rowcreator.getPaginaAtual()-1);
				build();
				updateRow();
			}
		});
		right.setOpaque(false);
		left.setOpaque(false);
		right.setIcon(new ImageIcon(API.resizeImage(ImageAPI.getUtils("row_right.png"), right.getWidth(), right.getHeight())));
		left.setIcon(new ImageIcon(API.resizeImage(ImageAPI.getUtils("row_left.png"), left.getWidth(), left.getHeight())));
		updateRow();
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public ListComp build() {
		int locX = this.locX;
		int locY = this.locY;
		pagina = rowcreator.getPaginaAtual();
		text.setLocation(locX, locY);
		locY+=text.getHeight()+2;
		empty.setVisible(false);
		right.setVisible(false);
		left.setVisible(false);
		for(List<JComponent> components : comps) {
			for(JComponent component : components) {
				component.setVisible(false);
			}
		}
		if (!rowcreator.getObjects().isEmpty()) {
			List<Object> objects = rowcreator.getPagina(pagina);
			if (objects.isEmpty()) {
				empty.setVisible(true);
				empty.setLocation(locX, locY);
				return this;
			}
			int y = 0;
			int lastX = 0;
			for (int i = 0; i < objects.size(); i++) {
				List<JComponent> comps = (List<JComponent>) objects.get(i);
				if (comps.isEmpty()) continue;
				int yH = 0;
				int xL = locX;
				for (int j = 0; j < comps.size(); j++) {
					JComponent component = comps.get(j);
					if (component.getHeight() >= yH) yH = component.getHeight();
					component.setLocation(xL, y==0 ? locY : locY+y+(i*size));
					component.setVisible(true);
					xL += component.getWidth();
				}
				lastX = xL;
				y+=yH;
			}
			if (rowcreator.containsPagina(pagina+1)) {
				right.setVisible(true);
				right.setLocation(lastX-right.getWidth(), (y==0 ? locY : locY+y+(objects.size()*size))+2);
			}
			if (rowcreator.containsPagina(pagina-1)) {
				left.setVisible(true);
				left.setLocation(locX, (y==0 ? locY : locY+y+(objects.size()*size))+2);
			}
		}else {
			empty.setVisible(true);
			empty.setLocation(locX, locY);
		}
		updateRow();
		return this;
	}
	
	private void updateRow() {
		if (!showRow) return;
		if (rows == null) {
			rows = new Text(null, new Color(64,64,64), Color.gray, text.getWidth(), 25, 0, 0, 15, false, true, frame);
		}
		rows.setText("Página " + rowcreator.getPaginaAtual() + "/" + rowcreator.getTotalPaginas() + " - " + rowcreator.getObjects().size() + " Item(s)");
		rows.setLocation((int)text.getLocation().getX(), (int)text.getLocation().getY() - text.getHeight()+5);
	}
	
}
