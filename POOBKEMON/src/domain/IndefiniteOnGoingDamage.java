package domain;

public class IndefiniteOnGoingDamage extends IndefiniteDurationStatus implements Damaging{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int damage;
	
	public IndefiniteOnGoingDamage(String name, boolean removeTemporarilyOnSwitch,boolean removeOnSwitch,double chanceOfLoseStatus,int damage,String inmuneType) {
		super(name,removeTemporarilyOnSwitch, removeOnSwitch, chanceOfLoseStatus,inmuneType);
		this.damage = damage;
	}
	
	@Override
	public Status copy() {
		return new IndefiniteOnGoingDamage(name,removeTemporarilyOnSwitch,removeOnSwitch,chanceOfLoseStatus, damage,inmuneType);
	}

	@Override
	protected void statusLogic(Pokemon pokemon) {
		makePokemonGetHurt(pokemon, damage,inmuneType);
	}
}
