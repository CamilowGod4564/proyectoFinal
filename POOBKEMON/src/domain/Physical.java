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
	public int calculateDamage(Pokemon pokemon, Pokemon targetPokemon) {
		ArrayList<Double> effectivity = new ArrayList<>();
		double totalEffectiveness = 1; //1 por que es el modulo de la multiplicacion Tpro reference
		double baseDamage = (((((2.0*pokemon.getLevel())/5.0)+2)*power*((double)pokemon.getAttack()/targetPokemon.getDefense()))/50.0)+2;
		//SE PUEDE SIMPLIFICAR
		
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
	
}
