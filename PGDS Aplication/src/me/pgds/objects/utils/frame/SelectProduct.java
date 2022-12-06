package me.pgds.objects.utils.frame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import lombok.Getter;
import me.pgds.imgs.ImageAPI;
import me.pgds.objects.Product;
import me.pgds.utils.WindowCore;

@Getter
public class SelectProduct {

	private Product selected;
	private long time = System.currentTimeMillis();
	
	public SelectProduct() {
		
		WindowCore core = WindowCore.getFrame();
		core.setVisible(false);
		JFrame frame = new JFrame("Selecionar produto");
		frame.setSize(core.getSize());
		frame.setLocation(core.getLocation());
		frame.setContentPane(new JLabel(new ImageIcon(ImageAPI.getUtils("banner.png"))));
		frame.setIconImage(ImageAPI.getIcon("icon_core.png"));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		new Thread(()->{
			
			while(true) {
				if (System.currentTimeMillis() - time >= 5000) {
					core.setLocation(frame.getLocation());
					frame.setVisible(false);
					core.setVisible(true);
					break;
				}
			}
			
		}).run();
		
	}
	
}
