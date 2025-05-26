package domain;

public class StatusMovement extends Movement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Status status;
	
	public StatusMovement(String name, int pp, Type type, Status status) {
		super(name, pp, type);
		this.status = status;
	}

	@Override
	public void makeMovement(Pokemon pokemon, Pokemon targetPokemon) {
		targetPokemon.addStatus(status);
	}

	@Override
	public Movement copy() {
		return new StatusMovement(this.name,this.pp , this.type, this.status);
	}

	@Override
	public double evaluateEffectiveness(Pokemon self, Pokemon target) {
		double effectiveness = 0;
		if(!target.getStatuses().containsKey(this.name)) {
			effectiveness = 50; 
		}
		return effectiveness;
	}

}
