package domain;

import java.util.*;

public class Player {
	private String name;
	private Pokemon playingPokemon;
	private Bag bag;
	private ArrayList<Pokemon> pokemons;
	
	/**
	 * @param name
	 */
	public Player(String name) {
		this.name = name;
	}
	
	public ArrayList<Pokemon> getPokemonTeam() {
		return pokemons;
	}
	public String getName(){
		return name;
	}
	public void addPokemon(Pokemon pokemon) {
		if(pokemons.size()< 6) {
			pokemons.add(pokemon);
			}else {
				//exception
		}	
	}
	public void removePokemon(Pokemon pokemon) {
		 if (pokemons.contains(pokemon)) {
		        pokemons.remove(pokemon);
		        if (pokemon.equals(playingPokemon)) {
		            playingPokemon = null; 
		        }
		    } else {
		        //exception
		 }
	}
	
	public Pokemon getPlayingPokemon() {
		return playingPokemon;
	}
	public void setPlayingPokemon(Pokemon pokemon) {
		if (pokemons.contains(pokemon)) {
	        this.playingPokemon = pokemon;
	    } else {
	        //exception
	    }
	}
	public void usePokemonMovement(String movement,Pokemon targetPokemon) {
		playingPokemon.useMovement(movement, targetPokemon);
	}
	public void useItem(String itemName){
		bag.useItem(itemName, playingPokemon);
	}
	public void addItem(Item item) {
		bag.addItem(item);
	}
	public void delItem(String itemName) {
		bag.delItem(itemName);
	}
	public Bag getBag() {
		return bag;
	}
}
