package domain;

import java.util.ArrayList;

public class Physical extends DirectDamage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Physical(String name, int pp, Type type,int power) {
		super(name, pp,type, power);
	}

	@Override
	protected double calculateBaseDamage(Pokemon pokemon, Pokemon targetPokemon) {
		return (((((2.0*pokemon.getLevel())/5.0)+2)*power*((double)pokemon.getAttack()/targetPokemon.getDefense()))/50.0)+2;
	}
	
}
