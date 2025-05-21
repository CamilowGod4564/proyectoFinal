package domain;

import java.util.Random;

public abstract class IndefiniteDurationStatus extends Status {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected double chanceOfLoseStatus;
	protected Random random = new Random();
	
	public IndefiniteDurationStatus(String name, boolean removeTemporarilyOnSwitch,boolean removeOnSwitch, double chanceOfLoseStatus,String inmuneType) {
		super(name, 1, removeTemporarilyOnSwitch, removeOnSwitch, inmuneType);
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
