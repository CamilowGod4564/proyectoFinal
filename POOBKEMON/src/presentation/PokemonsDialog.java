package presentation;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PokemonsDialog extends JDialog{
	
	ArrayList<String> pokemonMovements;

	public PokemonsDialog(JFrame parent, ArrayList<String> pokemonMovements) {
		super(parent, "TU EQUIPO :D", true);
        this.pokemonMovements = pokemonMovements;
        prepareElements();
        prepareActions();
    }


    private void prepareElements() {
        
        JPanel panel = new JPanel(new GridLayout(2, 3, 10, 10));
        panel.setPreferredSize(new Dimension(600, 300)); 
        for (int i = 0; i < 6; i++) {
            JButton botonPokemon;
            if (i < pokemonMovements.size()) {
                String nombre = pokemonMovements.get(i);
                botonPokemon = new JButton(nombre);
                String ruta = "/presentation/recursos/frame2/" + nombre + ".png";
                try {
                    botonPokemon.setIcon(new ImageIcon(getClass().getResource(ruta)));
                } catch (Exception e) {
                    System.out.println("No se encontró imagen para: " + nombre);
                }

            } else {
                botonPokemon = new JButton("Vacío");
                botonPokemon.setEnabled(false);
            }
            panel.add(botonPokemon);
        }
        add(panel);
        pack();  
        setLocationRelativeTo(getParent());
    }

    private void prepareActions() {

    	//nuevas barra vida para el pokemon nuevo
    	//playerHealth
    	//despues de que un boton haga lo que tiene que hacer debe 
    	//PoobkemonGUIProvisional.juego.nextTurn();
        //actualizarGUI();
    	
    }

}
