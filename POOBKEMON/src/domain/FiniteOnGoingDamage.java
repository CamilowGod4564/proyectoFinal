package domain;

public class FiniteOnGoingDamage extends FiniteDurationStatus implements Damaging{
	
	int damage;
	String typeInmuneToStatus;

	public FiniteOnGoingDamage(String name, int maxDuration, boolean removeTemporarilyOnSwitch, boolean removeOnSwitch,int minDuration,int damage,String typeInmuneToStatus) {
		super(name, maxDuration, removeTemporarilyOnSwitch, removeOnSwitch, minDuration);
		this.damage = damage;
		this.typeInmuneToStatus = typeInmuneToStatus;
	}

	@Override
	public Status copy() {
		return new  FiniteOnGoingDamage(name, duration, removeTemporarilyOnSwitch, removeOnSwitch,minDuration,damage,typeInmuneToStatus) ;
	}

	@Override
	protected void statusLogic(Pokemon pokemon) {
		makePokemonGetHurt(pokemon, damage,typeInmuneToStatus);
	}

}
