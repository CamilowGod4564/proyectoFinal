package domain;

public class IndefiniteActionBlocking extends IndefiniteDurationStatus implements Blocking{
	

	public IndefiniteActionBlocking(String name, boolean removeTemporarilyOnSwitch, boolean removeOnSwitch,double chanceOfLoseStatus) {
		super(name, removeTemporarilyOnSwitch, removeOnSwitch);
		this.chanceOfLoseStatus = chanceOfLoseStatus;
	}

	@Override
	public Status copy() {
		return new IndefiniteActionBlocking(name, removeTemporarilyOnSwitch,removeOnSwitch,chanceOfLoseStatus);
	}

	@Override
	protected void statusLogic(Pokemon pokemon) {
		makePokemonUseless(pokemon);
	}
	
	@Override
	public void finishStatus(Pokemon pokemon) {
		if(pokemon.getIsGonnaFail()) {
			pokemon.turnGonnaFail();	
		}
		pokemon.delStatus(this);
	}
}
