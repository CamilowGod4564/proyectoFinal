package domain;

import java.util.*;

public abstract class Duel {
	Player p1;
	Player p2;
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
		if(p1 == null) {
			p1 = new Player(name);
		}else if (!(p1 == null) && p2 == null) {
			p2 = new Player(name);
		}
	}
	
	public void assignPlayers() { //assign randomly the player who stars
		Random random = new Random();
        boolean p1Starts = random.nextBoolean(); 
        
        if (p1Starts) {
            playingPlayer = p1;
            waitingPlayer = p2;
        } else {
            playingPlayer = p2;
            waitingPlayer = p1;
        }
	}
	
	
	public void changePlayingPlayer() {
		Player p0 = waitingPlayer;
		waitingPlayer = playingPlayer;
		playingPlayer = p0;
	}
	public void playerSelectPokemon(Player p,Pokemon pokemon) {
		p.addPokemon(pokemon);
	}
	public void playerDeselectPokemon(Player p,Pokemon pokemon) {
		p.removePokemon(pokemon); //tener cuidado que viene desde el GUI
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

	public void cleanPlayers() {
		p1 = null;
		p2 = null;
	}

	public void cleanPokemons() {
		if(p1.getPokemonTeam() != null) {
			List<Pokemon> copy = new ArrayList<>(p1.getPokemonTeam());
			for (Pokemon p : copy) {
				p1.removePokemon(p);
			}
		}
		if(p2.getPokemonTeam() != null) {
			List<Pokemon> copy = new ArrayList<>(p2.getPokemonTeam());
			for (Pokemon p : copy) {
				p2.removePokemon(p);
			}
		}
	}

	public void cleanItems() {
		if(p1.getBag().getItems() != null) {
			List<Item> copy = new ArrayList<>(p1.getBag().getItems().values());
			for (Item i : copy) {
				p1.getBag().delItem(i.getName());
			}
		}
		if(p2.getBag().getItems() != null) {
			List<Item> copy = new ArrayList<>(p2.getBag().getItems().values());
			for (Item i : copy) {
				p2.getBag().delItem(i.getName());
			}
		}
	}
	
	
	
}
