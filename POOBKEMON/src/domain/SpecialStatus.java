package domain;

public class SpecialStatus extends Special {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	        
	@Override
	public SpecialStatus copy() {
		return new SpecialStatus(this.name, this.currentPP, this.type, this.power, this.chanceOfApplying, this.status);
	}
	@Override
	public double evaluateEffectiveness(Pokemon self, Pokemon target) {
		return super.calculateDamage(self, target);
	}
	
}
