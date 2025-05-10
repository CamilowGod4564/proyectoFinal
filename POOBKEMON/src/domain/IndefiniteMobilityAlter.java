package domain;

public class IndefiniteMobilityAlter extends IndefiniteDurationStatus implements Kidnapping{

	public IndefiniteMobilityAlter(String name, boolean removeTemporarilyOnSwitch, boolean removeOnSwitch,double chanceOfLoseStatus) {
		super(name, removeTemporarilyOnSwitch, removeOnSwitch, chanceOfLoseStatus);
	}

	@Override
	public Status copy() {
		return new IndefiniteMobilityAlter(name, removeTemporarilyOnSwitch, removeOnSwitch, chanceOfLoseStatus);
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
	} // los finish status podrian reciclarse xd

}
