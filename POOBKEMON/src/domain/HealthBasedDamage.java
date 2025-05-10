package domain;

public class HealthBasedDamage extends IndefiniteOnGoingDamage{
	double proportion;
	
	public HealthBasedDamage(String name, boolean removeTemporarilyOnSwitch, boolean removeOnSwitch,double chanceOfLoseStatus,double proportion) {
		super(name, removeTemporarilyOnSwitch, removeOnSwitch,chanceOfLoseStatus, 0);
		this.proportion = proportion;
	}
	
	@Override
	protected void statusLogic(Pokemon pokemon) {
		damage= (int) (pokemon.getHealth() / proportion);
		makePokemonGetHurt(pokemon, damage);
	}
	
}
