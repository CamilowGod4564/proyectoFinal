package presentation;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import domain.Player;

public class BattlePanel extends Panel {

	private JPanel panelDerecho;
	protected JPanel panelIzquierdo;
	protected CardLayout layoutMovimientos;
	private JButton attack;
	private JButton changePokemon;
	private JButton openBag;
	private JButton run;
	private JPanel texto;
	private JLabel msg;
	private JPanel movimientos;
	private JPanel botonesMovimientos;
	private JLabel nombrePokemonJugador;
	private JLabel nombrePokemonEnemigo;
	private JPanel parteSuperior;
	private JPanel parteInferior;
	private JPanel panelVidaEnemigo;
	private JPanel panelPokemonEnemigo;
	private JPanel panelPokemonAtacando;
	private JPanel panelVidaAtacando;
	private ImageIcon enemyIcon;
	private JLabel enemyLabel;
	private ImageIcon attackingIcon;
	private JLabel attackingLabel;
	private HealthBar enemyHealth;
	private HealthBar playerHealth;
	private JLabel estadoJugador;
	private JLabel estadoEnemigo;
	private JLabel vidaPokemonEnemigo;
	private JLabel vidaPokemonAtacando;
	private Player jugadorActual;
	private Player jugadorEnemigo;
	
	public BattlePanel(PoobkemonGUIProvisional gui, Panel prevPanel, Panel nextPanel, String backgroundImage) {
		super(gui, prevPanel, nextPanel, backgroundImage);
	}
	
	@Override
	public void Ready() {
		 prepareElements();
		 prepareActions();
	}
	
	@Override
	public void prepareElements() { 
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		parteSuperior = new JPanel(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.5; 
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.NORTH;

		add(parteSuperior, gbc);

		parteInferior = new JPanel(new GridBagLayout());
		gbc.gridy = 1;
		gbc.weighty = 0.5; 
		gbc.fill = GridBagConstraints.BOTH;

		add(parteInferior, gbc);
		
		parteSuperior.setOpaque(false);
		parteInferior.setOpaque(false);
		
		GridBagConstraints gbcSup = new GridBagConstraints();
		
		panelVidaEnemigo = new JPanel(null);
		gbcSup.gridx = 0;
		gbcSup.gridy = 0;
		gbcSup.weightx = 0.5;
		gbcSup.weighty = 0.5;
		gbcSup.fill = GridBagConstraints.BOTH;
		parteSuperior.add(panelVidaEnemigo, gbcSup);
		
		
		panelPokemonEnemigo = new JPanel(); 
		gbcSup.gridx = 1;
		gbcSup.gridy = 0;
		gbcSup.weightx = 0.5;
		gbcSup.weighty = 0.8;
		gbcSup.fill = GridBagConstraints.BOTH;
	    parteSuperior.add(panelPokemonEnemigo,gbcSup);
		
		panelPokemonAtacando = new JPanel(); 
		gbcSup.gridx = 0;
		gbcSup.gridy = 1;
		gbcSup.weightx = 0.5;
		gbcSup.weighty = 0.5;
		gbcSup.fill = GridBagConstraints.BOTH;
	    parteSuperior.add(panelPokemonAtacando,gbcSup);
	    
		panelVidaAtacando = new JPanel(null); 
		gbcSup.gridx = 1;
		gbcSup.gridy = 1;
		gbcSup.weightx = 0.5;
		gbcSup.weighty = 0.2; 
		gbcSup.fill = GridBagConstraints.BOTH;
	    parteSuperior.add(panelVidaAtacando,gbcSup);
	    
		panelPokemonEnemigo.setOpaque(false);
		panelPokemonAtacando.setOpaque(false);
		panelVidaAtacando.setOpaque(false);
		panelVidaEnemigo.setOpaque(false);
		
		panelPokemonEnemigo.setLayout(null);
		panelPokemonAtacando.setLayout(null);
		
		//IMAGEN POKEMONS
		
		enemyIcon = new ImageIcon(getClass().getResource("/presentation/recursos/frame2/"+PoobkemonGUIProvisional.juego.getOtherPokemonPokedex()+".png"));
        enemyLabel = new JLabel(escalarIcono(enemyIcon,150,150));
        enemyLabel.setBounds(70, 20, 250, 250);
        panelPokemonEnemigo.add(enemyLabel); 
        
	    
	    
	    attackingIcon = new ImageIcon(getClass().getResource("/presentation/recursos/frame2/"+PoobkemonGUIProvisional.juego.getCurrentPokemonPokedex()+".png"));
        attackingLabel = new JLabel(escalarIcono(attackingIcon,150,150));
        attackingLabel.setBounds(70, -50, 250, 250);
        panelPokemonAtacando.add(attackingLabel); 
        
        //VIDA POKEMONS
        enemyHealth = new HealthBar(PoobkemonGUIProvisional.juego.getOtherPokemonTotalHealth());
        enemyHealth.setBounds(55,86, 230, 7);
        panelVidaEnemigo.add(enemyHealth);

        playerHealth = new HealthBar(PoobkemonGUIProvisional.juego.getCurrentPokemonTotalHealth());
        playerHealth.setBounds(56, 72, 230, 7);
        panelVidaAtacando.add(playerHealth);
        
        //NOMBRE POKEMONS
        
        vidaPokemonEnemigo = new JLabel(Integer.toString(PoobkemonGUIProvisional.juego.getOtherPokemonCurrentHealth())+"/"+
        		Integer.toString(PoobkemonGUIProvisional.juego.getOtherPokemonTotalHealth()));
        
        vidaPokemonEnemigo.setFont(new Font("Arial", Font.BOLD, 15));
        vidaPokemonEnemigo.setBounds(215,50, 230, 40);
        panelVidaEnemigo.add(vidaPokemonEnemigo);
        
        nombrePokemonEnemigo = new JLabel(PoobkemonGUIProvisional.juego.getOtherPokemon());
        nombrePokemonEnemigo.setFont(new Font("Arial", Font.BOLD, 20));
        nombrePokemonEnemigo.setBounds(55,50, 230, 40);
        panelVidaEnemigo.add(nombrePokemonEnemigo);
        
        vidaPokemonAtacando = new JLabel(Integer.toString(PoobkemonGUIProvisional.juego.getCurrentPokemonHealth())+"/"+
        		Integer.toString(PoobkemonGUIProvisional.juego.getCurrentPokemonTotalHealth()));
        vidaPokemonAtacando.setFont(new Font("Arial", Font.BOLD, 15));
        vidaPokemonAtacando.setBounds(220,35, 230, 40);
        panelVidaAtacando.add(vidaPokemonAtacando);
        
        nombrePokemonJugador = new JLabel(PoobkemonGUIProvisional.juego.getCurrentPokemon());
        nombrePokemonJugador.setFont(new Font("Arial", Font.BOLD, 20));
        nombrePokemonJugador.setBounds(55,35, 230, 40);
        panelVidaAtacando.add(nombrePokemonJugador);
        
        estadoJugador = new JLabel(formatearEstados(PoobkemonGUIProvisional.juego.getCurrentPokemonStatus()));
        estadoJugador.setFont(new Font("Arial", Font.PLAIN, 14));
        estadoJugador.setBounds(55, 95, 230, 20);
        panelVidaAtacando.add(estadoJugador);
        
        estadoEnemigo = new JLabel(formatearEstados(PoobkemonGUIProvisional.juego.getOtherPokemonStatus()));
        estadoEnemigo.setFont(new Font("Arial", Font.PLAIN, 14));
        estadoEnemigo.setBounds(55, 110, 230, 20);
        panelVidaEnemigo.add(estadoEnemigo);
        
        //PANEL INFERIOR
        
        GridBagConstraints gbcInf = new GridBagConstraints();
        gbcInf.gridx = 0;
        gbcInf.gridy = 0;
        gbcInf.weightx = 0.65; 
        gbcInf.weighty = 1.0;
        gbcInf.fill = GridBagConstraints.BOTH;
        panelIzquierdo = new JPanel(new CardLayout());
        parteInferior.add(panelIzquierdo, gbcInf);

        gbcInf.gridx = 1;
        gbcInf.weightx = 0.6; 
        panelDerecho = new JPanel(new GridLayout(2, 2));
        parteInferior.add(panelDerecho, gbcInf);
        
        panelDerecho.setOpaque(false);
        panelIzquierdo.setOpaque(false);
        

        attack = new JButton();
        changePokemon = new JButton();
        openBag = new JButton();
        run = new JButton();
             
        attack.setContentAreaFilled(false); 
        attack.setBorderPainted(false);    
        attack.setBorder(null);     
        
        openBag.setContentAreaFilled(false); 
        openBag.setBorderPainted(false);    
        openBag.setBorder(null);    

        changePokemon.setContentAreaFilled(false); 
        changePokemon.setBorderPainted(false);    
        changePokemon.setBorder(null);   

        run.setContentAreaFilled(false); 
        run.setBorderPainted(false);    
        run.setBorder(null);    
        
        
        panelDerecho.add(attack);
        panelDerecho.add(openBag);
        panelDerecho.add(changePokemon);
        panelDerecho.add(run);
        
        //PANEL IZQUIERDA
        
        layoutMovimientos = (CardLayout) panelIzquierdo.getLayout();
        
        //TEXTO
        texto = new JPanel(null);
        texto.setOpaque(false);
        msg = new JLabel("Que hará "+PoobkemonGUIProvisional.juego.getCurrentPokemon()+" ?");
        msg.setFont(new Font("Arial", Font.BOLD, 20));
        msg.setBounds(35,30, 260, 40);
        texto.add(msg);
        
        panelIzquierdo.add(texto, "TEXTO");
        
        //MOVIMIENTOS
        movimientos = new JPanel(null);
        botonesMovimientos = new JPanel(new GridLayout(2, 2));
        botonesMovimientos.setBounds(25, 20, 320, 84);
        botonesMovimientos.setOpaque(true);
        
        MovementButtons.actualizarNombresDeBotones(PoobkemonGUIProvisional.juego.getCurrentPokemonMovements().get(0),
        		PoobkemonGUIProvisional.juego.getCurrentPokemonMovements().get(1),
        		PoobkemonGUIProvisional.juego.getCurrentPokemonMovements().get(2),
        		PoobkemonGUIProvisional.juego.getCurrentPokemonMovements().get(3));
        
        for(JButton b : MovementButtons.getButtons()) {
        	botonesMovimientos.add(b);
        }
        
        movimientos.add(botonesMovimientos);
        
        movimientos.setOpaque(false);
        panelIzquierdo.add(movimientos, "MOVIMIENTOS");
        msg.setText("¿Qué hará " + PoobkemonGUIProvisional.juego.getCurrentPokemon() + "?");
        
        layoutMovimientos.show(panelIzquierdo, "TEXTO");
        
        jugadorActual = PoobkemonGUIProvisional.juego.getPlayingPlayer();
        jugadorEnemigo= PoobkemonGUIProvisional.juego.getWaitingPlayer();
      
	}

	@Override
	public void prepareActions() {
		
		setupKeyBindings();
		
		attack.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            layoutMovimientos.show(panelIzquierdo, "MOVIMIENTOS"); //se podria usar layoutMovimientos para mostrar que se uso x mov
	        }
	    });
		for(JButton b : MovementButtons.getButtons()) {
            b.addActionListener(e -> {
                handleMovement((JButton) e.getSource());
                PoobkemonGUIProvisional.juego.nextTurn();
                layoutMovimientos.show(panelIzquierdo, "TEXTO");
                refresh();
            });
        }

		openBag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            BagDialog dialog = new BagDialog((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource()), 
	            		PoobkemonGUIProvisional.juego.getPlayerItems(),
	            		BattlePanel.this);
	            dialog.setVisible(true); 
			}
		});
				
		//changePokemon
		changePokemon.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	PokemonsDialog dialog = new PokemonsDialog((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource()), 
	            		PoobkemonGUIProvisional.juego.getPlayerPokemonsNames(),
	            		PoobkemonGUIProvisional.juego.getPlayerPokemonsIds(),
	            		PoobkemonGUIProvisional.juego.getPlayerPokemons(),
	            		BattlePanel.this);
	            dialog.setVisible(true); 
	        }
	    });
		
		run.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	PoobkemonGUIProvisional.juego.surrender();
	        	nextPanel.Ready();
	        	gui.changePanel(PoobkemonGUIProvisional.WINNER_PANEL);
	        }
	    });
	}
	
	@Override
	public void refresh() {
		
		//UPDATE PLAYERS
		jugadorActual = PoobkemonGUIProvisional.juego.getPlayingPlayer();
        jugadorEnemigo= PoobkemonGUIProvisional.juego.getWaitingPlayer();
		
	    // Update names
	    nombrePokemonJugador.setText(PoobkemonGUIProvisional.juego.getCurrentPokemon());
	    nombrePokemonEnemigo.setText(PoobkemonGUIProvisional.juego.getOtherPokemon());

	    // Update health text 
	    vidaPokemonAtacando.setText(PoobkemonGUIProvisional.juego.getCurrentPokemonHealth() + "/" +
	                                PoobkemonGUIProvisional.juego.getCurrentPokemonTotalHealth());
	    
	    vidaPokemonEnemigo.setText(PoobkemonGUIProvisional.juego.getOtherPokemonCurrentHealth() + "/" +
	                               PoobkemonGUIProvisional.juego.getOtherPokemonTotalHealth());

	    // Update health bars
	    playerHealth.setHP(PoobkemonGUIProvisional.juego.getCurrentPokemonHealth());
	    enemyHealth.setHP(PoobkemonGUIProvisional.juego.getOtherPokemonCurrentHealth());

	    // Update Pokémon images
	    attackingIcon = new ImageIcon(getClass().getResource("/presentation/recursos/frame2/" +
	            PoobkemonGUIProvisional.juego.getCurrentPokemonPokedex() + ".png"));
	    enemyIcon = new ImageIcon(getClass().getResource("/presentation/recursos/frame2/" +
	            PoobkemonGUIProvisional.juego.getOtherPokemonPokedex() + ".png"));

	    attackingLabel.setIcon(escalarIcono(attackingIcon, 150, 150));
	    enemyLabel.setIcon(escalarIcono(enemyIcon, 150, 150));

	    // Update states
	    estadoJugador.setText(formatearEstados(PoobkemonGUIProvisional.juego.getCurrentPokemonStatus()));
	    estadoEnemigo.setText(formatearEstados(PoobkemonGUIProvisional.juego.getOtherPokemonStatus()));

	    // Update moves
	    MovementButtons.actualizarNombresDeBotones(
	        PoobkemonGUIProvisional.juego.getCurrentPokemonMovements().get(0),
	        PoobkemonGUIProvisional.juego.getCurrentPokemonMovements().get(1),
	        PoobkemonGUIProvisional.juego.getCurrentPokemonMovements().get(2),
	        PoobkemonGUIProvisional.juego.getCurrentPokemonMovements().get(3)
	    );

	    msg.setText("¿Qué hará " + PoobkemonGUIProvisional.juego.getCurrentPokemon() + "?");
	    revalidate();
	    repaint();
	    endBattle();
	}

	private void handleMovement(JButton button) {
		PoobkemonGUIProvisional.juego.attack(button.getText());
    }
	
	private static ImageIcon escalarIcono(ImageIcon icon, int ancho, int alto) {
        Image imagenOriginal = icon.getImage();
        Image imagenEscalada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenEscalada);
    }	
	
	public void endBattle() {
		if (PoobkemonGUIProvisional.juego.getWinner() != null) {
			PoobkemonGUIProvisional.juego.surrender();
        	nextPanel.Ready();
        	gui.changePanel(PoobkemonGUIProvisional.WINNER_PANEL);
		}
	}
	
	private String formatearEstados(ArrayList<String> estados) {
	    if (estados == null || estados.isEmpty()) return "Estados: Ninguno!";
	    return "Estados: " + String.join(", ", estados);
	}	
}

	
	

