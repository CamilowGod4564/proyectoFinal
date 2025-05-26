package domain;

public class DefensiveMachine extends Machine {

    public DefensiveMachine(String name) {
        super(name);
    }

    @Override
    public void decideAction(Player enemy) {
        Pokemon self = getPlayingPokemon();
        Pokemon target = enemy.getPlayingPokemon();
        if (self == null || target == null || self.isFainteed()) return;

        if (self.getHealth() < 25 && !getBag().getItems().isEmpty()) {
            useItem(getBag().getItems().firstKey());
            return;
        }

        String bestMove = getBestMove(self, target);
        if (bestMove != null) {
            usePokemonMovement(bestMove, target);
        }
    }
}