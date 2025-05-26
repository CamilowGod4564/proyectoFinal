package test.domain;

import domain.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PokemonTest {

    private Pokemon pikachu;
    private Type electric;

    

    @Test
    public void testReceiveDamage() {
        pikachu.receiveDamage(30);
        assertEquals(70, pikachu.getCurrentHealth());
    }

    @Test
    public void testReceiveFatalDamage() {
        pikachu.receiveDamage(200);
        assertEquals(0, pikachu.getCurrentHealth());
        assertTrue(pikachu.isFainteed());
    }

    @Test
    public void testCure() {
        pikachu.receiveDamage(50);
        pikachu.cure(30);
        assertEquals(80, pikachu.getCurrentHealth());
    }

    @Test
    public void testOverheal() {
        pikachu.cure(50);
        assertEquals(100, pikachu.getCurrentHealth());
    }

    

    @Test
    public void testAddMovement() {
        Movement testMove = new Movement("TestMove", 10, electric) {
            @Override
            public void makeMovement(Pokemon pokemon, Pokemon targetPokemon) {}

            @Override
            public Movement copy() {
                return this;
            }

            @Override
            public double evaluateEffectiveness(Pokemon self, Pokemon target) {
                return 1.0;
            }
        };

        pikachu.addMovement(testMove);
        assertTrue(pikachu.getMovements().containsKey("TestMove"));
    }

    @Test
    public void testUseMovement() {
        Pokemon target = new Pokemon("001", "Bulbasaur", 10, 100, 50, 40, 60, 50, new ArrayList<>(List.of(electric)));

        Movement damageMove = new Movement("Shock", 10, electric) {
            @Override
            public void makeMovement(Pokemon pokemon, Pokemon targetPokemon) {
                targetPokemon.receiveDamage(20);
            }

            @Override
            public Movement copy() {
                return this;
            }

            @Override
            public double evaluateEffectiveness(Pokemon self, Pokemon target) {
                return 1.0;
            }
        };

        pikachu.addMovement(damageMove);
        pikachu.useMovement("Shock", target);

        assertEquals(80, target.getCurrentHealth());  // 100 - 20 = 80
    }

    @Test
    public void testUseMovementFailsWhenGonnaFail() {
        Pokemon target = new Pokemon("001", "Bulbasaur", 10, 100, 50, 40, 60, 50, new ArrayList<>(List.of(electric)));

        Movement testMove = new Movement("Shock", 10, electric) {
            @Override
            public void makeMovement(Pokemon pokemon, Pokemon targetPokemon) {
                targetPokemon.receiveDamage(20);
            }

            @Override
            public Movement copy() {
                return this;
            }

            @Override
            public double evaluateEffectiveness(Pokemon self, Pokemon target) {
                return 1.0;
            }
        };

        pikachu.addMovement(testMove);
        pikachu.turnGonnaFail();  // ahora está en modo "gonnaFail"
        pikachu.useMovement("Shock", target);

        assertEquals(100, target.getCurrentHealth());  // no se debería aplicar daño
    }

    @Test
    public void testCopy() {
        Movement move = new Movement("Thunderbolt", 10, electric) {
            @Override
            public void makeMovement(Pokemon pokemon, Pokemon targetPokemon) {}
            @Override
            public Movement copy() {
                return this;
            }
            @Override
            public double evaluateEffectiveness(Pokemon self, Pokemon target) {
                return 1;
            }
        };
        pikachu.addMovement(move);

        Pokemon copy = pikachu.copy();

        assertEquals(pikachu.getName(), copy.getName());
        assertNotSame(pikachu, copy);
        assertEquals(pikachu.getMovements().size(), copy.getMovements().size());
    }

    @Test
    public void testSetNewAttack() {
        pikachu.setNewAttack(75);
        assertEquals(75, pikachu.getAttack());
    }

    @Test
    public void testTurnGonnaFail() {
        boolean original = pikachu.getIsGonnaFail();
        pikachu.turnGonnaFail();
        assertNotEquals(original, pikachu.getIsGonnaFail());
    }

    @Test
    public void testTurnCanEscape() {
        boolean original = pikachu.getCanEscape();
        pikachu.turnCanEscape();
        assertNotEquals(original, pikachu.getCanEscape());
    }

    
}
