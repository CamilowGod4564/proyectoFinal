package domain;

public abstract class HealthItem implements Item {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
