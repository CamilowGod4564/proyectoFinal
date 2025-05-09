package domain;

import java.util.Random;

public class SelfHarmActionBlocking extends FiniteActionBlocking {
	
	double chanceOfApplying;
	Random random = new Random();
	
	public SelfHarmActionBlocking(String name, int maxDuration, boolean removeTemporarilyOnSwitch,boolean removeOnSwitch, int minDuration, double chanceOfApplying) {
		super(name, maxDuration, removeTemporarilyOnSwitch, removeOnSwitch, minDuration);
		this.chanceOfApplying = chanceOfApplying;
	}
	@Override
	public void apply(Pokemon pokemon) {
		if(isGonnaBeApplied()) {
			if(!pokemon.getIsGonnaFail() && failChance()) {
				pokemon.turnGonnaFail();
				pokemon.receiveDamage((int)(50*pokemon.getAttack())/pokemon.getDefense());
				//problema pq se queda asi no se que hacer
			}
		}else {
			finishStatus(pokemon);
		}
	}
	
	private boolean failChance() {
		return random.nextDouble()<chanceOfApplying;
	}
	@Override
	public void finishStatus(Pokemon pokemon) {
		pokemon.turnGonnaFail();
		pokemon.delStatus(this);
	}
}
