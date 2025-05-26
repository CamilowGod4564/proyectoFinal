package domain;

import java.util.ArrayList;

public class Special extends DirectDamage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Special(String name, int pp,Type type, int power) {
		super(name, pp,type, power);
	}

	@Override
	public Special copy() {
		return new Special(this.name, this.pp, this.type, this.power);
	}

	@Override
	protected double calculateBaseDamage(Pokemon pokemon, Pokemon targetPokemon) {
		return (((((2.0*pokemon.getLevel())/5.0)+2)*power*((double)pokemon.getSpecialAttack()/targetPokemon.getSpecialDefense()))/50.0)+2;
	}

}