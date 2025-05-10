package domain;

public class FiniteMobilityAlter extends FiniteDurationStatus implements Kidnapping{

	public FiniteMobilityAlter(String name, int maxDuration, boolean removeTemporarilyOnSwitch, boolean removeOnSwitch,int minDuration) {
		super(name, maxDuration, removeTemporarilyOnSwitch, removeOnSwitch, minDuration);
	}

	@Override
	public Status copy() {
		return new FiniteMobilityAlter(name, duration, removeTemporarilyOnSwitch, removeOnSwitch, minDuration);
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
