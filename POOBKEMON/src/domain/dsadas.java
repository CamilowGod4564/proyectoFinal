package domain;


import java.util.*;

public class dsadas {
	public static void main(String[] args) {
        try {
        	PoobkemonGame g = new PoobkemonGame();
        	PoobkemonLoader p = new PoobkemonLoader();
            HashMap<String, Type> tipos = p.loadTypes("TypesPoobkemon.txt");
            HashMap<String, Status> statuses = p.loadStatuses("StatusesPoobkemon.txt");
            HashMap<String, Movement> movements = p.loadMovements(tipos,statuses,"MovementsPoobkemon.txt");
            TreeMap<String,Pokemon> pokemons = p.loadPokemons("PokemonsPoobkemon.txt",movements,tipos);
            HashMap<String, Item> items = p.loadItems("ItemsPoobkemon.txt");
            
            //for (String status : items.keySet()) {
            //	System.out.println(status + ": " + items.get(status));
            //}
            Item healingPotion = items.get("SUPERPOTION");
            System.out.println(healingPotion);
            
            //for (String status : tipos.keySet()) {
            //    System.out.println(status + ": " + tipos.get(status));
            //}
            
            //for (String status : statuses.keySet()) {
            //    System.out.println(status + ": " + statuses.get(status));
            //}
            
            //for (String status : movements.keySet()) {
            //   System.out.println(status + ": " + movements.get(status));
            //}
            //for (String status : pokemons.keySet()) {
            //   System.out.println(status + ": " + pokemons.get(status).getMovements());
            //}
            
            System.out.print(pokemons.get("GARDEVOIR").getCurrentHealth());
            pokemons.get("SCEPTILE").useMovement("DRAGON CLAW", pokemons.get("PIKACHU"));
       
            
            g.setDuelMode("Normal");
            
            g.newPlayer("Nikolas");
            g.newPlayer("Camilo");
            
            
            g.playerSelectPokemonForTeam("Nikolas","BLAZIKEN");
            g.playerSelectPokemonForTeam("Camilo","Pikachu");
            
            g.confirmPlayers();
            
            while( g.getWinner() == null) {
            	
            	g.nextTurn();
                
                System.out.println(g.getPlayerPokemons().get(0).getName());
                System.out.println(g.getPlayerPokemons().get(0).getCurrentHealth());
                
                g.nextTurn();
                
                System.out.println(g.getCurrentPokemon());
                System.out.println(g.getPlayerPokemons().get(0).getMovements().firstKey());
                g.attack(g.getCurrentPokemonMovements().get(0));
                
                g.nextTurn();
                
                System.out.println(g.getCurrentPokemon());
                System.out.println(g.getPlayerPokemons().get(0).getCurrentHealth());
                
                g.nextTurn();
            }
            System.out.println(g.getWinner());
          
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}