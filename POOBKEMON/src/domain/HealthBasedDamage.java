package domain;

public class HealthBasedDamage extends IndefiniteOnGoingDamage{
	double proportion;
	
	public HealthBasedDamage(String name, boolean removeTemporarilyOnSwitch, boolean removeOnSwitch,double proportion) {
		super(name, removeTemporarilyOnSwitch, removeOnSwitch,0);
		this.proportion = proportion;
	}
	
	@Override
	public void apply(Pokemon pokemon) {
		if(!isFreezed) {
			damage= (int) (pokemon.getHealth() / proportion);
			pokemon.receiveDamage(damage);
			}	
	}
}
