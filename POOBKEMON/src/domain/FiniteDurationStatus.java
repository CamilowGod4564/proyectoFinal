package domain;

import java.util.Random;

public abstract class FiniteDurationStatus extends Status {
	private Random randomBool = new Random();
	protected int minDuration;
	
	public FiniteDurationStatus(String name,int maxDuration, boolean removeTemporarilyOnSwitch,boolean removeOnSwitch, int minDuration ) {
		super(name, maxDuration,removeTemporarilyOnSwitch,removeOnSwitch); //maxDuration its just duration tbh
		this.minDuration = minDuration;
	}
	public boolean  isGonnaBeApplied() {
		if((currentDuration == 0 || pokemonLosesStatusBefore()) && currentDuration > minDuration) {
			return false;
		}
		return true;
	}

	public boolean pokemonLosesStatusBefore() {
	    boolean loses = randomBool.nextBoolean();
	    return loses;
	}
	
}
