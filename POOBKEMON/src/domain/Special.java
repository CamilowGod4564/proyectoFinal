package domain;

import java.util.ArrayList;

public class Special extends DirectDamage{

	public Special(String name, int pp,Type type, int power) {
		super(name, pp,type, power);
	}

	@Override
	public int calculateDamage(Pokemon pokemon, Pokemon targetPokemon) {
		ArrayList<Double> effectivity = new ArrayList<>();
		double totalEffectiveness = 1; //1 por que es el modulo de la multiplicacion Tpro reference
		double baseDamage = (((((2.0*pokemon.getLevel())/5.0)+2)*power*((double)pokemon.getSpecialAttack()/targetPokemon.getSpecialDefense()))/50.0)+2;
		double stab = super.getSTAB(pokemon); 
		for(Type t:targetPokemon.getTypes()){
			effectivity.add(getEffectiveness(t));
		}
		for(Double d:effectivity) {
			totalEffectiveness *= d;
		}
		double variation = super.getRandomVariation(); 
		double criticalHit = super.getCritical();
		
		int totalDamage = (int) (baseDamage*stab*totalEffectiveness*variation*criticalHit);
		
		return totalDamage;
	}

	@Override
	protected Movement copy() {
		return new Special(name, pp,type, power);
	}
}