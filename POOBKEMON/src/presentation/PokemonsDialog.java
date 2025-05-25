package presentation;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.Pokemon;

public class PokemonsDialog extends JDialog{
	
	private ArrayList<String> pokemonsNames;
	private JButton[] arrayTeam;
	private ArrayList<Pokemon> playerPokemons;
	private BattlePanel panel;
	private ArrayList<String> pokemonsIds;

	public PokemonsDialog(JFrame parent, ArrayList<String> pokemonsNames,ArrayList<String> pokemonsIds,ArrayList<Pokemon> playerPokemons, BattlePanel panel) {
		super(parent, "TU EQUIPO :D", true);
        this.pokemonsNames = pokemonsNames;
        this.pokemonsIds = pokemonsIds;
        this.playerPokemons = playerPokemons;
        this.panel = panel;
        prepareElements();
        prepareActions();
    }

    private void prepareElements() {
        
        JPanel panel = new JPanel(new GridLayout(2, 3, 10, 10));
        panel.setPreferredSize(new Dimension(600, 300)); 
        arrayTeam = new JButton[6];
        for (int i = 0; i < 6; i++) {
            JButton botonPokemon;
            if (i < pokemonsNames.size()) {
                String nombre = pokemonsNames.get(i); //La posicion del nombre del pokemon es la misma al objeto pokemon en playerPokemons por el diseño actual
                botonPokemon = new JButton(nombre);
                botonPokemon.putClientProperty("pokemon", playerPokemons.get(i));
                String ruta = "/presentation/recursos/frame2/" + pokemonsIds.get(i) + ".png";
                try {
                    botonPokemon.setIcon(new ImageIcon(getClass().getResource(ruta)));
                } catch (Exception e) {
                    System.out.println("No se encontró imagen para: " + nombre);
                }

            } else {
                botonPokemon = new JButton("Vacío");
                botonPokemon.setEnabled(false);
            }
            arrayTeam[i] = botonPokemon; 
            panel.add(botonPokemon);
        }
        add(panel);
        pack();  
        setLocationRelativeTo(getParent());
    }

	private void prepareActions() {
    	for(JButton b : arrayTeam) {
    		 b.addActionListener(e -> {
    			 JButton source = (JButton) e.getSource();
    			 if(PoobkemonGUIProvisional.juego.getCurrentActualPokemon() == ( (Pokemon) source.getClientProperty("pokemon")) ) {
    				//ventanita de este pokemon ya esta en el campo!!
    			 }else {
    				 if(PoobkemonGUIProvisional.juego.pokemonIsFainted((Pokemon) source.getClientProperty("pokemon"))) {
    					if(PoobkemonGUIProvisional.juego.getPlayerItems().get("REVIVE") != null) { //esto hay que mejorarlo para ser mas extensible
    						if(PoobkemonGUIProvisional.juego.getPlayerItems().get("REVIVE") > 0) {
    							PoobkemonGUIProvisional.juego.revivePokemon((Pokemon) source.getClientProperty("pokemon"));
        						PoobkemonGUIProvisional.juego.changePlayingPokemon( (Pokemon) source.getClientProperty("pokemon"));
        	        			PoobkemonGUIProvisional.juego.nextTurn();
        	        			panel.refresh();
        	        			dispose();   
    						}
    					}
    				 }else if(!PoobkemonGUIProvisional.juego.pokemonIsFainted((Pokemon) source.getClientProperty("pokemon"))) {
    					PoobkemonGUIProvisional.juego.changePlayingPokemon( (Pokemon) source.getClientProperty("pokemon"));
 	        			PoobkemonGUIProvisional.juego.nextTurn();
 	        			panel.refresh();
 	        			dispose();   
    				 }
    			 }	 
    		 });
    	}
    }

}
