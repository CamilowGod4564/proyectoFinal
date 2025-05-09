package domain;

import java.util.*;

public class Type {
	String typeName;
	ArrayList<String> strongAgainst;
	ArrayList<String> inmuneAgainst;
	ArrayList<String> uneffectiveAgainst;
	/**
	 * @param typeName
	 * @param strongAgainst
	 * @param weakAgainst
	 */
	public Type(String typeName, ArrayList<String> strongAgainst, ArrayList<String> inmuneAgainst, ArrayList<String> uneffectiveAgainst ) {
		this.typeName = typeName;
		this.strongAgainst = strongAgainst;
		this.inmuneAgainst = inmuneAgainst;
		this.uneffectiveAgainst = uneffectiveAgainst;
	}
	
	public String getTypeName() {
		return typeName;
	}
	
	public boolean isStrongAgainst(String otherType) {
		return strongAgainst.contains(otherType);
	}
	
	public boolean isInmuneAgainst(String otherType) {
		return inmuneAgainst.contains(otherType);
	}
	
	public boolean isUnEffectiveAgainst(String otherType) {
		return uneffectiveAgainst.contains(otherType);
	}
}
