package domain;

public class IndefiniteOnGoingDamage extends IndefiniteDurationStatus{
	int damage;
	
	public IndefiniteOnGoingDamage(String name, boolean removeTemporarilyOnSwitch,boolean removeOnSwitch,int damage) {
		super(name,removeTemporarilyOnSwitch, removeOnSwitch);
		this.damage = damage;
	}

	@Override
	public void apply(Pokemon pokemon) {
		if(!isFreezed) {
		pokemon.receiveDamage(damage);
		}
	}
	
	@Override
	public Status copy() {
		return new IndefiniteOnGoingDamage(name,removeTemporarilyOnSwitch,removeOnSwitch,damage);
	}
}
