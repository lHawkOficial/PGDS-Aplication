package me.pgds.utils;

import java.awt.Desktop;
import java.awt.Graphics2D;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import me.pgds.objects.Client;
import me.pgds.objects.Entry;
import me.pgds.objects.Exit;
import me.pgds.objects.Product;
import me.pgds.objects.managers.Manager;

public class API {
	
	public static void updateOrganizationVariablesManager() {
		try {
			
			Object[] objectArray = Manager.get().getProducts().toArray();
			Product[] products = new Product[objectArray.length];
			for (int i = 0; i < objectArray.length; i++) {
				products[i] = (Product) objectArray[i];
			}
			Arrays.sort(products, (f1, f2) -> f1.getName().compareToIgnoreCase(f2.getName()));
			List<Product> listProduct = new ArrayList<>();
			for(Product product : products) {
				listProduct.add(product);
			}
			Manager.get().setProducts(listProduct);
			
			objectArray = Manager.get().getClients().toArray();
			Client[] clients = new Client[objectArray.length];
			for (int i = 0; i < objectArray.length; i++) {
				clients[i] = (Client) objectArray[i];
			}
			Arrays.sort(clients, (f1, f2) -> f1.getName().compareToIgnoreCase(f2.getName()));
			List<Client> listClients = new ArrayList<>();
			for(Client client : clients) {
				listClients.add(client);
			}
			Manager.get().setClients(listClients);
			
			objectArray = Manager.get().getEntrys().toArray();
			Entry[] entrys = new Entry[objectArray.length];
			for (int i = 0; i < objectArray.length; i++) {
				entrys[i] = (Entry) objectArray[i];
			}
			Arrays.sort(entrys, (f1, f2) -> f1.getData().compareToIgnoreCase(f2.getData()));
			List<Entry> listEntrys = new ArrayList<>();
			for(Entry entry : entrys) {
				listEntrys.add(entry);
			}
			Manager.get().setEntrys(listEntrys);
			
			objectArray = Manager.get().getExits().toArray();
			Exit[] exits = new Exit[objectArray.length];
			for (int i = 0; i < objectArray.length; i++) {
				exits[i] = (Exit) objectArray[i];
			}
			Arrays.sort(exits, (f1, f2) -> f1.getData().compareToIgnoreCase(f2.getData()));
			List<Exit> listExits = new ArrayList<>();
			for(Exit exit : listExits) {
				listExits.add(exit);
			}
			Manager.get().setExits(listExits);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[!] Não foi possivel atualizar a organização das variaveis.");
		}
		
	}
	
	public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
	    try {
	    	Image image = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
	    	BufferedImage img = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
	    	Graphics2D g = img.createGraphics();
	    	g.drawImage(image, 0, 0, null);
	    	g.dispose();
	    	return img;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static double getValueTotalEntrys() {
		double valor = 0;
		for(Entry e : Manager.get().getEntrys()) valor+=e.getValor();
		return valor;
	}

	public static double getValueTotalExits() {
		double valor = 0;
		for(Exit e : Manager.get().getExits()) valor+=e.getValor();
		return valor;
	}
	
	public static double formatValue(double valor) {
		NumberFormat formatter = new DecimalFormat("0.00");
		return Double.valueOf(formatter.format(valor).replace(",", "."));
	}
	
	public static String getData() {
		return new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
	}
	
	public static boolean isInteger(String txt) {
		try {
			Integer.valueOf(txt.replace(",", "."));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isDouble(String txt) {
		try {
			Double.valueOf(txt.replace(",", "."));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static String randomString() {
		String txt = new String();
		while(true) {
			if (txt.length() >= 10) break;
			loop: {
				for (char l = 'a'; l < 'z'; l++) {
					int chance = new Random().nextInt(100);
					if (chance <= 1) {
						txt += new Random().nextBoolean() ? String.valueOf(l).toUpperCase() : String.valueOf(l).toLowerCase();
						break loop;
					}
				}
			}
		}
		return txt;
	}
	
	public static void initialize() {
		
		WindowCore core = WindowCore.getFrame();
		
		File folder = new File(core.getFolder() + "/clients");
		if (folder.exists()) {
			for(File file : folder.listFiles()) {
				if (!file.getName().endsWith(".yml")) continue;
				Client.load(file);
			}
		}
		
		folder = new File(core.getFolder() + "/produts");
		if (folder.exists()) {
			for(File file : folder.listFiles()) {
				if (!file.getName().endsWith(".yml")) continue;
				Product.load(file);
			}
		}
		
		folder = new File(core.getFolder() + "/entrys");
		if (folder.exists()) {
			for(File fd : folder.listFiles()) {
				if (!fd.isDirectory()) continue;
				for(File file : fd.listFiles()) {
					if (file.getName().endsWith(".yml")) {
						Entry.load(file);
					}
				}
			}
		}
		
		folder = new File(core.getFolder() + "/exits");
		if (folder.exists()) {
			for(File fd : folder.listFiles()) {
				if (!fd.isDirectory()) continue;
				for(File file : fd.listFiles()) {
					if (file.getName().endsWith(".yml")) {
						Exit.load(file);
					}
				}
			}
		}
		
	}
	
	public static void relatoryEntrys() {
		WindowCore core = WindowCore.getFrame();
		File folder = new File(core.getFolder() + "/relatorys");
		File file = new File(folder + "/todos_"+randomString() + ".pdf");
		if (!file.exists()) {
			Document document = new Document();
			try {
				PdfWriter.getInstance(document, new FileOutputStream(file));
				document.open();
				document.setPageSize(PageSize.A4);
	            Font font = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.BLACK);
				Paragraph title = new Paragraph("RELATÓRIO DAS ENTRADAS");
				title.setAlignment(Element.ALIGN_CENTER);
				title.setFont(font);
				document.add(title);
				font = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
				Paragraph paragraph = new Paragraph("Aqui estão listadas " + Manager.get().getEntrys().size() + " entrada(s). (R$ " + API.getValueTotalEntrys()+")");
				paragraph.setFont(font);
				paragraph.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph);
				document.add(new Paragraph(" "));
				font = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
				PdfPTable table = new PdfPTable(7);
				Paragraph p = new Paragraph("DATA");
				p.setFont(font);
				PdfPCell cell1 = new PdfPCell(p);
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				p = new Paragraph("CLIENTE");
				p.setFont(font);
				PdfPCell cell2 = new PdfPCell(p);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				p = new Paragraph("PRODUTO");
				p.setFont(font);
				PdfPCell cell3 = new PdfPCell(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				p = new Paragraph("VALOR TOTAL");
				p.setFont(font);
				PdfPCell cell4 = new PdfPCell(p);
				cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				p = new Paragraph("VALOR UN");
				p.setFont(font);
				PdfPCell cell5 = new PdfPCell(p);
				cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
				p = new Paragraph("PAGAMENTO");
				p.setFont(font);
				PdfPCell cell6 = new PdfPCell(p);
				cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
				p = new Paragraph("DESCRIÇÃO");
				p.setFont(font);
				PdfPCell cell7 = new PdfPCell(p);
				cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell1);
				table.addCell(cell2);
				table.addCell(cell3);
				table.addCell(cell4);
				table.addCell(cell5);
				table.addCell(cell6);
				table.addCell(cell7);
				document.add(table);
				font = new Font(Font.FontFamily.HELVETICA, 6, Font.NORMAL, BaseColor.BLACK);
				for(Entry entry : Manager.get().getEntrys()) {
					table = new PdfPTable(7);
					p = new Paragraph(entry.getData());
					p.setFont(font);
					cell1 = new PdfPCell(p);
					p = new Paragraph(entry.getClient() == null ? "nenhum cliente" : entry.getClient().getName() + " > " + entry.getClient().getCpf() + " > " + entry.getClient().getCnpj());
					p.setFont(font);
					cell2 = new PdfPCell(p);
					p = new Paragraph(entry.getProduct() == null ? "nenhum produto" : entry.getQuantidade() + "x " + entry.getProduct().getName() + " > " + entry.getProduct().getDesc());
					p.setFont(font);
					cell3 = new PdfPCell(p);
					p = new Paragraph(String.valueOf("R$ "+API.formatValue(entry.getValor())));
					p.setFont(font);
					cell4 = new PdfPCell(p);
					p = new Paragraph(String.valueOf("R$ "+API.formatValue(entry.getValorUn())));
					p.setFont(font);
					cell5 = new PdfPCell(p);
					p = new Paragraph(entry.getPagamento());
					p.setFont(font);
					cell6 = new PdfPCell(p);
					p = new Paragraph(entry.getDesc());
					p.setFont(font);
					cell7 = new PdfPCell(new Paragraph(entry.getDesc()));
					table.addCell(cell1);
					table.addCell(cell2);
					table.addCell(cell3);
					table.addCell(cell4);
					table.addCell(cell5);
					table.addCell(cell6);
					table.addCell(cell7);
					document.add(table);
				}
				document.close();
				try {
					Desktop.getDesktop().open(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException | DocumentException e) {
				e.printStackTrace();
			}
		}
	}

	public static void relatoryExits() {
		WindowCore core = WindowCore.getFrame();
		File folder = new File(core.getFolder() + "/relatorys");
		File file = new File(folder + "/saida_"+randomString() + ".pdf");
		if (!file.exists()) {
			Document document = new Document();
			try {
				PdfWriter.getInstance(document, new FileOutputStream(file));
				document.open();
				document.setPageSize(PageSize.A4);
	            Font font = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.BLACK);
				Paragraph title = new Paragraph("RELATÓRIO DAS SAÍDAS");
				title.setAlignment(Element.ALIGN_CENTER);
				title.setFont(font);
				document.add(title);
				font = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
				Paragraph paragraph = new Paragraph("Aqui estão listadas " + Manager.get().getExits().size() + " saída(s). (R$ " + API.getValueTotalExits()+")");
				paragraph.setFont(font);
				paragraph.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph);
				document.add(new Paragraph(" "));
				font = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
				PdfPTable table = new PdfPTable(7);
				Paragraph p = new Paragraph("DATA");
				p.setFont(font);
				PdfPCell cell1 = new PdfPCell(p);
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				p = new Paragraph("CLIENTE");
				p.setFont(font);
				PdfPCell cell2 = new PdfPCell(p);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				p = new Paragraph("PRODUTO");
				p.setFont(font);
				PdfPCell cell3 = new PdfPCell(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				p = new Paragraph("VALOR TOTAL");
				p.setFont(font);
				PdfPCell cell4 = new PdfPCell(p);
				cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				p = new Paragraph("VALOR UN");
				p.setFont(font);
				PdfPCell cell5 = new PdfPCell(p);
				cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
				p = new Paragraph("PAGAMENTO");
				p.setFont(font);
				PdfPCell cell6 = new PdfPCell(p);
				cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
				p = new Paragraph("DESCRIÇÃO");
				p.setFont(font);
				PdfPCell cell7 = new PdfPCell(p);
				cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell1);
				table.addCell(cell2);
				table.addCell(cell3);
				table.addCell(cell4);
				table.addCell(cell5);
				table.addCell(cell6);
				table.addCell(cell7);
				document.add(table);
				font = new Font(Font.FontFamily.HELVETICA, 6, Font.NORMAL, BaseColor.BLACK);
				for(Exit exit : Manager.get().getExits()) {
					table = new PdfPTable(7);
					p = new Paragraph(exit.getData());
					p.setFont(font);
					cell1 = new PdfPCell(p);
					p = new Paragraph(exit.getClient() == null ? "nenhum cliente" : exit.getClient().getName() + " > " + exit.getClient().getCpf() + " > " + exit.getClient().getCnpj());
					p.setFont(font);
					cell2 = new PdfPCell(p);
					p = new Paragraph(exit.getProduct() == null ? "nenhum produto" : exit.getQuantidade() + "x " + exit.getProduct().getName() + " > " + exit.getProduct().getDesc());
					p.setFont(font);
					cell3 = new PdfPCell(p);
					p = new Paragraph(String.valueOf("R$ "+API.formatValue(exit.getValor())));
					p.setFont(font);
					cell4 = new PdfPCell(p);
					p = new Paragraph(String.valueOf("R$ "+API.formatValue(exit.getValorUn())));
					p.setFont(font);
					cell5 = new PdfPCell(p);
					p = new Paragraph(exit.getPagamento());
					p.setFont(font);
					cell6 = new PdfPCell(p);
					p = new Paragraph(exit.getDesc());
					p.setFont(font);
					cell7 = new PdfPCell(new Paragraph(exit.getDesc()));
					table.addCell(cell1);
					table.addCell(cell2);
					table.addCell(cell3);
					table.addCell(cell4);
					table.addCell(cell5);
					table.addCell(cell6);
					table.addCell(cell7);
					document.add(table);
				}
				document.close();
				try {
					Desktop.getDesktop().open(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException | DocumentException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void relatoryAll() {
		WindowCore core = WindowCore.getFrame();
		File folder = new File(core.getFolder() + "/relatorys");
		File file = new File(folder + "/saida_"+randomString() + ".pdf");
		if (!file.exists()) {
			Document document = new Document();
			try {
				PdfWriter.getInstance(document, new FileOutputStream(file));
				document.open();
				document.setPageSize(PageSize.A4);
	            Font font = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.BLACK);
				Paragraph title = new Paragraph("RELATÓRIO DE ENTRADAS E SAÍDAS");
				title.setAlignment(Element.ALIGN_CENTER);
				title.setFont(font);
				document.add(title);
				font = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
				Paragraph paragraph = new Paragraph("Aqui estão listadas " + Manager.get().getEntrys().size() + " entrada(s). (R$ " + API.getValueTotalEntrys()+")");
				paragraph.setFont(font);
				paragraph.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph);
				paragraph = new Paragraph("Aqui estão listadas " + Manager.get().getExits().size() + " saída(s). (R$ " + API.getValueTotalExits()+")");
				paragraph.setFont(font);
				paragraph.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph);
				document.add(new Paragraph(" "));
				font = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
				PdfPTable table = new PdfPTable(7);
				Paragraph p = new Paragraph("DATA");
				p.setFont(font);
				PdfPCell cell1 = new PdfPCell(p);
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				p = new Paragraph("CLIENTE");
				p.setFont(font);
				PdfPCell cell2 = new PdfPCell(p);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				p = new Paragraph("PRODUTO");
				p.setFont(font);
				PdfPCell cell3 = new PdfPCell(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				p = new Paragraph("VALOR TOTAL");
				p.setFont(font);
				PdfPCell cell4 = new PdfPCell(p);
				cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				p = new Paragraph("VALOR UN");
				p.setFont(font);
				PdfPCell cell5 = new PdfPCell(p);
				cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
				p = new Paragraph("PAGAMENTO");
				p.setFont(font);
				PdfPCell cell6 = new PdfPCell(p);
				cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
				p = new Paragraph("DESCRIÇÃO");
				p.setFont(font);
				PdfPCell cell7 = new PdfPCell(p);
				cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell1);
				table.addCell(cell2);
				table.addCell(cell3);
				table.addCell(cell4);
				table.addCell(cell5);
				table.addCell(cell6);
				table.addCell(cell7);
				document.add(table);
				font = new Font(Font.FontFamily.HELVETICA, 6, Font.NORMAL, BaseColor.BLACK);
				document.add(new Paragraph(" "));
				document.add(new Paragraph("ENTRADA(S)"));
				document.add(new Paragraph(" "));
				for(Entry entry : Manager.get().getEntrys()) {
					table = new PdfPTable(7);
					p = new Paragraph(entry.getData());
					p.setFont(font);
					cell1 = new PdfPCell(p);
					p = new Paragraph(entry.getClient() == null ? "nenhum cliente" : entry.getClient().getName() + " > " + entry.getClient().getCpf() + " > " + entry.getClient().getCnpj());
					p.setFont(font);
					cell2 = new PdfPCell(p);
					p = new Paragraph(entry.getProduct() == null ? "nenhum produto" : entry.getQuantidade() + "x " + entry.getProduct().getName() + " > " + entry.getProduct().getDesc());
					p.setFont(font);
					cell3 = new PdfPCell(p);
					p = new Paragraph(String.valueOf("R$ "+API.formatValue(entry.getValor())));
					p.setFont(font);
					cell4 = new PdfPCell(p);
					p = new Paragraph(String.valueOf("R$ "+API.formatValue(entry.getValorUn())));
					p.setFont(font);
					cell5 = new PdfPCell(p);
					p = new Paragraph(entry.getPagamento());
					p.setFont(font);
					cell6 = new PdfPCell(p);
					p = new Paragraph(entry.getDesc());
					p.setFont(font);
					cell7 = new PdfPCell(new Paragraph(entry.getDesc()));
					table.addCell(cell1);
					table.addCell(cell2);
					table.addCell(cell3);
					table.addCell(cell4);
					table.addCell(cell5);
					table.addCell(cell6);
					table.addCell(cell7);
					document.add(table);
				}
				document.add(new Paragraph(" "));
				document.add(new Paragraph("SAÍDA(S)"));
				document.add(new Paragraph(" "));
				for(Exit exit : Manager.get().getExits()) {
					table = new PdfPTable(7);
					p = new Paragraph(exit.getData());
					p.setFont(font);
					cell1 = new PdfPCell(p);
					p = new Paragraph(exit.getClient() == null ? "nenhum cliente" : exit.getClient().getName() + " > " + exit.getClient().getCpf() + " > " + exit.getClient().getCnpj());
					p.setFont(font);
					cell2 = new PdfPCell(p);
					p = new Paragraph(exit.getProduct() == null ? "nenhum produto" : exit.getQuantidade() + "x " + exit.getProduct().getName() + " > " + exit.getProduct().getDesc());
					p.setFont(font);
					cell3 = new PdfPCell(p);
					p = new Paragraph(String.valueOf("R$ "+API.formatValue(exit.getValor())));
					p.setFont(font);
					cell4 = new PdfPCell(p);
					p = new Paragraph(String.valueOf("R$ "+API.formatValue(exit.getValorUn())));
					p.setFont(font);
					cell5 = new PdfPCell(p);
					p = new Paragraph(exit.getPagamento());
					p.setFont(font);
					cell6 = new PdfPCell(p);
					p = new Paragraph(exit.getDesc());
					p.setFont(font);
					cell7 = new PdfPCell(new Paragraph(exit.getDesc()));
					table.addCell(cell1);
					table.addCell(cell2);
					table.addCell(cell3);
					table.addCell(cell4);
					table.addCell(cell5);
					table.addCell(cell6);
					table.addCell(cell7);
					document.add(table);
				}
				document.close();
				try {
					Desktop.getDesktop().open(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException | DocumentException e) {
				e.printStackTrace();
			}
		}
	}
	
}
