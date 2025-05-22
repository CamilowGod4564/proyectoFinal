package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class PokedexPanel extends Panel{

	private JLabel pokemonGif;
	private JButton eliminarPokemones;
	private JButton butonContinue;
	private JButton butonBack;
	private JButton agregarPlayer1;
	private JButton agregarPlayer2;
	private int posicionPokemon;
	private Object numeroPokemon;
	private String rutaPokemon;
	private JButton pokedexSiguiente;
	private JButton pokedexAnterior;
	private JPanel pokemonesJugador1;
	private JPanel pokemonesJugador2;
	private boolean jugador;
	private boolean maquina;

	
	public PokedexPanel(PoobkemonGUIProvisional gui, Panel prevPanel, Panel nextPanel, String backgroundImage) {
		
		super(gui, prevPanel, nextPanel, backgroundImage);
		SwingUtilities.invokeLater(() -> {
		    prepareElements();
		    prepareActions();
		});
		

	}

	@Override
	public void prepareElements() {
		
			jugador = prevPanel.getJugador();
			maquina = prevPanel.getMaquina();
			
		
	        //DISTRIBUCION DE PANELES
	        setLayout(new GridBagLayout());
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.fill = GridBagConstraints.BOTH;
	        gbc.gridx = 0;

	        //PANEL SUPERIOR
	        gbc.gridy = 0;
	        gbc.weighty = 0.6;
	        gbc.weightx = 1.0;
	        JPanel panelSuperior = new JPanel(new BorderLayout());
	        panelSuperior.setOpaque(false);

	        //PANTALLA DEL POKEMON
	        numeroPokemon = gui.getLlavesPokemones().get(0);
	        rutaPokemon = "/presentation/recursos/animated/"+numeroPokemon+".gif";

	        pokemonGif = new JLabel(new ImageIcon(getClass().getResource(rutaPokemon)));
	        pokemonGif.setBorder(BorderFactory.createEmptyBorder(-28, 0, 0, 0));
	        pokemonGif.setOpaque(false);

	        pokemonGif.setPreferredSize(new Dimension(getWidth()/4,0));

	        JButton prueba = new JButton();
	        prueba.setPreferredSize(new Dimension(getWidth()/4,0));
	        prueba.setBorder(new EmptyBorder(10, 20, 10, 20));
	        

	        //BOTONES POKEMON E INFORMACION
	        JPanel informacionPokemon = new JPanel(new GridLayout(2,0));
	        informacionPokemon.setOpaque(false);

	        //ESTADISTICAS POKEMON
	        JPanel estadisticasPokemon = new JPanel();
	        estadisticasPokemon.setOpaque(false);

	        informacionPokemon.add(estadisticasPokemon);

	        // BOTONES DE CAMBIAR POKEMON

	        pokedexSiguiente = new JButton();
	        botonComoImagen(pokedexSiguiente);

	        pokedexAnterior = new JButton();
	        botonComoImagen(pokedexAnterior);

	        JPanel botonesPokemon = new JPanel(new GridLayout(0,2));
	        botonesPokemon.setOpaque(false);
	        botonesPokemon.add(pokedexAnterior);
	        botonesPokemon.add(pokedexSiguiente);


	        informacionPokemon.add(botonesPokemon);


	        // PARTE DE SELECCIONAR ATAQUES POKEMON
	        String[] movimientosPokemon = gui.juego.getMovements().toArray(new String[0]);
	        

	        JPanel ataquesPokemon = new JPanel(new GridLayout(3,1,0,10));
	        ataquesPokemon.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	        ataquesPokemon.setOpaque(false);

	        JPanel ataquesSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
	        ataquesSuperior.setOpaque(false);
	        ataquesSuperior.add(new JComboBox<>(movimientosPokemon));
	        ataquesSuperior.add(new JComboBox<>(movimientosPokemon));

	        JPanel ataquesInferior = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
	        ataquesInferior.setOpaque(false);
	        ataquesInferior.add(new JComboBox<>(movimientosPokemon));
	        ataquesInferior.add(new JComboBox<>(movimientosPokemon));
	        ataquesPokemon.add(ataquesSuperior);
	        ataquesPokemon.add(ataquesInferior);
	        
	        agregarPlayer1 = new JButton("agregar a P1");
            agregarPlayer2 = new JButton("agregar a P2");
	        

	        if(jugador && !maquina ){
	            JPanel agregarPokemon = new JPanel(new GridLayout(0,2));
	            agregarPlayer1 = new JButton("agregar a P1");
	            agregarPlayer2 = new JButton("agregar a P2");
	            agregarPokemon.setOpaque(false);        
	            agregarPokemon.add(agregarPlayer1);
	            agregarPokemon.add(agregarPlayer2);

	            ataquesPokemon.add(agregarPokemon);
	        } else if (jugador && maquina){
	        	agregarPlayer1 = new JButton("agregar a P1");
	            ataquesPokemon.add(agregarPlayer1);
	        }

	        ataquesPokemon.setPreferredSize(new Dimension(getWidth()/3,0));

	        panelSuperior.add(pokemonGif,BorderLayout.WEST);
	        panelSuperior.add(ataquesPokemon,BorderLayout.EAST);
	        panelSuperior.add(informacionPokemon,BorderLayout.CENTER);

	        add(panelSuperior, gbc);

	        //PANEL INFERIOR
	        gbc.gridy = 1;
	        gbc.weighty = 0.4;
	        gbc.weightx = 1.0;
	        JPanel panelInferior = new JPanel(new BorderLayout());
	        panelInferior.setOpaque(false);

	        JPanel personajes = new JPanel();
	        personajes.setLayout(new GridLayout(2,0));
	        personajes.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 10));


	        personajes.add(new JButton());
	        personajes.add(new JButton());

	        personajes.setPreferredSize(new Dimension(getWidth()/6,0));
	        
	        personajes.setOpaque(false);
	        JPanel pokemones = new JPanel(new GridLayout(2,1));
	        pokemonesJugador1 = new JPanel(new GridLayout(1,6));
	        pokemonesJugador2 = new JPanel(new GridLayout(1,6));
	        pokemones.add(pokemonesJugador1);
	        pokemones.add(pokemonesJugador2);

	        Border borde = BorderFactory.createLineBorder(new Color(255, 0, 102), 3);
	        pokemonesJugador1.setBorder(borde);
	        pokemonesJugador2.setBorder(borde);

	        pokemones.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 20));
	   
	        pokemones.setOpaque(false);

	        JPanel botonesFinales = new JPanel(new GridLayout(2,0));
	        eliminarPokemones = new JButton();
	        eliminarPokemones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	        JPanel atrasYcontinuar = new JPanel(new GridLayout(0,2));
	        butonContinue = new JButton();
	        butonBack = new JButton();
	        atrasYcontinuar.add(butonBack);
	        atrasYcontinuar.add(butonContinue);

	        botonesFinales.add(eliminarPokemones);
	        botonesFinales.add(atrasYcontinuar);

	        botonesFinales.setPreferredSize(new Dimension(getWidth()/4,0));

	        botonesFinales.setOpaque(false);

	        panelInferior.add(personajes,BorderLayout.WEST);
	        panelInferior.add(pokemones,BorderLayout.CENTER);
	        panelInferior.add(botonesFinales,BorderLayout.EAST);

	        add(panelInferior, gbc);
	        setupKeyBindings();
	    }
		
	@Override
	public void prepareActions() {
		pokedexSiguiente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                posicionPokemon++;
                if(posicionPokemon == gui.getLlavesPokemones().size()){
                    posicionPokemon = 0;
                }
                numeroPokemon = gui.getLlavesPokemones().get(posicionPokemon);
                rutaPokemon = "/presentation/recursos/animated/"+numeroPokemon+".gif";
                pokemonGif.setIcon(new ImageIcon(getClass().getResource(rutaPokemon)));

            }
        });
        pokedexAnterior.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                posicionPokemon--;
                if(posicionPokemon == -1){
                    posicionPokemon = gui.getLlavesPokemones().size()-1;
                }
                numeroPokemon = gui.getLlavesPokemones().get(posicionPokemon);
                rutaPokemon = "/presentation/recursos/animated/"+numeroPokemon+".gif";
                pokemonGif.setIcon(new ImageIcon(getClass().getResource(rutaPokemon)));

            }
        });
        butonContinue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	gui.changePanel(PoobkemonGUIProvisional.BATTLE_PANEL);
            }
        });
        butonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	gui.changePanel(PoobkemonGUIProvisional.SELECT_PANEL);
            }
        });
        agregarPlayer1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {        
            	
            	JLabel imagenPokemon = new JLabel(imagenEscalada("/presentation/recursos/frame2/"+numeroPokemon+".png",1,12,1,12));
            	
            	imagenPokemon.setOpaque(true);
            	imagenPokemon.setBackground(new Color(255, 0, 102, 100));
            	Border borde = BorderFactory.createLineBorder(new Color(255, 0, 102), 3);
            	imagenPokemon.setBorder(borde);
            	pokemonesJugador1.setBorder(null);
              
            	pokemonesJugador1.add(imagenPokemon);
                
                
                revalidate();

            }
        });
        agregarPlayer2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JLabel imagenPokemon = new JLabel(imagenEscalada("/presentation/recursos/frame2/"+numeroPokemon+".png",1,12,1,12));
            	
            	imagenPokemon.setOpaque(true);
            	imagenPokemon.setBackground(new Color(255, 0, 102, 100));
            	Border borde = BorderFactory.createLineBorder(new Color(255, 0, 102), 3);
            	imagenPokemon.setBorder(borde);
            	pokemonesJugador2.setBorder(null);
            	pokemonesJugador2.add(imagenPokemon);
                revalidate();

            }
        });
        
        
    }
	
	private ImageIcon imagenEscalada(String ruta,int anchoN,int anchoD,int altoN,int altoD){

	       Image jugadorVsjugador = new ImageIcon(getClass().getResource(ruta)).getImage();
	       Image jugadorVsjugadorEscalada = jugadorVsjugador.getScaledInstance((getWidth()*anchoN)/anchoD, (getHeight()*altoN)/altoD, Image.SCALE_SMOOTH);
	       return new ImageIcon(jugadorVsjugadorEscalada);
	   }
}


