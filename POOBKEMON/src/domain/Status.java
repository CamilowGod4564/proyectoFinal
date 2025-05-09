package domain;

public abstract class Status {
	protected String name;
	protected int duration;
	protected int currentDuration;
	boolean removeOnSwitch;
	boolean removeTemporarilyOnSwitch;
	protected boolean isFreezed;

	/**
	 * @param name
	 * @param duration
	 * @param actualDuration
	 */
	public Status(String name, int duration,boolean removeTemporarilyOnSwitch,boolean removeOnSwitch) {
		this.name = name;
		this.duration = duration;
		this.currentDuration = duration;
		this.removeOnSwitch = removeOnSwitch;
		this.removeTemporarilyOnSwitch = removeTemporarilyOnSwitch;
		this.isFreezed = false;
	}
	
	public String getName() {
		return name;
	}
	
	public void removeOnSwitch(Pokemon pokemon) {
		if(removeOnSwitch){ 
				finishStatus(pokemon);
		}else if(removeTemporarilyOnSwitch) {
			stopStatus();
		}else {
			//action doesnt need to be taken in place here 
		}
	}
	
	public void stopStatus() {
		//Make the status "sleep" if needed so the damage or effect isnt applied
		isFreezed = true;
	}
	public void reStartStatus() {
		//wakes up the status again to apply 
		isFreezed =false;
	}

	public void finishStatus(Pokemon pokemon) {
		pokemon.delStatus(this);
	}
	
	public abstract Status copy();
	public abstract void apply(Pokemon pokemon);

	
	
	
}

