package presentation;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import domain.PoobkemonGame;

public class WinnerPanel extends Panel{

	private JButton boton2;
	private JButton boton1;
	private JPanel panelBotones;
	private JLabel mensajeFelicidades;
	private JLabel ganadorTexto;



	public WinnerPanel(PoobkemonGUIProvisional gui, Panel prevPanel, Panel nextPanel, String backgroundImage) {
		super(gui, prevPanel, nextPanel, backgroundImage);
		
	}

	@Override
	public void prepareElements() {
		 setLayout(new GridBagLayout());
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.insets = new Insets(5, 5, 5, 5); 


	        gbc.gridx = 0;
	        gbc.anchor = GridBagConstraints.CENTER;

	        gbc.gridy = 0;
	        mensajeFelicidades = new JLabel("FELICIDADES!!!");
	        add(mensajeFelicidades, gbc);
	        mensajeFelicidades.setFont(new Font("Arial", Font.BOLD, 70));

	        gbc.gridy = 1;
	        ganadorTexto = new JLabel(PoobkemonGUIProvisional.juego.getWinner()+" GANASTE :D");
	        mensajeFelicidades.setFont(new Font("Arial", Font.BOLD, 30));
	        add(ganadorTexto, gbc);

	        panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0)); 
	        boton1 = new JButton(" ??? ");
	        boton2 = new JButton("Salir");
	        panelBotones.add(boton1);
	        panelBotones.add(boton2);
	        panelBotones.setOpaque(false);

	        gbc.gridy = 2;
	        gbc.insets = new Insets(25, 5, 5, 5); 
	        add(panelBotones, gbc);
	      
	}
		
	

	@Override
	public void prepareActions() {
		boton2.addActionListener(e -> {
            Window ventanaPadre = SwingUtilities.getWindowAncestor(WinnerPanel.this);
                ventanaPadre.dispose();  
        });
	}
}
