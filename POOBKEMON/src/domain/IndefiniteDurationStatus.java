package domain;

public abstract class IndefiniteDurationStatus extends Status {
		
	public IndefiniteDurationStatus(String name, boolean removeTemporarilyOnSwitch,boolean removeOnSwitch) {
		super(name, 0, removeTemporarilyOnSwitch, removeOnSwitch);
	}
	
}
