package me.pgds.utils.frames;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import me.pgds.imgs.ImageAPI;
import me.pgds.objects.Product;
import me.pgds.objects.managers.Manager;
import me.pgds.objects.utils.Button;
import me.pgds.objects.utils.ListComp;
import me.pgds.objects.utils.Text;
import me.pgds.utils.WindowCore;
import me.pgds.utils.frames.main.Frame;

public class HProductsFrame extends Frame{

	public HProductsFrame(int buttonSelected) {
		super(()->{
			
			WindowCore core = WindowCore.getFrame();
			ListComp list = new ListComp(209, 27, 1, 12, new Text("NOME   /   DESCRIÇÃO   /   DELETAR", Color.GRAY.darker(), Color.gray, 300, 30, 0, 0, 15, false, true),
			new Text("nenhum produto foi encontrado!", Color.red.darker().darker(), Color.gray, 300, 30, 0, 0, 15, false, false));
			
			for(Product product : Manager.get().getProducts()) {
				List<JComponent> components = new ArrayList<>();
				components.add(new Text(product.getName(), Color.DARK_GRAY.darker().darker(), Color.gray, 150, 25, 0, 0, 15, false, true));
				components.add(new Text(product.getDesc(), Color.DARK_GRAY.darker(), Color.gray, 150, 25, 0, 0, 15, false, true));
				Button delete = new Button(new String(), Color.black, Color.white, 25, 25, 0, 0, 0, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						product.delete();
						core.getHprodutcs().run();
						return;
					}
				}, core);
				delete.setIcon(new ImageIcon(ImageAPI.getUtils("lixeira.png")));
				delete.setOpaque(false);
				components.add(delete);
				list.add(components);
			}
			list.complete().build();
			
		}, buttonSelected);
	}

}
