package presentation;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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
	
	public BattlePanel(PoobkemonGUIProvisional gui, Panel prevPanel, Panel nextPanel, String backgroundImage) {
		super(gui, prevPanel, nextPanel, backgroundImage);
		SwingUtilities.invokeLater(() -> {
		    prepareElements();
		    prepareActions();
		});
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
		
		enemyIcon = new ImageIcon(getClass().getResource("/presentation/recursos/frame2/0009.png"));
        enemyLabel = new JLabel(escalarIcono(enemyIcon,150,150));
        enemyLabel.setBounds(70, 20, 250, 250);
        panelPokemonEnemigo.add(enemyLabel); 
        
	    setupKeyBindings();
	    
	    attackingIcon = new ImageIcon(getClass().getResource("/presentation/recursos/frame2/0001.png"));
        attackingLabel = new JLabel(escalarIcono(attackingIcon,150,150));
        attackingLabel.setBounds(70, -50, 250, 250);
        panelPokemonAtacando.add(attackingLabel); 
        
        //VIDA POKEMONS
        enemyHealth = new HealthBar(100);
        enemyHealth.setBounds(55,86, 230, 7);
        panelVidaEnemigo.add(enemyHealth);

        playerHealth = new HealthBar(100);
        playerHealth.setBounds(56, 72, 230, 7);
        panelVidaAtacando.add(playerHealth);
        
        //NOMBRE POKEMONS
        
        nombrePokemonEnemigo = new JLabel("Charizard");
        nombrePokemonEnemigo.setFont(new Font("Arial", Font.BOLD, 20));
        nombrePokemonEnemigo.setBounds(55,50, 230, 40);
        panelVidaEnemigo.add(nombrePokemonEnemigo);
        
        nombrePokemonJugador = new JLabel("Pikachu");
        nombrePokemonJugador.setFont(new Font("Arial", Font.BOLD, 20));
        nombrePokemonJugador.setBounds(55,35, 230, 40);
        panelVidaAtacando.add(nombrePokemonJugador);
        
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
        msg = new JLabel("Que hará "+"Bulbasaur"+" ?");
        msg.setFont(new Font("Arial", Font.BOLD, 20));
        msg.setBounds(35,30, 230, 40);
        texto.add(msg);
        
        panelIzquierdo.add(texto, "TEXTO");
        
        //MOVIMIENTOS
        movimientos = new JPanel(null);
        botonesMovimientos = new JPanel(new GridLayout(2, 2));
        botonesMovimientos.setBounds(25, 20, 320, 84);
        botonesMovimientos.setOpaque(true);
        
        for(JButton b : MovementButtons.getButtons()) {
        	botonesMovimientos.add(b);
        }
        
        movimientos.add(botonesMovimientos);
        
        movimientos.setOpaque(false);
        panelIzquierdo.add(movimientos, "MOVIMIENTOS");
        
        
        layoutMovimientos.show(panelIzquierdo, "TEXTO");
      
	}

	@Override
	public void prepareActions() {
		attack.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            layoutMovimientos.show(panelIzquierdo, "MOVIMIENTOS"); 
	        }
	    });
		
		
	}
	
	public static ImageIcon escalarIcono(ImageIcon icon, int ancho, int alto) {
        Image imagenOriginal = icon.getImage();
        Image imagenEscalada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenEscalada);
    }
	
	
}

	
	

