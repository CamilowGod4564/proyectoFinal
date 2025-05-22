package presentation;

import java.awt.*;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.*;

public class ItemsPanel extends Panel{
	
	private Set<String> numeroItems;

	public ItemsPanel(PoobkemonGUIProvisional gui, Panel prevPanel, Panel nextPanel, String backgroundImage) {
		super(gui, prevPanel, nextPanel, backgroundImage);
		SwingUtilities.invokeLater(() -> {

		    prepareElements();
		    prepareActions();
		});
	}

	 @Override
	    public void prepareElements() {
	        setLayout(new BorderLayout());

	        JPanel topContainer = new JPanel(new BorderLayout());
	        topContainer.setPreferredSize(new Dimension(0, 200));
	        
	        numeroItems = PoobkemonGUIProvisional.juego.getItems();
	        

	        JPanel items = new JPanel(new GridLayout(numeroItems.size(),1));
	        
	        for (String i : numeroItems) {
	            items.add(new JButton());
	        }
	        
	        
	          
	        JScrollPane panelDeScroll = new JScrollPane(items);
	        panelDeScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	        panelDeScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        panelDeScroll.getVerticalScrollBar().setUnitIncrement(16);
	        
	        topContainer.add(panelDeScroll, BorderLayout.CENTER);
	        add(topContainer, BorderLayout.CENTER);

	        // Panel inferior
	        JPanel continuar = new JPanel();
	        continuar.setPreferredSize(new Dimension(0, getWidth()/4));
	        
	        

	        add(continuar, BorderLayout.SOUTH);
	    }

	@Override
	public void prepareActions() {
		
		
	}

}
