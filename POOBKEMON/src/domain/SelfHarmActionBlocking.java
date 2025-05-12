package domain;

import java.util.Random;

public class SelfHarmActionBlocking extends FiniteActionBlocking implements Damaging {
	
	protected double chanceOfApplying;
	protected Random random = new Random();
	boolean wasApplied;
	
	public SelfHarmActionBlocking(String name, int maxDuration, boolean removeTemporarilyOnSwitch,boolean removeOnSwitch, int minDuration, double chanceOfApplying,String inmuneType) {
		super(name, maxDuration, removeTemporarilyOnSwitch, removeOnSwitch, minDuration,inmuneType);
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
			makePokemonUseless(pokemon, inmuneType);
			int damageToBeApplied =(int) (((((2.0*pokemon.getLevel())/5.0)+2)*40.0*((double)pokemon.getAttack()/pokemon.getDefense()))/50.0)+2;
			makePokemonGetHurt(pokemon,damageToBeApplied , inmuneType);
			wasApplied = true;
		}
	}
	@Override
	public Status copy() {
		return new SelfHarmActionBlocking(name, duration, removeTemporarilyOnSwitch, removeOnSwitch, minDuration, chanceOfApplying, inmuneType);
	}
}

