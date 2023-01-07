package me.pgds.utils.frames;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import me.pgds.imgs.ImageAPI;
import me.pgds.objects.Entry;
import me.pgds.objects.utils.Button;
import me.pgds.objects.utils.List;
import me.pgds.objects.utils.Text;
import me.pgds.objects.utils.TextButton;
import me.pgds.objects.utils.TextClient;
import me.pgds.objects.utils.TextProduct;
import me.pgds.utils.API;
import me.pgds.utils.WindowCore;
import me.pgds.utils.frames.main.Frame;

public class EntryFrame extends Frame {

	public EntryFrame(int buttonSelected) {
		super(()->{
			
			WindowCore core = WindowCore.getFrame();
			
			TextButton data = new TextButton(0, 0, new Text("data", core.getColorBackground().darker(), Color.gray, 250, 30, 0, 0, 17, false, true), 
			new Text(API.getData(), core.getColorBackground(), Color.white, 250, 30, 0, 0, 17, true, false), null, null);
			
			TextButton valor = new TextButton(0, 0, new Text("valor total", core.getColorBackground().darker(), Color.gray, 250, 30, 0, 0, 17, false, true), 
			new Text(null, core.getColorBackground(), Color.white, 250, 30, 0, 0, 17, true, false), null, null);
			
			TextButton pagamento = new TextButton(0, 0,new Text("pagamento", core.getColorBackground().darker(), Color.gray, 250, 30, 0, 0, 17, false, true), 
			new Text(null, core.getColorBackground(), Color.white, 250, 30, 0, 0, 17, true, false), null, null);
			
			TextButton desc = new TextButton(0, 0,new Text("descrição", core.getColorBackground().darker(), Color.gray, 250, 30, 0, 0, 17, false, true), 
			new Text(null, core.getColorBackground(), Color.white, 250, 120, 0, 0, 17, true, false), null, null);
			
			Button confirmar = new Button("confirmar", new Color(38,128,0), Color.white, 754, 30, 0, 0, 17);
			
			List list = new List(0, 209, 2);
			list.addAll(data.getComponents());
			list.addAll(valor.getComponents());
			list.addAll(pagamento.getComponents());
			list.addAll(desc.getComponents());
			list.add(confirmar);
			list.build();
			
			TextButton valorUnidade = new TextButton(0, 0,new Text("valor p/unidade", core.getColorBackground().darker(), Color.gray, 250, 30, 0, 0, 17, false, true), 
			new Text(null, core.getColorBackground(), Color.white, 250, 30, 0, 0, 17, true, false), null, null);
			
			TextButton quantidade = new TextButton(0, 0,new Text("quantidade", core.getColorBackground().darker(), Color.gray, 250, 30, 0, 0, 17, false, true), 
			new Text(null, core.getColorBackground(), Color.white, 250, 30, 0, 0, 17, true, false), null, null);
					
			TextProduct product = new TextProduct(0, 0,new Text("produto", core.getColorBackground().darker(), Color.gray, 250, 30, 0, 0, 17, false, true), 
			new Text("nenhum", core.getColorBackground(), Color.white, 250, 30, 0, 0, 17, false, false),
			new Button(null, Color.BLACK, Color.white, 250, 30, 0, 0, 17));
			product.getSelect().setIcon(new ImageIcon(API.resizeImage(ImageAPI.getUtils("search.png"), 30, 30)));
			product.getSelect().setOpaque(false);
			
			list = new List(0, 461, 2);
			list.addAll(valorUnidade.getComponents());
			list.addAll(quantidade.getComponents());
			list.addAll(product.getComponents());
			list.build();
			
			TextClient cliente = new TextClient(0, 0,new Text("cliente", core.getColorBackground().darker(), Color.gray, 250, 30, 0, 0, 17, false, true), 
			new Text("N.A", core.getColorBackground(), Color.white, 250, 30, 0, 0, 17, false, false),
			new Button(null, Color.BLACK, Color.white, 250, 30, 0, 0, 17));
			cliente.getSelect().setIcon(new ImageIcon(API.resizeImage(ImageAPI.getUtils("search.png"), 30, 30)));
			cliente.getSelect().setOpaque(false);
			
							
			list = new List(0, 713, 2);
			list.addAll(cliente.getComponents());
			list.build();
			
			confirmar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (!data.getText().getText().isEmpty()) {
						if (!valor.getText().getText().isEmpty() && API.isDouble(valor.getText().getText().replace(",", "."))) {
							double value = Double.valueOf(valor.getText().getText().replace(",", "."));
							if (value >= 0 && value <= Double.MAX_VALUE) {
								if (!pagamento.getText().getText().isEmpty()) {
									String descricao = desc.getText().getText();
									descricao = descricao.isEmpty() ? "N.A" : descricao;
									Entry entry = new Entry(data.getText().getText(), descricao, pagamento.getText().getText(), value, true);
									if (product.getSelected() != null) entry.setProduct(product.getSelected());
									if (!valorUnidade.getText().getText().isEmpty()) {
										if (API.isDouble(valorUnidade.getText().getText())) {
											entry.setValorUn(Double.valueOf(valorUnidade.getText().getText().replace(",", ".")));
										} else {
											JOptionPane.showMessageDialog(null, "O valor per/Unidade foi escrito incorretamente e o valor foi setado para 0!");
										}
									}
									if (!quantidade.getText().getText().isEmpty()) {
										if (API.isInteger(quantidade.getText().getText())) {
											entry.setQuantidade(Integer.valueOf(quantidade.getText().getText().replace(",", ".")));
										} else {
											JOptionPane.showMessageDialog(null, "A quantidade foi escrita incorretamente e o valor foi setado para 0!");
										}
									}
									entry.setProduct(product.getSelected());
									entry.setClient(cliente.getSelected());
									entry.save();
									JOptionPane.showMessageDialog(null, "uma nova entrada foi efetuada!");
									WindowCore.getFrame().getEntry().run();
									return;
								}else {
									JOptionPane.showMessageDialog(null, "Preencha o campo de pagamento!");	
								}
							}else {
								JOptionPane.showMessageDialog(null, "O valor foi escrito de forma incorreta!");
							}
						}else {
							JOptionPane.showMessageDialog(null, "O valor foi escrito de forma incorreta!");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Digite a data corretamente!");
					}
				}
			});
			
		}, buttonSelected);
	}

}
