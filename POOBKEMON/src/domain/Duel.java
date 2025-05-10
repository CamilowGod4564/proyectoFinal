package domain;

import java.util.*;

public abstract class Duel {
	Player playingPlayer;
	Player waitingPlayer;
	
	public void applyPokemonsStatuses() {
		for (Pokemon p :playingPlayer.getPokemonTeam()) {
			p.applyActualStatuses();
		}
		for (Pokemon p :waitingPlayer.getPokemonTeam()) {
			p.applyActualStatuses();
		}
	}
	
	public void finish() {
		//mejor maneja el fin de juego con excepciones
	}
	
	public void createPlayer(String name) {
		if(playingPlayer == null) {
			playingPlayer = new Player(name);
		}else if (!(playingPlayer == null) && waitingPlayer == null) {
			waitingPlayer = new Player(name);
		}
	}
	
	public void changePlayingPlayer() {
		Player p0 = waitingPlayer;
		waitingPlayer = playingPlayer;
		playingPlayer = p0;
	}
	public void playerSelectPokemon(Pokemon pokemon) {
		playingPlayer.addPokemon(pokemon);
	}
	public void playerDeselectPokemon(Pokemon pokemon) {
		playingPlayer.removePokemon(pokemon); //tener cuidado que viene desde el GUI
	}
	public void playerUseItem(String itemName) {
		playingPlayer.useItem(itemName);
	}
	public void playerUseMovement(String movement){
		playingPlayer.usePokemonMovement(movement, waitingPlayer.getPlayingPokemon());		
	}
	public void playerChangePokemon(Pokemon newPokemon) {
		playingPlayer.changePlayingPokemon(newPokemon);
	}
	public void playerSurrender() {
		finish();
	}
	public ArrayList<Pokemon> getPlayerTeam() {
		return playingPlayer.getPokemonTeam();
	}
	public Pokemon getPlayerPlayingPokemon() {
		return playingPlayer.getPlayingPokemon();
	}
	public abstract void prepareTeams();

	public String getPlayingPlayerName() {
		return playingPlayer.getName();
	}

	public Pokemon getWaitingPlayerPlayingPokemon() {
		return waitingPlayer.getPlayingPokemon();
	}

	public void changePokemonAutomatically() {
		// TODO Auto-generated method stub
		
	}
	
	
}
