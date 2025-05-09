package domain;

public abstract class Movement {

	protected String name;
	protected int pp;
	protected int currentPP;
	protected Type type;
	
	/**
	 * @param name
	 * @param pp
	 * @param currentPP
	 */
	public Movement(String name, int pp,Type type) {
		this.name = name;
		this.pp = pp;
		this.currentPP = pp;
		this.type= type;
	}

	public abstract void makeMovement(Pokemon pokemon, Pokemon targetPokemon);

	public String getName() {
		return name;
	}

	public int getCurrentPP() {
		return currentPP;
	}
	public void losePP() {
		currentPP -=1;
	}
	public Type getType() {
		return type;
	}

	protected abstract Movement copy();

}
