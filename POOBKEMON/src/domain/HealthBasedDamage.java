package domain;

public class HealthBasedDamage extends IndefiniteOnGoingDamage{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double proportion;
	
	public HealthBasedDamage(String name, boolean removeTemporarilyOnSwitch, boolean removeOnSwitch,double chanceOfLoseStatus,double proportion,String inmuneType) {
		super(name, removeTemporarilyOnSwitch, removeOnSwitch,chanceOfLoseStatus, 0,inmuneType);
		this.proportion = proportion;
	}
	
	@Override
	protected void statusLogic(Pokemon pokemon) {
		damage= (int) (pokemon.getHealth() / proportion);
		makePokemonGetHurt(pokemon, damage,inmuneType);
	}
	
}
