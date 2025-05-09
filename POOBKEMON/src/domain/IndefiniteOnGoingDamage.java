package domain;

public class IndefiniteOnGoingDamage extends IndefiniteDurationStatus implements Damaging{
	int damage;
	
	public IndefiniteOnGoingDamage(String name, boolean removeTemporarilyOnSwitch,boolean removeOnSwitch,int damage) {
		super(name,removeTemporarilyOnSwitch, removeOnSwitch);
		this.damage = damage;
	}
	
	@Override
	public Status copy() {
		return new IndefiniteOnGoingDamage(name,removeTemporarilyOnSwitch,removeOnSwitch,damage);
	}

	@Override
	protected void statusLogic(Pokemon pokemon) {
		makePokemonGetHurt(pokemon, damage);
	}
}
