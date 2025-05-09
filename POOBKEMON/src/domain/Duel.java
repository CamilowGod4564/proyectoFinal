package domain;

import java.util.*;

public abstract class Duel {
	Player playingPlayer;
	Player waitingPlayer;
	
	public void start() {
		
	}
	
	public void finish() {
		//mejor maneja el fin de juego con excepciones
	}
	
	public void assignPlayers(Player p1,Player p2) {
		playingPlayer = p1;
		waitingPlayer = p2;
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
	public void playerChangePokemon(Pokemon newPokemon,Pokemon oldPokemon) {
		playerDeselectPokemon(oldPokemon);
		playerSelectPokemon(newPokemon);
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
}
