package domain;

public abstract class DirectDamage extends Movement{
	
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
	public abstract int calculateDamage(Pokemon pokemon,Pokemon targetPokemon);
	
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
				return 1.5;
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
}
