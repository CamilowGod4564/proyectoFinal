package domain;

import java.io.Serializable;

public abstract class Movement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	public int getTotalPP() {
		return pp;
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

	public abstract Movement copy();

	public abstract double evaluateEffectiveness(Pokemon self, Pokemon target);

}
