package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.TreeMap;

class BagTest {

    private Bag bag;
    private Pokemon dummyPokemon;
    private Item healingPotion;

    @BeforeEach
    void setUp() throws ClassNotFoundException, FileNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
        bag = new Bag();
        PoobkemonLoader p = new PoobkemonLoader();
        HashMap<String, Type> tipos = p.loadTypes("TypesPoobkemon.txt");
        HashMap<String, Status> statuses = p.loadStatuses("StatusesPoobkemon.txt");
        HashMap<String, Movement> movements = p.loadMovements(tipos,statuses,"MovementsPoobkemon.txt");
        TreeMap<String,Pokemon> pokemons = p.loadPokemons("PokemonsPoobkemon.txt",movements,tipos);
        HashMap<String, Item> items = p.loadItems("ItemsPoobkemon.txt");
        
        healingPotion = items.get("SUPERPOTION");
        dummyPokemon = pokemons.get("PIKACHU").copy();

    }

    @Test
    void testAddItemIncreasesCount() {
        bag.addItem(healingPotion);
        assertEquals(1, bag.getItemQuantity("SUPERPOTION"));
    }

    @Test
    void testAddMultipleItems() {
        bag.addItem(healingPotion);
        bag.addItem(healingPotion);
        assertEquals(2, bag.getItemQuantity("superpotion"));
    }

    @Test
    void testDelItemDecreasesCount() {
        bag.addItem(healingPotion);
        bag.addItem(healingPotion);
        bag.delItem("superpotion");
        assertEquals(1, bag.getItemQuantity("superpotion"));
    }

    @Test
    void testDelItemRemovesItemCompletely() {
        bag.addItem(healingPotion);
        bag.delItem("superpotion");
        assertEquals(0, bag.getItemQuantity("superpotion"));
        assertFalse(bag.getItems().containsKey("superpotion"));
    }

    @Test
    void testUseItemAppliesEffectAndReducesCount() {
        bag.addItem(healingPotion);
        bag.useItem("superpotion", dummyPokemon);
        assertEquals(0, bag.getItemQuantity("superpotion"));
        assertEquals(dummyPokemon.getHealth(), dummyPokemon.getCurrentHealth()); 
    }

    @Test
    void testCaseInsensitiveItemHandling() {
        bag.addItem(healingPotion);
        assertEquals(1, bag.getItemQuantity("superpotion"));
    }
    
}

