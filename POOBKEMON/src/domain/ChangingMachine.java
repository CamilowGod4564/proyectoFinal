package domain;

public class ChangingMachine extends Machine {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean attackTurn = true;

    public ChangingMachine(String name) {
        super(name);
    }

    @Override
    public void decideAction(Player enemy) {
        Pokemon self = getPlayingPokemon();
        Pokemon target = enemy.getPlayingPokemon();

        String bestMove = getBestMove(self, target);

       
        if (attackTurn && bestMove != null) {
            usePokemonMovement(bestMove, target);
        } else {
        	if (self.getHealth() < 25 && !getBag().getItems().isEmpty()) {
                useItem(getBag().getItems().firstKey());
            } else if (bestMove != null) {
                usePokemonMovement(bestMove, target); 
            }
        }

        attackTurn = !attackTurn;
    }
}