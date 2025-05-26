package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

class PlayerTest {

    private Player player;
    private Pokemon bulbasaur;
    private Pokemon pikachu;
    private Item potion;

    @BeforeEach
    void setUp() throws ClassNotFoundException, FileNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
    	
    	player = new Player("Ash");
    	
        PoobkemonLoader p = new PoobkemonLoader();
        HashMap<String, Type> tipos = p.loadTypes("TypesPoobkemon.txt");
        HashMap<String, Status> statuses = p.loadStatuses("StatusesPoobkemon.txt");
        HashMap<String, Movement> movements = p.loadMovements(tipos,statuses,"MovementsPoobkemon.txt");
        TreeMap<String,Pokemon> pokemons = p.loadPokemons("PokemonsPoobkemon.txt",movements,tipos);
        HashMap<String, Item> items = p.loadItems("ItemsPoobkemon.txt");
        
        potion = items.get("SUPERPOTION");
        pikachu = pokemons.get("PIKACHU").copy();
        bulbasaur = pokemons.get("BULBASAUR").copy();
        
    }

    @Test
    void testGetName() {
        assertEquals("Ash", player.getName());
    }

    @Test
    void testAddPokemon() {
        player.addPokemon(bulbasaur);
        assertTrue(player.getPokemonTeam().contains(bulbasaur));
    }

    @Test
    void testAddPokemonSetsFirstAsPlaying() {
        player.addPokemon(bulbasaur);
        assertEquals(bulbasaur, player.getPlayingPokemon());
    }

    @Test
    void testSetPlayingPokemon() {
        player.addPokemon(bulbasaur);
        player.addPokemon(pikachu);
        player.setPlayingPokemon(pikachu);
        assertEquals(pikachu, player.getPlayingPokemon());
    }

    @Test
    void testChangePlayingPokemon() {

        player.addPokemon(bulbasaur);
        player.addPokemon(pikachu);
        player.setPlayingPokemon(bulbasaur);

        player.changePlayingPokemon(pikachu);

        assertEquals(pikachu, player.getPlayingPokemon());
    }

    @Test
    void testAddItemAndUseItem() {
        player.addPokemon(bulbasaur);
        player.getPlayingPokemon().receiveDamage(1);
        player.addItem(potion);
        player.useItem("Superpotion");

        assertEquals(player.getPlayingPokemon().getHealth(), player.getPlayingPokemon().getCurrentHealth());
    }

    @Test
    void testDelItem() {
        player.addItem(potion);
        player.delItem("Potion");
        assertEquals(0, player.getBag().getItemQuantity("Potion"));
    }

    @Test
    void testRemovePokemonRemovesFromTeam() {
        player.addPokemon(bulbasaur);
        player.removePokemon(bulbasaur);
        assertFalse(player.getPokemonTeam().contains(bulbasaur));
    }

    @Test
    void testRemovePlayingPokemonSetsToNull() {
        player.addPokemon(bulbasaur);
        player.removePokemon(bulbasaur);
        assertNull(player.getPlayingPokemon());
    }

    @Test
    void testCannotAddMoreThanSixPokemons() {
        for (int i = 0; i < 6; i++) {
            player.addPokemon(new Pokemon(String.valueOf(i), "Poke" + i, 1, 100, 10, 10, 10, 10, new ArrayList<>())); //cambiar esto
        }

        Pokemon extra = new Pokemon("9999", "randombro", 1, 100, 10, 10, 10, 10, new ArrayList<>());
        player.addPokemon(extra);

        assertEquals(6, player.getPokemonTeam().size());
        assertFalse(player.getPokemonTeam().contains(extra));
    }
}
