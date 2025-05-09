package domain;

public interface Blocking {
	public default void makePokemonUseless(Pokemon pokemon) {
		if(!pokemon.getIsGonnaFail()) {
			pokemon.turnGonnaFail();
		}
	}
}
