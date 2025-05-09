package domain;

import java.util.Random;

public abstract class FiniteDurationStatus extends Status {
	private Random randomBool = new Random();
	protected int minDuration;
	
	public FiniteDurationStatus(String name,int maxDuration, boolean removeTemporarilyOnSwitch,boolean removeOnSwitch, int minDuration ) {
		super(name, maxDuration,removeTemporarilyOnSwitch,removeOnSwitch); //maxDuration its just duration tbh
		this.minDuration = minDuration;
	}

	public boolean pokemonLosesStatusBefore() {
	    return randomBool.nextBoolean();
	}
	
	@Override
	protected void makeStatusWork(Pokemon pokemon) {
		if((currentDuration == 0 || pokemonLosesStatusBefore()) && currentDuration >= minDuration) {
			finishStatus(pokemon);
		}else {
			currentDuration -= 1;
			statusLogic(pokemon);
		}
	}
}
