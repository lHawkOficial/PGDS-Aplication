package me.pgds.utils.frames;

import java.awt.Color;

import me.pgds.objects.utils.List;
import me.pgds.objects.utils.Text;
import me.pgds.objects.utils.TextButton;
import me.pgds.utils.API;
import me.pgds.utils.WindowCore;
import me.pgds.utils.frames.main.Frame;

public class EntryFrame extends Frame {

	public EntryFrame(int buttonSelected) {
		super(()->{
			
			WindowCore core = WindowCore.getFrame();
			
			TextButton data = new TextButton(209, 2, new Text("data", core.getColorBackground().darker(), Color.gray, 250, 30, 0, 0, 17, false, true), 
			new Text(API.getData(), core.getColorBackground(), Color.white, 250, 30, 0, 0, 17, true, false), null, null);
			
			TextButton valor = new TextButton(209, 2, new Text("valor", core.getColorBackground().darker(), Color.gray, 250, 30, 0, 0, 17, false, true), 
			new Text(null, core.getColorBackground(), Color.white, 250, 30, 0, 0, 17, true, false), null, null);
			
			TextButton pagamento = new TextButton(209, 2,new Text("pagamento", core.getColorBackground().darker(), Color.gray, 250, 30, 0, 0, 17, false, true), 
			new Text(null, core.getColorBackground(), Color.white, 250, 30, 0, 0, 17, true, false), null, null);
			
			TextButton desc = new TextButton(209, 2,new Text("observações", core.getColorBackground().darker(), Color.gray, 250, 30, 0, 0, 17, false, true), 
			new Text(null, core.getColorBackground(), Color.white, 250, 120, 0, 0, 17, true, false), null, null);
			
			List list = new List(0, 209, 2);
			list.addAll(data.getComponents());
			list.addAll(valor.getComponents());
			list.addAll(pagamento.getComponents());
			list.addAll(desc.getComponents());
			list.build();
			
		}, buttonSelected);
	}

}
