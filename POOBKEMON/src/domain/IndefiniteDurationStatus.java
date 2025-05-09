package domain;

import java.util.Random;

public abstract class IndefiniteDurationStatus extends Status {
	
	double chanceOfLoseStatus;
	Random random = new Random();
	
	public IndefiniteDurationStatus(String name, boolean removeTemporarilyOnSwitch,boolean removeOnSwitch) {
		super(name, 1, removeTemporarilyOnSwitch, removeOnSwitch);
	}
	@Override
	protected void makeStatusWork(Pokemon pokemon) {
		if(isFinished()) {
			finishStatus(pokemon);
		}else {
			statusLogic(pokemon);
		}
		
	}

	public boolean isFinished() {
		return random.nextDouble() < chanceOfLoseStatus;
	}
	
}
