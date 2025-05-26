package domain;

public class ExpertMachine extends Machine {

    public ExpertMachine(String name) {
        super(name);
    }

    @Override
    public void decideAction(Player enemy) {
        Pokemon self = getPlayingPokemon();
        Pokemon target = enemy.getPlayingPokemon();

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
