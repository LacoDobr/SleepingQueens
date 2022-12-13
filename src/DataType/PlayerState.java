package DataType;

import java.util.*;

public class PlayerState {
    private Map<Integer, Optional<Card>> cards;
    private Map<Integer, Queen> awokenGueens;

    public PlayerState(Map<Integer, Optional<Card>> cards, Map<Integer, Queen> awokenGueens) {
        this.cards = cards;
        this.awokenGueens = awokenGueens;
    }

    public Map<Integer, Optional<Card>> getCards() {
        return cards;
    }
}
