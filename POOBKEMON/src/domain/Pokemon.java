package domain;

import java.util.*;

public class Pokemon {
	private String name;
	private String pokedexNumber;
	private int level;
	private int health;
	private int currentHealth;
	private int attack;
	private int currentAttack;
	private TreeMap<String,Status> statuses; 
	private int defense;
	private int specialAttack;
	private int specialDefense;
	private boolean gonnaFail;
	private boolean canEscape;
	private TreeMap<String,Movement> movements;
	private ArrayList<Type> types;
	
	
	 public Pokemon(String pokedexNumber,String name, int level, int health, int attack, int defense, int specialAttack, int specialDefense,ArrayList<Type> types) {
	        this.pokedexNumber = pokedexNumber;
		 	this.name = name;
	        this.level = level;
	        this.health = health;
	        this.currentHealth = health; 
	        this.attack = attack;
	        this.statuses = new TreeMap<>(); 
	        this.defense = defense;
	        this.specialAttack = specialAttack;
	        this.specialDefense = specialDefense;
	        this.gonnaFail = false;
	        this.canEscape = true;
	        this.movements = new TreeMap<>();
	        this.types = types;
	        this.currentAttack = attack;
	    }
	 
	 
	public String getPokedexNumber() {
		return pokedexNumber;
	}


	public Pokemon copy() {
		Pokemon clone = new Pokemon(pokedexNumber,name,level, health, attack, defense,specialAttack,specialDefense,types);
		for(Movement m : movements.values()) {
			clone.addMovement(m.copy());
		}
		return clone;
	}
	
	public boolean isFainteed() {
		if (currentHealth <= 0) {
			return true;
		}
		return false;
	}
	
	public void receiveDamage(int damage) {
		if(currentHealth <= damage) {
			currentHealth = 0;
		}else {
		currentHealth -= damage;
		}
	}
	
	public void addStatus(Status status) {
		statuses.put(status.getName(),status);
		
	}
	public void delStatus(Status status) {
		statuses.remove(status.getName());
		
	}
	public void alterDefense(int defense) {
		this.defense += defense;
	}

	public int getLevel() {
		return level;
	}


	public int getHealth() {
		return health;
	}
	
	public int getCurrentHealth() {
		return currentHealth;
	}

	public int getDefense() {
		return defense;
	}

	public void useMovement(String movement,Pokemon targetPokemon){
		if(!gonnaFail) {
			movements.get(movement.toUpperCase()).makeMovement(this,targetPokemon);
		}
		//try catch
	}
	
	public void addMovement(Movement movement) {
		for(Type t:types) {
		if (movement.getType().isStrongAgainst(t.getTypeName()) && movements.size() < 5) {
			//raise exception
		}
	}
		movements.put(movement.getName(), movement);
	}
	public void removeMovement(String movement) {
		movements.remove(movement);
	}
	public void cure(int hp) {
		if(currentHealth + hp < health) {
			currentHealth += hp;
		}else {
			currentHealth = health;
		}
	}

	public ArrayList<Type> getTypes() {
		return types;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAttack() {
		return currentAttack;
	}


	public int getSpecialAttack() {
		return specialAttack;
	}


	public int getSpecialDefense() {
		return specialDefense;
	}

	public void turnGonnaFail() {
		gonnaFail = !gonnaFail;
	}


	public boolean getIsGonnaFail() {
		return gonnaFail;
	}
	
	public void turnCanEscape() {
		canEscape = !canEscape;
	}


	public boolean getCanEscape() {
		return canEscape;
	}

	
	public TreeMap<String,Status> getStatuses(){
		return statuses;
	}
	
	public TreeMap<String,Movement> getMovements() {
		return movements;
	}
	
	public void retireFromBattle() {
		for(Status s : statuses.values()) {
			s.removeOnSwitch(this);
		}
	}
	public void joinBattle() {
		for(Status s : statuses.values()) {
			s.reStartStatus();
		}
	}
	public void applyActualStatuses() {
		for(Status s: statuses.values()) {
			s.apply(this);
		}
	}
	public void setNewAttack(int newAttack) {
		currentAttack = newAttack;
	}
	
	public int getRealAttack() {
		return attack;
	}
	
	
}
