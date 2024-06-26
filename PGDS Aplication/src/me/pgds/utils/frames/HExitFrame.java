package me.pgds.utils.frames;

import java.awt.Color;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import me.pgds.imgs.ImageAPI;
import me.pgds.objects.Exit;
import me.pgds.objects.managers.Manager;
import me.pgds.objects.utils.Button;
import me.pgds.objects.utils.ListComp;
import me.pgds.objects.utils.Text;
import me.pgds.utils.API;
import me.pgds.utils.WindowCore;
import me.pgds.utils.frames.main.Frame;

public class HExitFrame extends Frame {

	public HExitFrame(int buttonSelected) {
		super(()->{
			
			WindowCore core = WindowCore.getFrame();
			ListComp list = new ListComp(209, 27, 1, 5, new Text("DATA / CLIENTE / QTD E PRODUTO / VALOR TOTAL / VALOR UN / PAGAMENTO / DESC / DELETAR", Color.GRAY.darker(), Color.gray, 1025, 30, 0, 0, 15, false, true),
			new Text("nenhuma sa�da foi encontrada!", Color.red.darker().darker(), Color.gray, 1025, 30, 0, 0, 15, false, false));
			
			for(Exit exit : Manager.get().getExits()) {
				List<JComponent> components = new ArrayList<>();
				components.add(new Text("\n"+exit.getData(), Color.BLACK, Color.gray.darker(), 100, 90, 0, 0, 15, false, true));
				components.add(new Text(exit.getClient() == null ? "nenhum cliente" : exit.getClient().getName() + " > " + exit.getClient().getCpf() + " > " + exit.getClient().getCnpj(), Color.DARK_GRAY.darker(), Color.gray, 150, 90, 0, 0, 15, false, true));
				components.add(new Text(exit.getProduct() == null ? "nenhum produto" : exit.getQuantidade() + "x " + exit.getProduct().getName() + " > " + exit.getProduct().getDesc(), Color.DARK_GRAY.darker().darker(), Color.gray, 150, 90, 0, 0, 15, false, true));
				components.add(new Text(String.valueOf("R$ "+API.formatValue(exit.getValor())), Color.DARK_GRAY.darker(), Color.gray, 100, 90, 0, 0, 15, false, true));
				components.add(new Text(String.valueOf("R$ "+API.formatValue(exit.getValorUn())), Color.DARK_GRAY.darker().darker(), Color.gray, 100, 90, 0, 0, 15, false, true));
				components.add(new Text(exit.getPagamento(), Color.DARK_GRAY.darker(), Color.gray, 100, 90, 0, 0, 15, false, true));
				components.add(new Text(exit.getDesc(), Color.DARK_GRAY.darker().darker(), Color.gray, 300, 90, 0, 0, 15, false, true));
				Button delete = new Button(new String(), Color.black, Color.white, 25, 25, 0, 0, 0, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						exit.delete();
						core.getHexit().run();
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
