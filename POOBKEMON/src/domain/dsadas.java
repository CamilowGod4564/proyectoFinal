package domain;


import java.util.*;

public class dsadas {
	public static void main(String[] args) {
        try {
        	PoobkemonLoader p = new PoobkemonLoader();
            HashMap<String, Type> tipos = p.loadTypes("TypesPoobkemon.txt");
            HashMap<String, Status> statuses = p.loadStatuses("StatusesPoobkemon.txt");
            HashMap<String, Movement> movements = p.loadMovements(tipos,statuses,"MovementsPoobkemon.txt");
            TreeMap<String,Pokemon> pokemons = p.loadPokemons("PokemonsPoobkemon.txt",movements,tipos);
            
            for (String status : statuses.keySet()) {
                System.out.println(status + ": " + statuses.get(status));
            }
            
            for (String status : movements.keySet()) {
                System.out.println(status + ": " + movements.get(status));
            }
            for (String status : pokemons.keySet()) {
                System.out.println(status + ": " + pokemons.get(status));
            }
            
            Pokemon pokemon_prueba =  pokemons.get("SCEPTILE");
            System.out.println(pokemon_prueba.getCurrentHealth());
            pokemons.get("PIKACHU").useMovement("Thunderbolt", pokemon_prueba);
            System.out.println(pokemon_prueba.getCurrentHealth());
            for(String s : pokemon_prueba.getStatuses().keySet()) {
            	System.out.println(s);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        	
	}
	
	
}