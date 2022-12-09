package me.pgds.objects.utils.frame;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

import lombok.Getter;
import me.pgds.imgs.ImageAPI;
import me.pgds.objects.Client;
import me.pgds.objects.managers.Manager;
import me.pgds.objects.utils.Button;
import me.pgds.objects.utils.ListComp;
import me.pgds.objects.utils.Text;
import me.pgds.objects.utils.TextClient;
import me.pgds.utils.API;
import me.pgds.utils.WindowCore;

@Getter
public class SelectClient {

	private Client selected;
	private boolean searching = true;
	private long time = System.currentTimeMillis();
	private JFrame frame;
	private TextClient textproduct;
	
	public SelectClient(TextClient textProduct) {
		this.textproduct = textProduct;
		WindowCore core = WindowCore.getFrame();
		core.setVisible(false);
		frame = new JFrame("Selecionar cliente");
		frame.setSize(core.getSize());
		frame.setLocation(core.getLocation());
		frame.setContentPane(new JLabel(new ImageIcon(API.resizeImage(ImageAPI.getUtils("banner.png"), frame.getWidth(), frame.getHeight()))));
		frame.setIconImage(ImageAPI.getIcon("icon_core.png"));
		frame.setResizable(false);
		frame.revalidate();
		frame.repaint();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.setLayout(null);
		
		new Thread(()->{
			
			new Button("voltar", core.getBackground().darker(), Color.white, 100, 30, 4, 2, 15, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					searching = false;
				}
			},frame);
			
			ListComp list = new ListComp(4, 63, 1, 12, 
			new Text("Selecione um cliente logo abaixo", core.getBackground().darker().darker(), Color.white, 400, 30, 0, 0, 17, false, true, frame),
			new Text("nenhum cliente foi encontrado!", new Color(175,0,0), Color.white, 520, 30, 0, 0, 17, false, false, frame), frame);
			for(Client client : Manager.get().getClients()) {
				List<JComponent> components = new ArrayList<>();
				components.add(new Text(""+client.getId(), Color.BLACK, Color.GRAY, 35, 30, 0, 0, 15, false, false,frame));
				components.add(new Text(client.getName(), new Color(71,71,71), Color.white, 240, 30, 0, 0, 15, false, true,frame));
				components.add(new Text(client.getNumero1(), new Color(71,71,71).darker(), Color.white, 120, 30, 0, 0, 15, false, true,frame));
				components.add(new Text(client.getNumero2(), new Color(71,71,71).darker().darker(), Color.white, 120, 30, 0, 0, 15, false, true,frame));
				Button button = new Button(null, Color.black, Color.white, 30, 30, 0, 0, 15, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						selected = client;
					}
				},frame);
				button.setIcon(new ImageIcon(API.resizeImage(ImageAPI.getUtils("search.png"), 30, 30)));
				button.setOpaque(false);
				components.add(button);
				list.add(components);
			}
			list.complete().build();
			frame.revalidate();
			frame.repaint();
			
			new Timer().scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					if (selected == null && searching) return;
					this.cancel();
					finish();
					textproduct.updateSelected();
				}
			}, 0, 100);
			
		}).run();
		
	}
	
	private Client finish() {
		searching = false;
		WindowCore core = WindowCore.getFrame();
		core.setLocation(frame.getLocation());
		frame.setVisible(false);
		core.setVisible(true);
		return selected;
	}
	
}
