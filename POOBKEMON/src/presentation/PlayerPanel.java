package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerPanel extends Panel {

	private JTextField primerJugadorNameField ;
    private JTextField segundoJugadorNameField;
    private JButton player1ImageButton;
    private JButton player2ImageButton;
    private JButton volverButton;
    private JButton continuarButton;
    private ImageIcon[] trainerIcons;
    private int player1SelectedIcon = 0;
    private int player2SelectedIcon = 1;
	private JPanel jugadores;
	private JPanel opciones;
	private JPanel primerJugador;
	private JPanel segundoJugador;
	private JPanel primerIzq;
	private JPanel primerDer;
	private JPanel segundoIzq;
	private JPanel segundoDer;
	private JLabel msg;
	private JLabel primerMsg;
	private JLabel segundoMsg;
	private JButton botonContinuar;
	private JButton botonVolver;


    public PlayerPanel(PoobkemonGUIProvisional gui, Panel prevPanel, Panel nextPanel, String backgroundImage) {
		super(gui, prevPanel, nextPanel, backgroundImage);
	}
    

    public void prepareElements() {
    	
    	setLayout(new GridBagLayout());
    	GridBagConstraints gbcPrincipal = new GridBagConstraints();

		jugadores = new JPanel(new GridBagLayout());
		gbcPrincipal.gridx = 0;
		gbcPrincipal.gridy = 0;
		gbcPrincipal.weightx = 1.0;
		gbcPrincipal.weighty = 0.8; 
		gbcPrincipal.fill = GridBagConstraints.BOTH;
		gbcPrincipal.anchor = GridBagConstraints.NORTH;

		add(jugadores, gbcPrincipal);

		opciones = new JPanel(null);
		gbcPrincipal.gridy = 1;
		gbcPrincipal.weighty = 0.2; 

		add(opciones, gbcPrincipal);
		
		jugadores.setOpaque(false);
		opciones.setOpaque(false);
        
        //JUGADORES
		
		GridBagConstraints gbcJugadores = new GridBagConstraints();
		
		primerJugador = new JPanel(new GridBagLayout());
		gbcJugadores.gridx = 0;
		gbcJugadores.gridy = 0;
		gbcJugadores.weightx = 1.0;
		gbcJugadores.weighty = 0.5; 
		gbcJugadores.fill = GridBagConstraints.BOTH;
		gbcJugadores.anchor = GridBagConstraints.NORTH;
		
		jugadores.add(primerJugador,gbcJugadores);
		
		segundoJugador = new JPanel(new GridBagLayout());
		gbcJugadores.gridy = 1;
		
		jugadores.add(segundoJugador,gbcJugadores);
		
		primerJugador.setOpaque(false);
		segundoJugador.setOpaque(false);
		
		//JUGADOR 1
		GridBagConstraints gbcPrimerJugador = new GridBagConstraints();
		
		gbcPrimerJugador.gridx = 0;
		gbcPrimerJugador.gridy = 0;
		gbcPrimerJugador.weightx = 0.5;
		gbcPrimerJugador.weighty = 1.0; 
		gbcPrimerJugador.fill = GridBagConstraints.BOTH;
		gbcPrimerJugador.anchor = GridBagConstraints.WEST;
		primerIzq = new JPanel(new GridLayout(2,1));
		primerJugador.add(primerIzq,gbcPrimerJugador);
		
		
		gbcPrimerJugador.gridx = 1;
		primerDer = new JPanel();
		primerJugador.add(primerDer,gbcPrimerJugador);
		
		primerDer.setOpaque(false);
		primerIzq.setOpaque(false);
		
		//JUGADOR 2
		
		GridBagConstraints gbcSegundoJugador = new GridBagConstraints();
		
		gbcSegundoJugador.gridx = 0;
		gbcSegundoJugador.gridy = 0;
		gbcSegundoJugador.weightx = 0.5;
		gbcSegundoJugador.weighty = 1.0; 
		gbcSegundoJugador.fill = GridBagConstraints.BOTH;
		gbcSegundoJugador.anchor = GridBagConstraints.WEST;
		segundoIzq = new JPanel();
		segundoJugador.add(segundoIzq,gbcSegundoJugador);
		
		
		gbcSegundoJugador.gridx = 1;
		segundoDer = new JPanel(new GridLayout(2,1));
		segundoJugador.add(segundoDer,gbcSegundoJugador);
		
		segundoIzq.setOpaque(false);
		segundoDer.setOpaque(false);
		
		//BARRAS PARA ESCOGER NOMBRE
		
		primerMsg = new JLabel("JUGADOR 1 - CUAL ES TU NOMBRE?");
		segundoMsg = new JLabel("JUGADOR 2 - CUAL ES TU NOMBRE?");
		
		primerJugadorNameField = new JTextField();
		primerJugadorNameField.setFont(new Font("Arial", Font.PLAIN, 14));
        
		segundoJugadorNameField = new JTextField();
		segundoJugadorNameField.setFont(new Font("Arial", Font.PLAIN, 14));
		
		primerIzq.setAlignmentX(Component.CENTER_ALIGNMENT);
		primerMsg.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel primerCampoWrap = new JPanel(new FlowLayout(FlowLayout.CENTER));
		primerCampoWrap.setOpaque(false);
		primerJugadorNameField.setPreferredSize(new Dimension(200, 30));
		primerCampoWrap.add(primerJugadorNameField);

		primerIzq.add(primerMsg);
		primerIzq.add(primerCampoWrap);

		segundoDer.setAlignmentX(Component.CENTER_ALIGNMENT);
		segundoMsg.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel segundoCampoWrap = new JPanel(new FlowLayout(FlowLayout.CENTER));
		segundoCampoWrap.setOpaque(false);
		segundoJugadorNameField.setPreferredSize(new Dimension(200, 30));
		segundoCampoWrap.add(segundoJugadorNameField);

		segundoDer.add(segundoMsg);
		segundoDer.add(segundoCampoWrap);
		
		botonContinuar = new JButton("CONTINUAR");
		botonVolver = new JButton("VOLVER");

		botonContinuar.setBounds(630, 0, 110, 30);
		botonVolver.setBounds(25, 0, 110, 30);
		
		opciones.add(botonContinuar);
		opciones.add(botonVolver);
		
    }

    public void prepareActions() {
    	setupKeyBindings();
    	
    	botonContinuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	nextPanel.removeAll();
            	PoobkemonGUIProvisional.juego.newPlayer(getPlayer1Name());
            	PoobkemonGUIProvisional.juego.newPlayer(getPlayer2Name());
            	
            	if(prevPanel.getSurvival()) {
            		PoobkemonGUIProvisional.juego.confirmPlayers();
            		PoobkemonGUIProvisional.juego.prepareTeams();
            		nextPanel.getNextPanel().getNextPanel().Ready();
            		gui.changePanel(PoobkemonGUIProvisional.BATTLE_PANEL);
            	}else {
            		nextPanel.Ready();
            		gui.changePanel(PoobkemonGUIProvisional.POKEDEX_PANEL);
            	}
            }
        });
		
        botonVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	gui.changePanel(PoobkemonGUIProvisional.SELECT_PANEL); 
            }
        });
    }

    @Override
    public String getPlayer1Name() {
    	return primerJugadorNameField.getText().trim().isEmpty() ? "P1" : primerJugadorNameField.getText().trim(); 
    }
    
    @Override
    public String getPlayer2Name() {
    	return segundoJugadorNameField.getText().trim().isEmpty() ? "P2" : segundoJugadorNameField.getText().trim(); 
    }
    
    public int getPlayer1SelectedIcon() { 
    	return player1SelectedIcon; 
    }
    
    public int getPlayer2SelectedIcon() { 
    	return player2SelectedIcon; 
    }
    
    public boolean getMaquina() {
		return prevPanel.getMaquina();
	}
    
    public boolean getJugador() {
		return prevPanel.getJugador();
	}
    
    public boolean getSurvival() {
		return prevPanel.getSurvival();
	}
    
    private ImageIcon imagenEscalada(String ruta,int anchoN,int anchoD,int altoN,int altoD){
        Image jugadorVsjugador = new ImageIcon(getClass().getResource(ruta)).getImage();
        Image jugadorVsjugadorEscalada = jugadorVsjugador.getScaledInstance((getWidth()*anchoN)/anchoD, (getHeight()*altoN)/altoD, Image.SCALE_SMOOTH);
        return new ImageIcon(jugadorVsjugadorEscalada);
    }
}
