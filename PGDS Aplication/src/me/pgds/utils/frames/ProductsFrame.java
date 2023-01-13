package me.pgds.utils.frames;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import me.pgds.objects.Product;
import me.pgds.objects.utils.Button;
import me.pgds.objects.utils.List;
import me.pgds.objects.utils.Text;
import me.pgds.objects.utils.TextButton;
import me.pgds.utils.WindowCore;
import me.pgds.utils.frames.main.Frame;

public class ProductsFrame extends Frame{

	public ProductsFrame(int buttonSelected) {
		super(()->{
			
			WindowCore core = WindowCore.getFrame();
			
			TextButton nome = new TextButton(0, 0, new Text("nome", core.getColorBackground().darker(), Color.gray, 500, 30, 0, 0, 17, false, true), 
			new Text(new String(), core.getColorBackground(), Color.white, 500, 30, 0, 0, 17, true, false), null, null);
			
			TextButton desc = new TextButton(0, 0, new Text("descrição", core.getColorBackground().darker(), Color.gray, 500, 30, 0, 0, 17, false, true), 
			new Text(new String(), core.getColorBackground(), Color.white, 500, 30, 0, 0, 17, true, false), null, null);
			
			Button confirmar = new Button("confirmar", new Color(38,128,0), Color.white, 500, 30, 0, 0, 17);
			
			List list = new List(0, 209,2);
			list.addAll(nome.getComponents());
			list.addAll(desc.getComponents());
			list.add(confirmar);
			list.build();
			
			confirmar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (!nome.getText().getText().isEmpty()) {
						if (!desc.getText().getText().isEmpty()) {
							new Product(nome.getText().getText(), desc.getText().getText(), true);
							JOptionPane.showMessageDialog(null, "Você criou um novo produto!");
							core.getProducts().run();
							return;
						}else {
							JOptionPane.showMessageDialog(null, "Você precisa preencher a descrição do produto!");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Você precisa preencher o nome do produto!");
					}
				}
			});
			
		}, buttonSelected);
	}

}
