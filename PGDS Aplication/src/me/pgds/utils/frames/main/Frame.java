package me.pgds.utils.frames.main;



import lombok.Getter;
import me.pgds.objects.managers.Manager;
import me.pgds.objects.utils.Button;
import me.pgds.utils.API;
import me.pgds.utils.WindowCore;

@Getter
public class Frame implements FrameImplements {

	private Runnable runnable;
	private boolean waiting_process = false;
	private Thread thread;
	private Button buttonSelected;
	private int id;
	
	public Frame(Runnable runnable, int buttonSelected) {
		this.runnable = runnable;
		this.id = buttonSelected;
		Manager.get().getFrames().add(this);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Frame run() {
		WindowCore core = WindowCore.getFrame();
		if (core.getSelected()!=null) core.getSelected().getThread().stop();
		core.setSelected(this);
		waiting_process = true;
		thread = new Thread(()-> {
			core.clear();
			core.defaultIcons();
			API.updateOrganizationVariablesManager();
			if (core.getTimer() != null) {
				core.getTimer().cancel();
				core.setTimer(null);
			}
			try {
				if (id >= 0 && id < core.getButtons().size()) {
					buttonSelected = core.getButtons().get(id);
					buttonSelected.select();
				}
				core.update();
				runnable.run();
				waiting_process = false;
			} catch (Exception e) {
				System.exit(1);
			}
		});
		thread.start();
		
		new Thread(()->{
			while(true) {
				if (core.getSelected().isWaiting_process()) core.setTitle("ATUALIZANDO O CONTEÚDO DA PAGINA, AGUARDE...");
				else {
					core.setTitle("PEGADAS PRÉ-MOLDADOS " + core.getVersion());
					break;
				}
			}
		}).start();
		
		return this;
	}
	
}
