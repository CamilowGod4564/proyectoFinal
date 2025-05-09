package domain;

public interface Damaging {
	
	public default void makePokemonGetHurt(Pokemon pokemon,int damage) {
		pokemon.receiveDamage(damage);
	}
	
}
