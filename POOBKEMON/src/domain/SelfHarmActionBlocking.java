package domain;

import java.util.Random;

public class SelfHarmActionBlocking extends FiniteActionBlocking {
	
	protected double chanceOfApplying;
	protected Random random = new Random();
	boolean wasApplied;
	
	public SelfHarmActionBlocking(String name, int maxDuration, boolean removeTemporarilyOnSwitch,boolean removeOnSwitch, int minDuration, double chanceOfApplying) {
		super(name, maxDuration, removeTemporarilyOnSwitch, removeOnSwitch, minDuration);
		this.chanceOfApplying = chanceOfApplying;
		this.wasApplied = false;
	}

	
	private boolean winChance() {
		return random.nextDouble()<chanceOfApplying;
	}
	
	@Override
	public void statusLogic(Pokemon pokemon) {
		if(wasApplied) {
			pokemon.turnGonnaFail();
			wasApplied = false;
		}
		if(winChance()) {
			makePokemonUseless(pokemon);
			//pokemon se debe cortar las venas aca
			wasApplied = true;
		}
	}
}

