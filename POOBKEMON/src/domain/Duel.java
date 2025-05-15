package domain;

import java.util.*;

public abstract class Duel {
	protected Player p1;
	protected Player p2;
	protected Player playingPlayer;
	protected Player waitingPlayer;
	protected Player winner;
	
	public void applyPokemonsStatuses() {
		for (Pokemon p :playingPlayer.getPokemonTeam()) {
			p.applyActualStatuses();
		}
		for (Pokemon p :waitingPlayer.getPokemonTeam()) {
			p.applyActualStatuses();
		}
	}
	
	public void finish(Player player) {
		if(player == playingPlayer) {
			winner = waitingPlayer;
		}else {
			winner = playingPlayer;
		}
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
	public void playerSelectPokemon(String p,Pokemon pokemon) {
		if(p1.getName().equals(p)) {
			p1.addPokemon(pokemon);
		}else if(p2.getName().equals(p)) {
			p2.addPokemon(pokemon);
		}
	}
	public void playerDeselectPokemon(Player p,Pokemon pokemon) {
		if(p1.getName().equals(p)) {
			p1.removePokemon(pokemon);
		}else if(p2.getName().equals(p)) {
			p2.removePokemon(pokemon);
		}
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
		finish(playingPlayer);
	}
	public ArrayList<Pokemon> getPlayerTeam() {
		return playingPlayer.getPokemonTeam();
	}
	public Pokemon getPlayerPlayingPokemon() {
		return playingPlayer.getPlayingPokemon();
	}
	public abstract void prepareTeams(TreeMap<String,Pokemon> pokemons, HashMap<String,Movement> movements);

	public Player getPlayingPlayer() {
		return playingPlayer;
	}
	
	public Player getWaitingPlayer() {
		return waitingPlayer;
	}

	public Pokemon getWaitingPlayerPlayingPokemon() {
		return waitingPlayer.getPlayingPokemon();
	}

	public void changePokemonAutomatically(Player player) {
		boolean wasntChanged = true;
		for(Pokemon p :player.getPokemonTeam()) {
			if(!p.isFainteed()) {
				player.changePlayingPokemon(p);
				wasntChanged= false;
				break;
			}
		}
		if(wasntChanged) {
			finish(player); //player doesnt have any left pokemons
		}
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

	public Player getWinner() {
		return winner;
	}

	public void playerSelectItem(String playerUsingTheItem,Item item) {
		if(p1.getName().equals(playerUsingTheItem)) {
			p1.addItem(item);
		}else if(p2.getName().equals(playerUsingTheItem)) {
			p2.addItem(item);
		}
	}

	public void playerDeselectItem(String playerRemovingTheItem,String item) {
		if(p1.getName().equals(playerRemovingTheItem)) {
			p1.delItem(item);
		}else if(p2.getName().equals(playerRemovingTheItem)) {
			p2.delItem(item);
		}
		
	}
	
	
}
