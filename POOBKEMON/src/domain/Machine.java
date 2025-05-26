package domain;

import java.util.Map;
import java.util.TreeMap;
import java.util.Comparator;

public abstract class Machine extends Player {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int turnCounter = 0;

    public Machine(String name) {
        super(name);
    }

    public abstract void decideAction(Player enemy);

    protected String getBestMove(Pokemon self, Pokemon target) {
        return self.getMovements().entrySet().stream()
            .max(Comparator.comparingDouble(entry -> entry.getValue().evaluateEffectiveness(self, target)))
            .map(Map.Entry::getKey)
            .orElse(null);
    }
}