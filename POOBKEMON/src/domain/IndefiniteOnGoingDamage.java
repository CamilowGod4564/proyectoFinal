package domain;

public class IndefiniteOnGoingDamage extends IndefiniteDurationStatus implements Damaging{
	int damage;
	String typeInmuneToStatus;
	
	public IndefiniteOnGoingDamage(String name, boolean removeTemporarilyOnSwitch,boolean removeOnSwitch,double chanceOfLoseStatus,int damage,String typeInmuneToStatus) {
		super(name,removeTemporarilyOnSwitch, removeOnSwitch, chanceOfLoseStatus);
		this.damage = damage;
		this.typeInmuneToStatus = typeInmuneToStatus;
	}
	
	@Override
	public Status copy() {
		return new IndefiniteOnGoingDamage(name,removeTemporarilyOnSwitch,removeOnSwitch,chanceOfLoseStatus, damage,typeInmuneToStatus);
	}

	@Override
	protected void statusLogic(Pokemon pokemon) {
		makePokemonGetHurt(pokemon, damage,typeInmuneToStatus);
	}
}
