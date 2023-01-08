package me.pgds.objects;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import lombok.Getter;

import lombok.Setter;
import me.pgds.objects.managers.Manager;
import me.pgds.utils.API;
import me.pgds.utils.Save;
import me.pgds.utils.WindowCore;

@Getter
@Setter
public class Exit {

	private String data, desc, pagamento;
	private int quantidade;
	private double valor,valorUn;
	private Product product;
	private Client client;
	private File folder, file;
	
	public Exit(String data,String desc,String pagamento,double valor, boolean createfile) {
		this.data = data;
		this.desc = desc;
		this.pagamento = pagamento;
		this.valor = valor;
		this.folder = new File(WindowCore.getFrame().getFolder()+"/exits");
		if (!folder.exists()) folder.mkdir();
		this.folder = new File(folder + "/"+data.replace("/", "_"));
		if (!folder.exists()) folder.mkdir();
		if (createfile) {
			this.file = new File(folder + "/" + API.randomString() + ".yml");
			if (!file.exists()) {
				try {
					file.createNewFile();
					save();
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		}
		Manager.get().getExits().add(this);
	}
	
	public void save() {
		List<Object> list = new ArrayList<>();
		list.add(data);
		list.add(desc);
		list.add(pagamento);;
		list.add(String.valueOf(valor));
		list.add(String.valueOf(valorUn));
		list.add(String.valueOf(quantidade));
		list.add(product == null ? "N.A" : String.valueOf(product.getId()));
		list.add(client == null ? "N.A" : String.valueOf(client.getId()));
		new Save(file, list);
	}
	
	public static Exit load(File file) {
		List<Object> list = Save.load(file);
		if (list == null) return null;
		if (list.isEmpty()) return null;
		String data = (String)list.get(0);
		String desc = (String)list.get(1);
		String pagamento = (String)list.get(2);
		double valor = Double.valueOf((String)list.get(3));
		double valorUn = Double.valueOf((String)list.get(4));
		int quantidade = Integer.valueOf((String)list.get(5));
		Product product = null;
		Client client = null;
		if (!((String)list.get(6)).equalsIgnoreCase("N.A")) {
			product = Manager.get().getProduct(Integer.valueOf((String)list.get(6)));
		}
		if (!((String)list.get(7)).equalsIgnoreCase("N.A")) {
			client = Manager.get().getClient(Integer.valueOf((String)list.get(7)));
		}
		Exit exit = new Exit(data, desc, pagamento, valor, false);
		exit.setClient(client);
		exit.setProduct(product);
		exit.setValorUn(valorUn);
		exit.setQuantidade(quantidade);
		exit.setFile(file);
		return exit;
	}
	
	public void delete() {
		Manager.get().getExits().remove(this);
		file.delete();
	}
	
}
