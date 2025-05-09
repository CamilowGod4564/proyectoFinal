package domain;

public class FiniteActionBlocking extends FiniteDurationStatus{
	


	public FiniteActionBlocking(String name,int maxDuration, boolean removeTemporarilyOnSwitch,boolean removeOnSwitch, int minDuration ) {
		super(name, maxDuration, removeOnSwitch, removeOnSwitch, minDuration);
	}

	@Override
	public void finishStatus(Pokemon pokemon) {
		pokemon.turnGonnaFail();
		pokemon.delStatus(this);
	}

	@Override
	public Status copy() {
		
		return null;
	}

	@Override
	public void apply(Pokemon pokemon) {
		if(isGonnaBeApplied()) {
			if(!pokemon.getIsGonnaFail()) {
				pokemon.turnGonnaFail();
			}
		}else {
			finishStatus(pokemon);
		}
	}
}
