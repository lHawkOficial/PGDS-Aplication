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
import me.pgds.objects.Client;
import me.pgds.objects.managers.Manager;
import me.pgds.objects.utils.Button;
import me.pgds.objects.utils.ListComp;
import me.pgds.objects.utils.Text;
import me.pgds.utils.WindowCore;
import me.pgds.utils.frames.main.Frame;

public class HClientFrame extends Frame {

	public HClientFrame(int buttonSelected) {
		super(()->{
			
			WindowCore core = WindowCore.getFrame();
			ListComp list = new ListComp(209, 27, 1, 12, new Text("NOME    /     CPF     /     CNPJ     /     CELULAR     /     TELEFONE     /     DELETAR", Color.GRAY.darker(), Color.gray, 850, 30, 0, 0, 15, false, true),
			new Text("nenhum cliente foi encontrado!", Color.GRAY.darker(), Color.gray, 300, 30, 0, 0, 15, false, false));
			
			for(Client client : Manager.get().getClients()) {
				List<JComponent> components = new ArrayList<>();
				components.add(new Text(client.getName(), Color.DARK_GRAY.darker().darker(), Color.gray, 250, 25, 0, 0, 15, false, true));
				components.add(new Text(client.getCpf(), Color.DARK_GRAY.darker(), Color.gray, 150, 25, 0, 0, 15, false, true));
				components.add(new Text(client.getCnpj(), Color.DARK_GRAY.darker().darker(), Color.gray, 150, 25, 0, 0, 15, false, true));
				components.add(new Text(client.getNumero1(), Color.DARK_GRAY.darker(), Color.gray, 150, 25, 0, 0, 15, false, true));
				components.add(new Text(client.getNumero2(), Color.DARK_GRAY.darker().darker(), Color.gray, 150, 25, 0, 0, 15, false, true));
				Button delete = new Button(new String(), Color.black, Color.white, 25, 25, 0, 0, 0, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						client.delete();
						JOptionPane.showMessageDialog(null, "Cliente deletado com sucesso!");
						core.getHclient().run();
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
