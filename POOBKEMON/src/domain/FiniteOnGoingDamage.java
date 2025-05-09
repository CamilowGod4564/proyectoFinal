package domain;

public class FiniteOnGoingDamage extends FiniteDurationStatus implements Damaging{
	
	int damage;

	public FiniteOnGoingDamage(String name, int maxDuration, boolean removeTemporarilyOnSwitch, boolean removeOnSwitch,int minDuration,int damage) {
		super(name, maxDuration, removeTemporarilyOnSwitch, removeOnSwitch, minDuration);
		this.damage = damage;
	}

	@Override
	public Status copy() {
		return new  FiniteOnGoingDamage(name, duration, removeTemporarilyOnSwitch, removeOnSwitch,minDuration,damage) ;
	}

	@Override
	protected void statusLogic(Pokemon pokemon) {
		makePokemonGetHurt(pokemon, damage);
	}

}
