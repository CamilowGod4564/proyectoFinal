package domain;

import java.util.Random;

public abstract class IndefiniteDurationStatus extends Status {
	
	protected double chanceOfLoseStatus;
	protected Random random = new Random();
	
	public IndefiniteDurationStatus(String name, boolean removeTemporarilyOnSwitch,boolean removeOnSwitch, double chanceOfLoseStatus) {
		super(name, 1, removeTemporarilyOnSwitch, removeOnSwitch);
		this.chanceOfLoseStatus = chanceOfLoseStatus;
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
