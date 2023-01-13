package me.pgds.utils.frames;

import java.awt.Color;

import me.pgds.objects.utils.Text;
import me.pgds.utils.frames.main.Frame;

public class RelatoryFrame extends Frame{

	public RelatoryFrame(int buttonSelected) {
		super(()->{
			
			new Text("PÁGINA EM DESENVOLVIMENTO", Color.red.darker().darker().darker(), Color.white, 1000, 720, 209, 0, 15, false, false);
			
			/*
			WindowCore core = WindowCore.getFrame();
			ListComp list = new ListComp(209, 27, 1, 10, new Text("ID    /    NOME    /    DELETAR", Color.GRAY.darker(), Color.gray, 300, 30, 0, 0, 15, false, true),
			new Text("nenhum relatório foi encontrado!", Color.red.darker().darker(), Color.gray, 300, 30, 0, 0, 15, false, false));
			
			File folder = new File(core.getFolder() + "/relatorys");
			if (folder.exists()) {
				int i = 0;
				for(File file : folder.listFiles()) {
					List<JComponent> components = new ArrayList<>();
					components.add(new Text(String.valueOf(i), Color.black, Color.gray.darker(), 25, 25, 0, 0, 15, false, false));
					components.add(new Text(file.getName(), Color.DARK_GRAY.darker(), Color.gray, 275, 25, 0, 0, 15, false, true));
					Button delete = new Button(new String(), Color.black, Color.white, 25, 25, 0, 0, 0, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							file.delete();
							core.getRelatory().run();
							return;
						}
					}, core);
					delete.setIcon(new ImageIcon(ImageAPI.getUtils("lixeira.png")));
					delete.setOpaque(false);
					components.add(delete);
					list.add(components);
					i++;
				}
			}
			list.complete().build();
			
			new TextButton(209+325, 2, new Text("novo relatório (entradas)", core.getColorBackground().darker(), Color.gray, 225, 30, 0, 0, 17, false, true), null, new Button("confirmar", new Color(38,128,0), Color.white, 225, 30, 0, 0, 17),()->{
				API.relatoryEntrys();
				core.getRelatory().run();
			});
			
			new TextButton(209+325+227, 2, new Text("novo relatório (saídas)", core.getColorBackground().darker(), Color.gray, 225, 30, 0, 0, 17, false, true), null, new Button("confirmar", new Color(38,128,0), Color.white, 225, 30, 0, 0, 17),()->{
				API.relatoryExits();
				core.getRelatory().run();
			});
			
			new TextButton(209+325+225+229, 2, new Text("novo relatório (geral)", core.getColorBackground().darker(), Color.gray, 225, 30, 0, 0, 17, false, true), null, new Button("confirmar", new Color(38,128,0), Color.white, 225, 30, 0, 0, 17),()->{
				API.relatoryAll();
				core.getRelatory().run();
			});
			*/
			
		}, buttonSelected);
	}

}
