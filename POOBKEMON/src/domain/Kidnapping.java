package domain;

public interface Kidnapping {
	public default void trapPokemonInBattle(Pokemon pokemon) {
		if(pokemon.getCanEscape()) {
			pokemon.turnCanEscape();	
		}	
	}
}
