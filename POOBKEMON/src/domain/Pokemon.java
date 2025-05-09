package domain;

import java.util.*;

public class Pokemon {
	private String name;
	private int level;
	private int health;
	private int currentHealth;
	private int attack;
	private TreeMap<String,Status> statuses; 
	private int defense;
	private int specialAttack;
	private int specialDefense;
	private boolean gonnaFail;
	private TreeMap<String,Movement> movements;
	private ArrayList<Type> types;
	
	
	 public Pokemon(String name, int level, int health, int attack, int defense, int specialAttack, int specialDefense,ArrayList<Type> types) {
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
	        this.movements = new TreeMap<>();
	        this.types = types;
	    }
	 
	 
	public Pokemon copy() {
		Pokemon p = new Pokemon(name,level, health, attack, defense,specialAttack,specialDefense,types);
		return p;
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
		if (movement.getType().isStrongAgainst(t.getTypeName())) {
			//raise exception
		}
	}
		movements.put(movement.getName(), movement);
	}
	public void removeMovement(Movement movement) {
		movements.remove(movement.getName());
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
		return attack;
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


	public TreeMap<String,Movement> getMovements() {
		return movements;
	}
	
	
}
