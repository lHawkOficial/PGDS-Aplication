package me.pgds.utils;

import java.awt.Color;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.JComponent;
import javax.swing.JFrame;

import lombok.Getter;
import lombok.Setter;
import me.pgds.imgs.ImageAPI;
import me.pgds.objects.List;
import me.pgds.objects.Manager;
import me.pgds.objects.utils.Button;
import me.pgds.objects.utils.Draw;
import me.pgds.objects.utils.Text;
import me.pgds.utils.frames.ClientFrame;
import me.pgds.utils.frames.EntryFrame;
import me.pgds.utils.frames.ExitFrame;
import me.pgds.utils.frames.HEntryFrame;
import me.pgds.utils.frames.MainFrame;
import me.pgds.utils.frames.main.Frame;
import me.pgds.utils.frames.main.KeyListener;

@Getter
public class WindowCore extends JFrame {
	
	private static final long serialVersionUID = -5140442525033186243L;
	
	@Getter
	private static WindowCore frame;
	private java.util.List<Button> buttons = new ArrayList<>();
	
	@Setter
	private Timer timer;
	
	private Manager manager;
	
	private Frame main,
	entry, 
	exit,
	client,
	hentry,
	hexit,
	hclient,
	relatory;
	
	@Setter
	private Frame selected;
	
	public WindowCore() {
		frame = this;
		setSize(1280, 720);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(107,107,107));
		setTitle("PEGADAS PRÉ-MOLDADOS");
		setIconImage(ImageAPI.getIcon("icon_core.png"));
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(new KeyListener());
		manager = new Manager();
		main = new MainFrame(()->{
			
			new Text("Texto de teste", Color.black, Color.white, 300, 30, 210, 10, 17, false, true);
			
		}, 0);
		entry = new EntryFrame(()->{
			
		}, 1);
		exit = new ExitFrame(()->{
			
		}, 2);
		client = new ClientFrame(()->{
			
		}, 3);
		hclient = new HEntryFrame(()->{
					
		}, 4);
		hentry = new HEntryFrame(()->{
			
		}, 5);
		hexit = new HEntryFrame(()->{
			
		}, 6);
		relatory = new HEntryFrame(()->{
			
		}, 7);
		main.run();
	}
	
	public WindowCore init() {
		setVisible(true);
		update(getGraphics());
		return this;
	}
	
	public void clear() {
		frame.getContentPane().removeAll();
		update();
	}
	
	public void update() {
		frame.revalidate();
		frame.repaint();
		update(getGraphics());
	}
	
	public void defaultIcons() {
		
		buttons.clear();
		Color color = new Color(145, 118, 0);
		List list = new List(2, 2,new Button("início", color, Color.white, 200, 25, 2, 2, 15, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Button button = (Button) e.getSource();
				if (button.getSelected()) return;
				main.run();
			}
		}),new Button("nova entrada", color, Color.white, 200, 25, 2, 2, 15, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Button button = (Button) e.getSource();
				if (button.getSelected()) return;
				entry.run();
			}
		}),new Button("nova saida", color, Color.white, 200, 25, 2, 2, 15, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Button button = (Button) e.getSource();
				if (button.getSelected()) return;
				exit.run();
			}
		}),new Button("novo cliente", color, Color.white, 200, 25, 2, 2, 15, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Button button = (Button) e.getSource();
				if (button.getSelected()) return;
				client.run();
			}
		}),new Button("histórico cliente", color, Color.white, 200, 25, 2, 2, 15, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Button button = (Button) e.getSource();
				if (button.getSelected()) return;
				hclient.run();
			}
		}),new Button("histórico entrada", color, Color.white, 200, 25, 2, 2, 15, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Button button = (Button) e.getSource();
				if (button.getSelected()) return;
				hentry.run();
			}
		}),new Button("histórico saída", color, Color.white, 200, 25, 2, 2, 15, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Button button = (Button) e.getSource();
				if (button.getSelected()) return;
				hexit.run();
			}
		}),new Button("relatórios", color, Color.white, 200, 25, 2, 2, 15, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Button button = (Button) e.getSource();
				if (button.getSelected()) return;
				relatory.run();
			}
		})).build();
		for(JComponent component : list.getObjects()) {
			if (!(component instanceof Button)) continue;
			buttons.add((Button) component);
		}
		
		new Draw(203, 0, 3, 720, new Color(71,71,71));
		
	}
			
}
