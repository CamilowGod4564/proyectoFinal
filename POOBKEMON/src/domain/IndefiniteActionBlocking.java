package domain;
import java.util.Random;

public class IndefiniteActionBlocking extends IndefiniteDurationStatus{
	double chanceOfLoseStatus;
	Random random = new Random();

	public IndefiniteActionBlocking(String name, boolean removeTemporarilyOnSwitch, boolean removeOnSwitch,double chanceOfLoseStatus) {
		super(name, removeTemporarilyOnSwitch, removeOnSwitch);
		this.chanceOfLoseStatus = chanceOfLoseStatus;
	}

	@Override
	public Status copy() {
		return new IndefiniteActionBlocking(name, removeTemporarilyOnSwitch,removeOnSwitch,chanceOfLoseStatus);
	}

	@Override
	public void apply(Pokemon pokemon) {
		if(!pokemon.getIsGonnaFail()) {
			pokemon.turnGonnaFail();
		}else {
			finishStatus(pokemon);
		}
	}
	
	@Override
	public void finishStatus(Pokemon pokemon) {
		 if( random.nextDouble() < chanceOfLoseStatus ) {
			 pokemon.turnGonnaFail();
			 pokemon.delStatus(this);
		 }
	}
			
}
