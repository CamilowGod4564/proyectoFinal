package domain;

import java.util.*;

public class dsadas {
	public static void main(String[] args) {
        try {
        	PoobkemonLoader p = new PoobkemonLoader();
            HashMap<String, Type> tipos = p.loadTypes("src/game_info/TypesPoobkemon.txt");
            HashMap<String, Status> statuses = p.loadStatuses("src/game_info/StatusesPoobkemon.txt");
            HashMap<String, Movement> movements = p.loadMovements(tipos,statuses,"src/game_info/MovementsPoobkemon.txt");
            HashMap<String, Item> items = p.loadItems("src/game_info/ItemsPoobkemon.txt");
            TreeMap<String,Pokemon> pokemons = p.loadPokemons("src/game_info/PokemonsPoobkemon.txt",movements,tipos);
 
            for (String tipo : tipos.keySet()) {
                System.out.println(tipo + ": " + tipos.get(tipo));
            }
            for (String status : statuses.keySet()) {
                System.out.println(status + ": " + statuses.get(status));
            }
            for (String status : movements.keySet()) {
                System.out.println(status + ": " + movements.get(status));
            }
            for (String status : items.keySet()) {
                System.out.println(status + ": " + items.get(status));
            }
            for (String status : pokemons.keySet()) {
                System.out.println(status + ": " + pokemons.get(status));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}