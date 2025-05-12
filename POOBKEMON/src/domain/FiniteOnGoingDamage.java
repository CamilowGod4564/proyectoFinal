package domain;

public class FiniteOnGoingDamage extends FiniteDurationStatus implements Damaging{
	
	int damage;

	public FiniteOnGoingDamage(String name, int maxDuration, boolean removeTemporarilyOnSwitch, boolean removeOnSwitch,int minDuration,int damage,String inmuneType) {
		super(name, maxDuration, removeTemporarilyOnSwitch, removeOnSwitch, minDuration,inmuneType);
		this.damage = damage;
	}

	@Override
	public Status copy() {
		return new  FiniteOnGoingDamage(name, duration, removeTemporarilyOnSwitch, removeOnSwitch,minDuration,damage,inmuneType) ;
	}

	@Override
	protected void statusLogic(Pokemon pokemon) {
		makePokemonGetHurt(pokemon, damage,inmuneType);
	}

}
