package me.pgds.utils;

import java.awt.Color;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;
import me.pgds.imgs.ImageAPI;
import me.pgds.objects.managers.Manager;
import me.pgds.objects.utils.Button;
import me.pgds.objects.utils.Draw;
import me.pgds.objects.utils.List;
import me.pgds.objects.utils.Text;
import me.pgds.utils.frames.ClientFrame;
import me.pgds.utils.frames.EntryFrame;
import me.pgds.utils.frames.ExitFrame;
import me.pgds.utils.frames.HClientFrame;
import me.pgds.utils.frames.HEntryFrame;
import me.pgds.utils.frames.HExitFrame;
import me.pgds.utils.frames.HProductsFrame;
import me.pgds.utils.frames.MainFrame;
import me.pgds.utils.frames.ProductsFrame;
import me.pgds.utils.frames.RelatoryFrame;
import me.pgds.utils.frames.main.Frame;
import me.pgds.utils.frames.main.KeyListener;

@Getter
public class WindowCore extends JFrame {
	
	private String version = "v" + 0.1;
	private static final long serialVersionUID = -5140442525033186243L;
	
	private Color colorBackground = new Color(71,71,71);
	private File folder,
	folderProducts,
	folderClients,
	folderRelatorys;
	
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
	products,
	hprodutcs,
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
		setContentPane(new JLabel(new ImageIcon(API.resizeImage(ImageAPI.getUtils("banner.png"), getWidth(), getHeight()))));
		setTitle("PEGADAS PRÉ-MOLDADOS " + version);
		setIconImage(ImageAPI.getIcon("icon_core.png"));
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(new KeyListener());
		createFolders();
		manager = new Manager();
		API.initialize();
		main = new MainFrame(0);
		entry = new EntryFrame(1);
		exit = new ExitFrame(2);
		client = new ClientFrame(3);
		products = new ProductsFrame(4);
		hprodutcs = new HProductsFrame(5);
		hclient = new HClientFrame(6);
		hentry = new HEntryFrame(7);
		hexit = new HExitFrame(8);
		relatory = new RelatoryFrame(9);
		main.run();
	}
	
	private void createFolders() {
		folder = new File("pgds_data");
		if (!folder.exists()) folder.mkdir();
		
		folderProducts = new File(folder + "/produts");
		if (!folderProducts.exists()) folderProducts.mkdir();
		
		folderClients = new File(folder + "/clients");
		if (!folderClients.exists()) folderClients.mkdir();
		
		folderRelatorys = new File(folder + "/relatorys");
		if (!folderRelatorys.exists()) folderRelatorys.mkdir();
		
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
	}
	
	public void defaultIcons() {
		
		buttons.clear();
		Color color = new Color(145, 118, 0);
		int size = 35;
		List list = new List(1, 2, 2,new Button("início", color, Color.white, 200, size, 2, 2, 15, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Button button = (Button) e.getSource();
				if (button.getSelected()) return;
				main.run();
			}
		}),new Button("nova entrada", color, Color.white, 200, size, 2, 2, 15, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Button button = (Button) e.getSource();
				if (button.getSelected()) return;
				entry.run();
			}
		}),new Button("nova saida", color, Color.white, 200, size, 2, 2, 15, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Button button = (Button) e.getSource();
				if (button.getSelected()) return;
				exit.run();
			}
		}),new Button("novo cliente", color, Color.white, 200, size, 2, 2, 15, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Button button = (Button) e.getSource();
				if (button.getSelected()) return;
				client.run();
			}
		}),new Button("novo produto", color, Color.white, 200, size, 2, 2, 15, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Button button = (Button) e.getSource();
				if (button.getSelected()) return;
				products.run();
			}
		}),new Button("produtos", color, Color.white, 200, size, 2, 2, 15, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Button button = (Button) e.getSource();
				if (button.getSelected()) return;
				hprodutcs.run();
			}
		}),new Button("clientes", color, Color.white, 200, size, 2, 2, 15, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Button button = (Button) e.getSource();
						if (button.getSelected()) return;
						hclient.run();
					}
		}),new Button("entradas", color, Color.white, 200, size, 2, 2, 15, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Button button = (Button) e.getSource();
				if (button.getSelected()) return;
				hentry.run();
			}
		}),new Button("saídas", color, Color.white, 200, size, 2, 2, 15, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Button button = (Button) e.getSource();
				if (button.getSelected()) return;
				hexit.run();
			}
		}),new Button("relatórios", color, Color.white, 200, size, 2, 2, 15, new ActionListener() {
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
		new Draw(203, 0, 3, 720, colorBackground);
		Text vs = new Text("🔧 " + version, Color.black, Color.white, 200, 25, 2, 655, 16, false, false);
		vs.setOpaque(true);
	}
			
}
