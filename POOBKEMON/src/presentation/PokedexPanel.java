package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class PokedexPanel extends Panel{

	private JLabel pokemonGif;
	private JButton eliminarPokemones;
	private JButton butonContinue;
	private JButton butonBack;
	private JButton agregarPlayer1;
	private JButton agregarPlayer2;
	private boolean jugador;
	private boolean maquina;
	private int posicionPokemon;
	private Object numeroPokemon;
	private String rutaPokemon;
	private JButton pokedexSiguiente;
	private JButton pokedexAnterior;

	public PokedexPanel(PoobkemonGUIProvisional gui, Panel prevPanel, Panel nextPanel, String backgroundImage) {
		super(gui, prevPanel, nextPanel, backgroundImage);
		SwingUtilities.invokeLater(() -> {
		    prepareElements();
		    prepareActions();
		});
	}

	@Override
	public void prepareElements() {
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

	        if(jugador && !maquina){
	            JPanel agregarPokemon = new JPanel(new GridLayout(0,2));
	            agregarPokemon.setOpaque(false);
	            agregarPlayer1 = new JButton("agregar a P1");
	            agregarPlayer2 = new JButton("agregar a P2");
	            agregarPokemon.add(agregarPlayer1);
	            agregarPokemon.add(agregarPlayer2);

	            ataquesPokemon.add(agregarPokemon);
	        } else if (jugador && maquina){
	            agregarPlayer1 = new JButton("agregar a player");
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

	        JPanel personajes = new JPanel();
	        personajes.setLayout(new GridLayout(2,0));
	        personajes.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 10));


	        personajes.add(new JButton());
	        personajes.add(new JButton());

	        personajes.setPreferredSize(new Dimension(getWidth()/6,0));
	        personajes.setBackground(Color.cyan);

	        JPanel pokemones = new JPanel(new GridLayout(2,6));
	        pokemones.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 20));
	        pokemones.add(new JButton());
	        pokemones.add(new JButton());
	        pokemones.add(new JButton());
	        pokemones.add(new JButton());
	        pokemones.add(new JButton());
	        pokemones.add(new JButton());
	        pokemones.add(new JButton());
	        pokemones.add(new JButton());
	        pokemones.add(new JButton());
	        pokemones.add(new JButton());
	        pokemones.add(new JButton());
	        pokemones.add(new JButton());

	        pokemones.setBackground(Color.DARK_GRAY);

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

	        botonesFinales.setBackground(Color.GREEN);

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
    }
}


