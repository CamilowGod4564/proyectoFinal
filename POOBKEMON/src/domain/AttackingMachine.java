package domain;

public class AttackingMachine extends Machine {

    public AttackingMachine(String name) {
        super(name);
    }

    @Override
    public void decideAction(Player enemy) {
        Pokemon self = getPlayingPokemon();
        Pokemon target = enemy.getPlayingPokemon();

        String bestMove = getBestMove(self, target);
        if (bestMove != null) {
            usePokemonMovement(bestMove, target);
        }
    }
}