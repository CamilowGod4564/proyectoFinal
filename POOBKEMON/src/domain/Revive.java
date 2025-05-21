package domain;

public class Revive extends HealthItem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Revive(String name) {
		super(name,0);
	}

	@Override
	public void use(Pokemon pokemon) {
		if(pokemon.getCurrentHealth() == 0) {
			pokemon.cure((int)pokemon.getHealth()/2);
		}else {
			//exception
		}
	}
}
