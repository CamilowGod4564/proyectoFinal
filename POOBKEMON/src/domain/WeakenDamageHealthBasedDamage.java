package domain;

public class WeakenDamageHealthBasedDamage extends HealthBasedDamage{
	
	double weakenProportion;
	
	public WeakenDamageHealthBasedDamage(String name, boolean removeTemporarilyOnSwitch, boolean removeOnSwitch,
			double chanceOfLoseStatus, double proportion,String inmuneType,double weakenProportion) {
		super(name, removeTemporarilyOnSwitch, removeOnSwitch, chanceOfLoseStatus, proportion, inmuneType);
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
