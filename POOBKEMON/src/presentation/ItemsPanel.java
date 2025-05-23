package presentation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class ItemsPanel extends Panel{
	
	private Set<String> numeroItems;
	private JButton botonContinuar;
	private Set<JSpinner> cantidadesJugador1;
	private Set<JSpinner> cantidadesJugador2;

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
	        topContainer.setOpaque(false);
	        topContainer.setPreferredSize(new Dimension(0, 200));
	        
	        numeroItems = PoobkemonGUIProvisional.juego.getItems();
	        
	        
	        cantidadesJugador1 = new HashSet<JSpinner>();
	        
	        cantidadesJugador2 = new HashSet<JSpinner>();

	        
	        
	        JPanel items = new JPanel(new GridLayout(numeroItems.size(),1));
	        items.setOpaque(false);
	        
	        
	        for (String i : numeroItems) {
	        	
	        	
	        	JPanel item = new JPanel(new GridLayout(0,2));
	        	item.setOpaque(false);
	        	JLabel titulo = new JLabel(i);
	        	titulo.setOpaque(false);
	        	titulo.setHorizontalAlignment(SwingConstants.CENTER);
	        	titulo.setVerticalAlignment(SwingConstants.CENTER);
	        	item.add(titulo);
	        	
	        	SpinnerNumberModel modeloEntero1 = new SpinnerNumberModel(0, 0, 100, 1);
	        	SpinnerNumberModel modeloEntero2 = new SpinnerNumberModel(0, 0, 100, 1);
	            JSpinner spinner1 = new JSpinner(modeloEntero1);
	            JSpinner spinner2 = new JSpinner(modeloEntero2);
	           
	            
	            JPanel panelitem = new JPanel(new GridLayout(0,2));
	            
	            cantidadesJugador1.add(spinner1);
	            cantidadesJugador2.add(spinner2);
	            
	            panelitem.add(spinner1);
	            panelitem.add(spinner2);
	            
	            
	            item.add(panelitem);
	            
	            items.add(item);
	        }
	        
	        
	          
	        
	        
	        topContainer.add(items, BorderLayout.CENTER);
	        add(topContainer, BorderLayout.CENTER);

	        // Panel inferior
	        JPanel continuar = new JPanel();
	        
	        botonContinuar = new JButton("BATALLA");
 
	        botonContinuar.setBorder(new EmptyBorder(20, 20, 20, 20));
	        continuar.setPreferredSize(new Dimension(0, getHeight()/4));
	        
	        

	        add(botonContinuar, BorderLayout.SOUTH);
	    }

	@Override
	public void prepareActions() {
		botonContinuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int j = 0;
            	List<String> lista = new ArrayList<>(numeroItems);
	
            	for (JSpinner a : cantidadesJugador1) {
            		Object valor = a.getValue();
                    int valorInt = (int) valor;
                    for (int i = 0; i < valorInt; i++) {
                    	PoobkemonGUIProvisional.juego.playerSelectItem("p1",lista.get(j));
                    }     	
                    
            	}
            	int k = 0;
            	for (JSpinner a : cantidadesJugador2) {
            		Object valor = a.getValue();
                    int valorInt = (int) valor;
                    for (int i = 0; i < valorInt; i++) {
                    	PoobkemonGUIProvisional.juego.playerSelectItem("p1",lista.get(k));
                    }     	
                    
            	}
            	
            	PoobkemonGUIProvisional.juego.confirmPlayers();
            	nextPanel.Ready();
            	gui.changePanel(PoobkemonGUIProvisional.BATTLE_PANEL);
           

            }
        });

		
		
	}
	
	

}
