package domain;

public class FiniteActionBlocking extends FiniteDurationStatus implements Blocking{
	


	public FiniteActionBlocking(String name,int maxDuration, boolean removeTemporarilyOnSwitch,boolean removeOnSwitch, int minDuration ) {
		super(name, maxDuration, removeTemporarilyOnSwitch,removeOnSwitch , minDuration);
	}

	@Override
	public void finishStatus(Pokemon pokemon) {
		if(pokemon.getIsGonnaFail()) {
			pokemon.turnGonnaFail();
		}
		pokemon.delStatus(this);
	}

	@Override
	public Status copy() {
		return new FiniteActionBlocking(name, duration, removeTemporarilyOnSwitch,removeOnSwitch, minDuration );
	}

	@Override
	protected void statusLogic(Pokemon pokemon) {
		makePokemonUseless(pokemon);
	}
}
