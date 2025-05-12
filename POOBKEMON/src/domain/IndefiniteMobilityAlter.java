package domain;

public class IndefiniteMobilityAlter extends IndefiniteDurationStatus implements Kidnapping{

	public IndefiniteMobilityAlter(String name, boolean removeTemporarilyOnSwitch, boolean removeOnSwitch,double chanceOfLoseStatus,String inmuneType) {
		super(name, removeTemporarilyOnSwitch, removeOnSwitch, chanceOfLoseStatus,inmuneType);
	}

	@Override
	public Status copy() {
		return new IndefiniteMobilityAlter(name, removeTemporarilyOnSwitch, removeOnSwitch, chanceOfLoseStatus,inmuneType);
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
