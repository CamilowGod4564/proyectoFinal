package domain;

public class WeakenDamageHealthBasedDamage extends HealthBasedDamage{
	
	double weakenProportion;
	
	public WeakenDamageHealthBasedDamage(String name, boolean removeTemporarilyOnSwitch, boolean removeOnSwitch,
			double chanceOfLoseStatus, String typeInmuneToStatus, double proportion,double weakenProportion) {
		super(name, removeTemporarilyOnSwitch, removeOnSwitch, chanceOfLoseStatus, typeInmuneToStatus, proportion);
		this.weakenProportion =weakenProportion;
	}
	
	@Override
	public void statusLogic(Pokemon pokemon) {
		super.statusLogic(pokemon);
		pokemon.setNewAttack((int) (pokemon.getRealAttack()*weakenProportion));
	}
	
	@Override 
	public void finishStatus(Pokemon pokemon) {
		pokemon.setNewAttack(pokemon.getRealAttack());
		super.finishStatus(pokemon);
	}
}
