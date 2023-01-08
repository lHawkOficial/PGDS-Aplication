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
public class Client {

	private String name, cpf="N.A", cnpj="N.A";
	private String numero1="(62) 9XXXX-XXXX", numero2="(62) XXXX-XXXX";
	private int id;
	private File folder = new File(WindowCore.getFrame().getFolder() + "/clients"), file;
	
	public Client(String name, String numero1, String numero2, String cpf, String cnpj, boolean createfile) {
		this.name = name;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.numero1 = numero1.length() == 0 ? "não tem" : numero1;
		this.numero2 = numero2.length() == 0 ? "não tem" : numero2;
		this.id = Manager.get().getClients().size();
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
		Manager.get().getClients().add(this);
	}
	
	public void save() {
		List<Object> lista = new ArrayList<>();
		lista.add(name);
		lista.add(cpf);
		lista.add(cnpj);
		lista.add(numero1);
		lista.add(numero2);
		new Save(file, lista);
	}
	
	public static Client load(File file) {
		List<Object> lista = Save.load(file);
		if (lista == null) return null;
		if (lista.isEmpty()) return null;
		String name = (String)lista.get(0);
		String cpf = (String)lista.get(1);
		String cnpj = (String)lista.get(2);
		String celular = (String)lista.get(3);
		String telefone = (String)lista.get(4);
		Client client = new Client(name, celular, telefone, cpf, cnpj, false);
		client.setFile(file);
		return client;
	}
	
	public void delete() {
		Manager.get().getClients().remove(this);
		file.delete();
	}
	
}
