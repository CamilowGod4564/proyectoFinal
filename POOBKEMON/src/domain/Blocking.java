package domain;

public interface Blocking {
	public default void makePokemonUseless(Pokemon pokemon,String inmuneType) {
		boolean shouldBeApplied = true;
		
		for(Type t : pokemon.getTypes()) {
			if(t.getTypeName() == inmuneType) {
				shouldBeApplied = false;
			}
		}
		if(!pokemon.getIsGonnaFail() && shouldBeApplied) {
			pokemon.turnGonnaFail();
		}
	}
}
