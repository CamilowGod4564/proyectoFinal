package domain;

public class SpecialStatus extends Special {
	Status status;
	double chanceOfApplying;
	
	public SpecialStatus(String name, int pp,Type type ,int power,double chance,Status status) {
		super(name, pp, type,power);
		this.status = status;
		chanceOfApplying = chance;
	}
	
	// Puede aplicar un estado al objetivo después de hacer daño.
	@Override
	public void makeMovement(Pokemon pokemon, Pokemon targetPokemon){
		super.makeMovement(pokemon, targetPokemon);
		 double probability = Math.random(); 
	        if (probability <= chanceOfApplying) {
	        	targetPokemon.addStatus(status.copy());
	        }
	}
	
}
