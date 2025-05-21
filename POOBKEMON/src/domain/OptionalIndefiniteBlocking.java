package domain;

import java.util.Random;

public class OptionalIndefiniteBlocking extends IndefiniteActionBlocking{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Random random = new Random();
	double chanceOfApplying;
	boolean wasApplied;
	
	public OptionalIndefiniteBlocking(String name, boolean removeTemporarilyOnSwitch, boolean removeOnSwitch,double chanceOfLoseStatus,double chanceOfApplying,String inmuneType) {
		super(name, removeTemporarilyOnSwitch, removeOnSwitch, chanceOfLoseStatus,inmuneType);
		this.chanceOfApplying = chanceOfApplying;
		this.wasApplied = false;
	}
	
	@Override
	public void statusLogic(Pokemon pokemon) {
		if(wasApplied) {
			pokemon.turnGonnaFail();
			wasApplied = false;
		}
		if(random.nextDouble()<chanceOfApplying) {
			makePokemonUseless(pokemon,inmuneType);
			wasApplied = true;
		}
	}
	@Override
	public Status copy() {
		return new OptionalIndefiniteBlocking(name, removeTemporarilyOnSwitch, removeOnSwitch, chanceOfLoseStatus, chanceOfApplying, inmuneType);
	}
}
