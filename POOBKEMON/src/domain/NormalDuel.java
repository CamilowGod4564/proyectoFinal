package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;

public class NormalDuel extends Duel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Random random  = new Random();

	public void prepareTeams(TreeMap<String,Pokemon> pokemons, HashMap<String,Movement> movements) {
		
		ArrayList<Pokemon> available = new ArrayList<>(pokemons.values());
		
		for(int i = 0; i<7;i++) {
			p2.addPokemon(available.get(random.nextInt(available.size())).copy());
			available.remove(i);
		}
		
	}
	
}
