package presentation;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PlayerPanel extends Panel{


	public PlayerPanel(PoobkemonGUIProvisional gui, Panel prevPanel, Panel nextPanel, String backgroundImage) {
		super(gui, prevPanel, nextPanel, backgroundImage);
		SwingUtilities.invokeLater(() -> {
		    prepareElements();
		    prepareActions();
		});
	}

	@Override
	public void prepareElements() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void prepareActions() {
		// TODO Auto-generated method stub
		
	}

}
