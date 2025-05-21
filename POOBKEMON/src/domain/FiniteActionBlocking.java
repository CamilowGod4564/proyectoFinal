package domain;

public class FiniteActionBlocking extends FiniteDurationStatus implements Blocking{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FiniteActionBlocking(String name, int maxDuration, boolean removeTemporarilyOnSwitch, boolean removeOnSwitch,int minDuration, String inmuneType) {
		super(name, maxDuration, removeTemporarilyOnSwitch, removeOnSwitch, minDuration, inmuneType);
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
		return new FiniteActionBlocking(name, duration, removeTemporarilyOnSwitch,removeOnSwitch, minDuration,inmuneType);
	}

	@Override
	protected void statusLogic(Pokemon pokemon) {
		makePokemonUseless(pokemon,inmuneType);
	}
}
