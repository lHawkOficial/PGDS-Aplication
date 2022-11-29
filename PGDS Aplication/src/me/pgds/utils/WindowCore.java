package me.pgds.utils;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import lombok.Getter;
import me.pgds.imgs.ImageAPI;
import me.pgds.objects.utils.Button;
import me.pgds.utils.frames.MainFrame;

@Getter
public class WindowCore extends JFrame {
	
	private static final long serialVersionUID = -5140442525033186243L;
	
	@Getter
	private static WindowCore frame;
	
	public WindowCore() {
		frame = this;
		setSize(1280, 720);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.GRAY.darker());
		setTitle("PEGADAS PRÉ-MOLDADOS");
		setIconImage(ImageAPI.get("icon_core.png"));
		
		new MainFrame(()->{
			clear();
			Button button = new Button("Botão Teste", Color.YELLOW.darker().darker(), Color.white, 100, 25, 2, 2);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					clear();
				}
			});
		}).run();
		
	}
	
	public WindowCore init() {
		setVisible(true);
		update(getGraphics());
		return this;
	}
	
	public void update() {
		frame.repaint();
	}
	
	public void clear() {
		frame.getContentPane().removeAll();
		update();
	}
			
}
