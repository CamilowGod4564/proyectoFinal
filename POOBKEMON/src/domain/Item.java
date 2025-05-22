package domain;

import java.io.Serializable;

public interface Item extends Serializable{
	public void use(Pokemon pokemon);
	public String getName();
}


//ya se agrego todo y los jugadores estan listos 
//en el momento que se presione continuar en esa vista
//se tiene que hace un confirmPlayers y despues hacer nestPanel(BattlePanel).battleReady()
