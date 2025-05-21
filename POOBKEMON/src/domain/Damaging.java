package domain;


public interface Damaging {
	
	public default void makePokemonGetHurt(Pokemon pokemon,int damage,String typeInmuneToDamaging) {
		boolean shouldBeApplied = true;
		for(Type t : pokemon.getTypes()) {
			if(t.getTypeName() == typeInmuneToDamaging) {
				shouldBeApplied = false;
			}
		}
		if (shouldBeApplied){
			pokemon.receiveDamage(damage);
		}
	}
}
