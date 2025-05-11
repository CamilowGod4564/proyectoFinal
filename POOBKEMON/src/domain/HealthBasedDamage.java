package domain;

public class HealthBasedDamage extends IndefiniteOnGoingDamage{
	double proportion;
	
	public HealthBasedDamage(String name, boolean removeTemporarilyOnSwitch, boolean removeOnSwitch,double chanceOfLoseStatus,String typeInmuneToStatus,double proportion) {
		super(name, removeTemporarilyOnSwitch, removeOnSwitch,chanceOfLoseStatus, 0,typeInmuneToStatus);
		this.proportion = proportion;
	}
	
	@Override
	protected void statusLogic(Pokemon pokemon) {
		damage= (int) (pokemon.getHealth() / proportion);
		makePokemonGetHurt(pokemon, damage,typeInmuneToStatus);
	}
	
}
