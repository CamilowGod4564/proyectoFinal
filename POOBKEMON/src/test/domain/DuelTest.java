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

class DuelTest {

    private Duel duel;
    private Pokemon p1Pokemon;
    private Pokemon p2Pokemon;
    private Item testItem;

    @BeforeEach
    void setUp() throws ClassNotFoundException, FileNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
        duel = new NormalDuel();
        duel.createPlayer("Ash");
        duel.createPlayer("Gary");
        
        PoobkemonLoader p = new PoobkemonLoader();
        HashMap<String, Type> tipos = p.loadTypes("TypesPoobkemon.txt");
        HashMap<String, Status> statuses = p.loadStatuses("StatusesPoobkemon.txt");
        HashMap<String, Movement> movements = p.loadMovements(tipos,statuses,"MovementsPoobkemon.txt");
        TreeMap<String,Pokemon> pokemons = p.loadPokemons("PokemonsPoobkemon.txt",movements,tipos);
        HashMap<String, Item> items = p.loadItems("ItemsPoobkemon.txt");
        
        p1Pokemon = pokemons.get("BULBASAUR").copy();
        p2Pokemon = pokemons.get("PIKACHU").copy();

        duel.playerSelectPokemon("Ash", p1Pokemon);
        duel.playerSelectPokemon("Gary", p2Pokemon);

        testItem = items.get("SUPERPOTION");

        duel.playerSelectItem("Ash", testItem);
        duel.assignPlayers(); 
    }

    @Test
    void testCreatePlayers() {
        assertNotNull(duel.getPlayingPlayer());
        assertNotNull(duel.getWaitingPlayer());
    }

    @Test
    void testSwitchPlayer() {
        Player original = duel.getPlayingPlayer();
        duel.changePlayingPlayer();
        assertNotEquals(original, duel.getPlayingPlayer());
    }

    @Test
    void testPlayerSurrenderAssignsWinner() {
        Player current = duel.getPlayingPlayer();
        Player other = duel.getWaitingPlayer();

        duel.playerSurrender();

        assertEquals(other, duel.getWinner());
    }

    @Test
    void testPlayerUseItemAffectsHealth() {
        Player p = duel.getPlayingPlayer();
        p.getPlayingPokemon().receiveDamage(55);
        int before = p.getPlayingPokemon().getCurrentHealth();
        duel.playerUseItem("SUPERPOTION");
        int after = p.getPlayingPokemon().getCurrentHealth();

        assertTrue(after >= before);
    }

    @Test
    void testCleanPokemons() {
        duel.cleanPokemons();
        assertEquals(0, duel.getPlayerTeam().size());
        duel.changePlayingPlayer();
        assertEquals(0, duel.getPlayerTeam().size());
    }

    @Test
    void testCleanItems() {
        duel.cleanItems();
        assertEquals(0, duel.p1.getBag().getItems().size());
        assertEquals(0, duel.p2.getBag().getItems().size());
    }

    @Test
    void testChangePokemonAutomaticallyFaints() {
        p1Pokemon.receiveDamage(9999);
        duel.changePokemonAutomatically(duel.p1);
        assertEquals(duel.getWinner(), duel.p2);
    }

    @Test
    void testAddAndRemoveItem() {
        duel.playerDeselectItem("Ash", "Potion");
        assertEquals(0, duel.p1.getBag().getItemQuantity("Potion"));
    }
}