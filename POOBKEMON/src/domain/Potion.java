package domain;

public class Potion extends HealthItem {

	public Potion(String name,int recoverPoints) {
		super(name,recoverPoints);
	}

	@Override
	public void use(Pokemon pokemon) {
		if(pokemon.getCurrentHealth() == 0 ) {
			//excepcion
		}else {
			pokemon.cure(recoverPoints);
		}
	}

}
