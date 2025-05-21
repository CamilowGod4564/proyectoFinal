package domain;

public class IndefiniteActionBlocking extends IndefiniteDurationStatus implements Blocking{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IndefiniteActionBlocking(String name, boolean removeTemporarilyOnSwitch, boolean removeOnSwitch,double chanceOfLoseStatus,String inmuneType) {
		super(name, removeTemporarilyOnSwitch, removeOnSwitch, chanceOfLoseStatus,inmuneType);
		this.chanceOfLoseStatus = chanceOfLoseStatus;
	}

	@Override
	public Status copy() {
		return new IndefiniteActionBlocking(name, removeTemporarilyOnSwitch,removeOnSwitch,chanceOfLoseStatus,inmuneType);
	}

	@Override
	protected void statusLogic(Pokemon pokemon) {
		makePokemonUseless(pokemon,inmuneType);
	}
	
	@Override
	public void finishStatus(Pokemon pokemon) {
		if(pokemon.getIsGonnaFail()) {
			pokemon.turnGonnaFail();	
		}
		pokemon.delStatus(this);
	}
	
}
