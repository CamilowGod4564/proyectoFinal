package domain;

public class IndefiniteOnGoingDamage extends IndefiniteDurationStatus implements Damaging{
	int damage;
	
	public IndefiniteOnGoingDamage(String name, boolean removeTemporarilyOnSwitch,boolean removeOnSwitch,double chanceOfLoseStatus,int damage) {
		super(name,removeTemporarilyOnSwitch, removeOnSwitch, chanceOfLoseStatus);
		this.damage = damage;
	}
	
	@Override
	public Status copy() {
		return new IndefiniteOnGoingDamage(name,removeTemporarilyOnSwitch,removeOnSwitch,chanceOfLoseStatus, damage);
	}

	@Override
	protected void statusLogic(Pokemon pokemon) {
		makePokemonGetHurt(pokemon, damage);
	}
}
