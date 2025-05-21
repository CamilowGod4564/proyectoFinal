package domain;

import java.io.Serializable;

public interface Item extends Serializable{
	public void use(Pokemon pokemon);
	public String getName();
}
