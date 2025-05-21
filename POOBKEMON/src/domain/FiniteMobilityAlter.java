package domain;

public class FiniteMobilityAlter extends FiniteDurationStatus implements Kidnapping{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FiniteMobilityAlter(String name, int maxDuration, boolean removeTemporarilyOnSwitch, boolean removeOnSwitch,int minDuration,String inmuneType) {
		super(name, maxDuration, removeTemporarilyOnSwitch, removeOnSwitch, minDuration, inmuneType);
	}

	@Override
	public Status copy() {
		return new FiniteMobilityAlter(name, duration, removeTemporarilyOnSwitch, removeOnSwitch, minDuration, inmuneType);
	}

	@Override
	protected void statusLogic(Pokemon pokemon) {
		trapPokemonInBattle(pokemon);
	}
	
	@Override 
	public void finishStatus(Pokemon pokemon) {
		if(pokemon.getCanEscape()) {
			pokemon.turnCanEscape();
		}
		pokemon.delStatus(this);
	}
}
