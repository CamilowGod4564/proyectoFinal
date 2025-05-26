package domain;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class DirectDamage extends Movement implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int power;
	
	public DirectDamage(String name, int pp,Type type,int power) {
		super(name, pp,type);
		this.power = power;
	}
	
	@Override
	public void makeMovement(Pokemon pokemon, Pokemon targetPokemon) {
		if(currentPP > 0) {
			super.losePP();
			int damageToApply =  calculateDamage(pokemon, targetPokemon);
			
			targetPokemon.receiveDamage(damageToApply);
		}else {
			//exception
		}
	}
	public int calculateDamage(Pokemon pokemon,Pokemon targetPokemon) {
		ArrayList<Double> effectivity = new ArrayList<>();
		double totalEffectiveness = 1; //1 por que es el modulo de la multiplicacion Tpro reference
		
		double baseDamage = calculateBaseDamage(pokemon, targetPokemon);
		
		double stab = getSTAB(pokemon); 
		for(Type t:targetPokemon.getTypes()){
			effectivity.add(getEffectiveness(t));
		}
		for(Double d:effectivity) {
			totalEffectiveness *= d;
		}
		double variation = getRandomVariation(); 
		double criticalHit = getCritical();
		
		int totalDamage = (int) (baseDamage*stab*totalEffectiveness*variation*criticalHit);
		return totalDamage;
	}
	
	
	protected abstract double calculateBaseDamage(Pokemon pokemon, Pokemon targetPokemon);

	public double getRandomVariation() {
	    int min = 85;
	    int max = 100;
	    int randomPercent = min + (int)(Math.random() * (max - min + 1));
	    return randomPercent / 100.0;
	}
	
	public double getCritical() {
	    double critChance = 1.0 / 16.0; 
	    return Math.random() < critChance ? 1.5 : 1.0;
	}
	public double getSTAB(Pokemon pokemon) {
		for(Type t:pokemon.getTypes()) {
			if (t==type) {
				return 1.5; //SE PUEDEN REDUCIR RETURNS
			}
		}
		return 1;
	}
	
	public double getEffectiveness(Type askingType) {
		if(askingType.isInmuneAgainst(type.getTypeName())) {
			return 0;
		}else if(type.isUnEffectiveAgainst(askingType.getTypeName())) {
			return 0.5;
		}else if(type.isStrongAgainst(askingType.getTypeName())) {
			return 2;
		}else {
			return 1;
		}
	}
	
	@Override
	public double evaluateEffectiveness(Pokemon self, Pokemon target) {
		return calculateDamage(self, target)*0.85;
	}
	
	
	@Override
	public Movement copy() {
		return new Special(name, pp,type, power);
	}
}
