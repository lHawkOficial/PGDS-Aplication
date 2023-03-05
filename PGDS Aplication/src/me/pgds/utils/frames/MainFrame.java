package me.pgds.utils.frames;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import lombok.Getter;
import me.pgds.objects.Client;
import me.pgds.objects.Product;
import me.pgds.objects.managers.Manager;
import me.pgds.objects.utils.List;
import me.pgds.objects.utils.Text;
import me.pgds.utils.API;
import me.pgds.utils.WindowCore;
import me.pgds.utils.frames.main.Frame;

@Getter
public class MainFrame extends Frame {

	public MainFrame(int id) {
		super(()->{
			
			WindowCore core = WindowCore.getFrame();
			
			List list = new List(0, 209, 2);
			list.add(new Text("Total Entradas", core.getColorBackground().darker(), Color.gray, 150, 30, 0, 0, 15, false, true));
			list.add(new Text(String.valueOf(Manager.get().getEntrys().size()), core.getColorBackground(), Color.white, 150, 30, 0, 0, 15, false, true));
			list.add(new Text("Valor Total", core.getColorBackground().darker(), Color.gray, 150, 30, 0, 0, 15, false, true));
			list.add(new Text("R$ " + API.formatValue(API.getValueTotalEntrys()), core.getColorBackground(), Color.green.darker(), 150, 30, 0, 0, 12, false, true));
			list.build();
			
			list = new List(0, 209+150, 2);
			list.add(new Text("Total Saídas", core.getColorBackground().darker(), Color.gray, 150, 30, 0, 0, 15, false, true));
			list.add(new Text(String.valueOf(Manager.get().getExits().size()), core.getColorBackground(), Color.white, 150, 30, 0, 0, 15, false, true));
			list.add(new Text("Valor Total", core.getColorBackground().darker(), Color.gray, 150, 30, 0, 0, 15, false, true));
			list.add(new Text("R$ " + API.formatValue(API.getValueTotalExits()), core.getColorBackground(), Color.red.darker().darker(), 150, 30, 0, 0, 12, false, true));
			list.build();
			
			list = new List(0, 209, 122);
			list.add(new Text("                         Saldo Final", core.getColorBackground().darker(), Color.gray, 300, 30, 0, 0, 15, false, true));
			list.add(new Text("                         R$ "+API.formatValue(API.getValueTotalEntrys()-API.getValueTotalExits()), core.getColorBackground(), Color.yellow.darker(), 300, 30, 0, 0, 14, false, true));
			list.build();
			
			list = new List(0, 209+300, 2);
			list.add(new Text("Total Produtos", core.getColorBackground().darker(), Color.gray, 150, 30, 0, 0, 15, false, true));
			list.add(new Text(String.valueOf(Manager.get().getProducts().size()), core.getColorBackground(), Color.white, 150, 30, 0, 0, 15, false, true));
			list.add(new Text("Produtos", core.getColorBackground().darker(), Color.gray, 150, 30, 0, 0, 15, false, true));
			Text produtos = new Text("nenhum", core.getColorBackground(), Color.white.darker(), 150, 30, 0, 0, 12, false, true);
			list.add(produtos);
			list.build();
			
			list = new List(0, 209+300+150, 2);
			list.add(new Text("Total Clientes", core.getColorBackground().darker(), Color.gray, 250, 30, 0, 0, 15, false, true));
			list.add(new Text(String.valueOf(Manager.get().getClients().size()), core.getColorBackground(), Color.white, 250, 30, 0, 0, 15, false, true));
			list.add(new Text("Clientes", core.getColorBackground().darker(), Color.gray, 250, 30, 0, 0, 15, false, true));
			Text clientes = new Text("nenhum", core.getColorBackground(), Color.white.darker(), 250, 30, 0, 0, 12, false, true);
			list.add(clientes);
			list.build();
			
			core.setTimer(new Timer());
			core.getTimer().scheduleAtFixedRate(new TimerTask() {
				int i = 0;
				int j = 0;
				long time = 0;
				@Override
				public void run() {
					if (!Manager.get().getProducts().isEmpty()) {
						Product product;
						if (i<Manager.get().getProducts().size()) {
							product = Manager.get().getProducts().get(i);
							i++;
						}
						if (i>=Manager.get().getProducts().size())i=0;
						product = Manager.get().getProducts().get(i);
						produtos.setText(product.getName() + " > " + product.getDesc());
					}
					if (!Manager.get().getClients().isEmpty() && (System.currentTimeMillis()-time >= 2000)) {
						time = System.currentTimeMillis();
						Client client;
						if (j<Manager.get().getClients().size()) {
							client = Manager.get().getClients().get(j);
							j++;
						}
						if (j>=Manager.get().getClients().size()) j=0;
						client = Manager.get().getClients().get(j);
						clientes.setText(client.getName());
					}
				}
			}, 0, 1500);
			
		}, id);
	}

}
