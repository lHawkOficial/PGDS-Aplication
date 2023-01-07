package me.pgds.utils.frames;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import me.pgds.objects.Client;
import me.pgds.objects.managers.Manager;
import me.pgds.objects.utils.Button;
import me.pgds.objects.utils.List;
import me.pgds.objects.utils.Text;
import me.pgds.objects.utils.TextButton;
import me.pgds.utils.WindowCore;
import me.pgds.utils.frames.main.Frame;

public class ClientFrame extends Frame {

	public ClientFrame(int buttonSelected) {
		super(()->{
			
			WindowCore core = WindowCore.getFrame();
			
			TextButton nome = new TextButton(0, 0, new Text("nome", core.getColorBackground().darker(), Color.gray, 500, 30, 0, 0, 17, false, true), 
			new Text(new String(), core.getColorBackground(), Color.white, 500, 30, 0, 0, 17, true, false), null, null);
			
			TextButton cpf = new TextButton(0, 0, new Text("CPF", core.getColorBackground().darker(), Color.gray, 500, 30, 0, 0, 17, false, true), 
			new Text("N.A", core.getColorBackground(), Color.white, 500, 30, 0, 0, 17, true, false), null, null);
			
			TextButton cnpj = new TextButton(0, 0, new Text("CNPJ", core.getColorBackground().darker(), Color.gray, 500, 30, 0, 0, 17, false, true), 
			new Text("N.A", core.getColorBackground(), Color.white, 500, 30, 0, 0, 17, true, false), null, null);
			
			TextButton celular = new TextButton(0, 0, new Text("celular", core.getColorBackground().darker(), Color.gray, 500, 30, 0, 0, 17, false, true), 
			new Text("(62) 9XXXX-XXXX", core.getColorBackground(), Color.white, 500, 30, 0, 0, 17, true, false), null, null);
			
			TextButton telefone = new TextButton(0, 0, new Text("telefone", core.getColorBackground().darker(), Color.gray, 500, 30, 0, 0, 17, false, true), 
			new Text("(62) XXXX-XXXX", core.getColorBackground(), Color.white, 500, 30, 0, 0, 17, true, false), null, null);
			
			Button confirmar = new Button("confirmar", new Color(38,128,0), Color.white, 500, 30, 0, 0, 17);
			
			List list = new List(0, 209,2);
			list.addAll(nome.getComponents());
			list.addAll(cpf.getComponents());
			list.addAll(cnpj.getComponents());
			list.addAll(celular.getComponents());
			list.addAll(telefone.getComponents());
			list.add(confirmar);
			list.build();
			
			confirmar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (!cpf.getText().getText().isEmpty()) {
						if (!nome.getText().getText().isEmpty()) {
							if (!cpf.getText().getText().isEmpty()) {
								if (!cnpj.getText().getText().isEmpty()) {
									if (!celular.getText().getText().isEmpty()) {
										if (!telefone.getText().getText().isEmpty()) {
											if (Manager.get().getClientCpf(cpf.getText().getText()) == null) {
												String no = nome.getText().getText();
												String c = celular.getText().getText();
												String t = telefone.getText().getText();
												String cp = cpf.getText().getText();
												String cn = cnpj.getText().getText();
												new Client(no, c, t, cp, cn);
												JOptionPane.showMessageDialog(null, "Você criou um novo cliente!");
												core.getClient().run();
												return;
											}else {
												JOptionPane.showMessageDialog(null, "Este cliente para foi criado neste cpf!");
											}
										}else {
											JOptionPane.showMessageDialog(null, "Você precisa preencher o campo de telefone do cliente!");
										}
									}else {
										JOptionPane.showMessageDialog(null, "Você precisa preencher o campo de celular do cliente!");
									}
								}else {
									JOptionPane.showMessageDialog(null, "Você precisa preencher o campo de CNPJ do cliente!");
								}
							}else {
								JOptionPane.showMessageDialog(null, "Você precisa preencher o campo de CPF do cliente!");
							}
						}else {
							JOptionPane.showMessageDialog(null, "Você precisa colocar um nome para o cliente!");
						}
					}
				}
			});
			
		}, buttonSelected);
	}

}
