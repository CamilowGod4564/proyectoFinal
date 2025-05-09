package domain;

public abstract class HealthItem implements Item {
	protected String name;
	protected int recoverPoints;
	/**
	 * @param recoverPoints
	 * @param name
	 */
	public HealthItem(String name,int recoverPoints) {
		this.recoverPoints = recoverPoints; 
		this.name = name;
		
	}
	public int getRecoverPoints() {
		return recoverPoints;
	}
	
	@Override
	public String getName() {
		return name;
	}
}
