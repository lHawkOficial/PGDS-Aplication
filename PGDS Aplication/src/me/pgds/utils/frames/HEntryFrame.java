package me.pgds.utils.frames;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;


import me.pgds.imgs.ImageAPI;
import me.pgds.objects.Entry;
import me.pgds.objects.managers.Manager;
import me.pgds.objects.utils.Button;
import me.pgds.objects.utils.ListComp;
import me.pgds.objects.utils.Text;
import me.pgds.utils.API;
import me.pgds.utils.WindowCore;
import me.pgds.utils.frames.main.Frame;

public class HEntryFrame extends Frame {

	public HEntryFrame(int buttonSelected) {
		super(()->{
			
			WindowCore core = WindowCore.getFrame();
			ListComp list = new ListComp(209, 27, 1, 5, new Text("DATA / PRODUTO / VALOR TOTAL / VALOR UN / PAGAMENTO / DESC", Color.GRAY.darker(), Color.gray, 875, 30, 0, 0, 15, false, true),
			new Text("nenhuma entrada foi encontrada!", Color.red.darker().darker(), Color.gray, 875, 30, 0, 0, 15, false, false));
			
			for(Entry entry : Manager.get().getEntrys()) {
				List<JComponent> components = new ArrayList<>();
				components.add(new Text(entry.getData(), Color.BLACK, Color.gray.darker(), 100, 75, 0, 0, 15, false, true));
				components.add(new Text(entry.getProduct() == null ? "nenhum" : entry.getProduct().getName() + " > " + entry.getProduct().getDesc(), Color.DARK_GRAY.darker().darker(), Color.gray, 150, 75, 0, 0, 15, false, true));
				components.add(new Text(String.valueOf(API.formatValue(entry.getValor())), Color.DARK_GRAY.darker(), Color.gray, 100, 75, 0, 0, 15, false, true));
				components.add(new Text(String.valueOf(API.formatValue(entry.getValorUn())), Color.DARK_GRAY.darker().darker(), Color.gray, 100, 75, 0, 0, 15, false, true));
				components.add(new Text(entry.getPagamento(), Color.DARK_GRAY.darker(), Color.gray, 150, 75, 0, 0, 15, false, true));
				components.add(new Text(entry.getDesc(), Color.DARK_GRAY.darker().darker(), Color.gray, 350, 75, 0, 0, 15, false, true));
				Button delete = new Button(new String(), Color.black, Color.white, 25, 25, 0, 0, 0, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						entry.delete();
						JOptionPane.showMessageDialog(null, "Entrada deletada com sucesso!");
						core.getHentry().run();
						return;
					}
				}, core);
				delete.setIcon(new ImageIcon(ImageAPI.getUtils("lixeira.png")));
				delete.setOpaque(false);
				components.add(delete);
				list.add(components);
			}
			list.complete().build();
			
		},buttonSelected);
	}

}
