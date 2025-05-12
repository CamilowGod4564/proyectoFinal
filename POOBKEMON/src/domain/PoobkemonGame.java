package domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class PoobkemonGame{
	TreeMap<String,Pokemon> pokemons;
	HashMap<String,Movement> movements;
	HashMap<String,Status> statuses;
	HashMap<String,Type> types;
	HashMap<String,Item> items;
	Duel duel;
	
	public PoobkemonGame() throws ClassNotFoundException, FileNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		PoobkemonLoader p =new PoobkemonLoader();
		types = p.loadTypes("TypesPoobkemon.txt");
		statuses = p.loadStatuses("StatusesPoobkemon.txt");
		movements = p.loadMovements(types, statuses, "MovementsPoobkemon.txt");
		pokemons = p.loadPokemons("PokemonsPoobkemon.txt", movements, types);
		items = p.loadItems("ItemsPoobkemon.txt");
	}
	
	//Before duel
	
	public void setDuelMode(String mode) {
		if(mode == "Normal") {
			duel = new NormalDuel();
		}else if(mode == "Survival"){
			duel = new SurvivalDuel();
			//duel.createPlayer(mode);
		}
	}
	
	public void prepareTeams() {
		duel.prepareTeams(pokemons,movements);
	}
	
	
	public void newPlayer(String trainerName) {
		duel.createPlayer(trainerName);
	}
	
	public void confirmPlayers() {
		duel.assignPlayers();
	}
	
	public void playerSelectPokemonForTeam(Player player,String pokemonName) {
		duel.playerSelectPokemon(player,pokemons.get(pokemonName.toUpperCase()).copy());
		//try catch para mostrar que equipo completo
	}
	
	public void playerDeselectPokemonForTeam(Player player,Pokemon pokemon) {
		duel.playerDeselectPokemon(player,pokemon);
	}
	
	public void playerSelectItem(Player player,String item) {
		//hacer metodo en duel
	}
	public void playerDeselectItem(Player player,String item) {
		//hacer metodo en duel
	}
	
	public void cleanPlayers() {
		duel.cleanPlayers();
	}
	public void cleanPokemons() {
		duel.cleanPokemons();
	}
	public void cleanPlayersItems() {
		duel.cleanItems();
	}
	
	
	// falta un changepokemonMovements mhmmm 
	
	//For consult
	
	public TreeMap<String,String>  getPokemons() {
		TreeMap<String,String> numberNamePokemons = new TreeMap<>();
		for(Pokemon p:pokemons.values()) {
			numberNamePokemons.put(p.getPokedexNumber(),p.getName());
		}
		return numberNamePokemons;
	}

	public Set<String> getMovements() {
		return movements.keySet();
	}

	public Set<String> getItems() {
		return items.keySet();
	}
	
	public ArrayList<Pokemon> getPlayerPokemons(){
		return duel.getPlayerTeam();
	}
	//get current player??
	
	//Actions that the currentPlayer in Duel can do
	
	public void attack(String movement) {
		duel.playerUseMovement(movement);
		//try catch si no hay suficiente pp
	}
	public void useItem(String item) {
		duel.playerUseItem(item);
	}
	public void changePlayingPokemon(Pokemon newPokemon) {
		duel.playerChangePokemon(newPokemon);
	}
	public void surrender() {
		duel.playerSurrender();
	}
	
	//During Battle 
	
	public void nextTurn() {
		duel.changePlayingPlayer();
	}
	
	public void applyStatuses() {
		duel.applyPokemonsStatuses();
	}
	
	public void aPokemonFainted() {
		if(duel.getPlayerPlayingPokemon().isFainteed()) {
			duel.changePokemonAutomatically(duel.getPlayingPlayer());
		}
		if(duel.getWaitingPlayerPlayingPokemon().isFainteed()) {
			duel.changePokemonAutomatically(duel.getWaitingPlayer());
		}
	}
	
	
	

	
}