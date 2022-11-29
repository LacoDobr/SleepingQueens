package DataType;

import Enumeration.CardType;

public class Card {
    private final CardType type;
    private final int value;

    public Card(CardType type, int value) {
        this.type = type;
        this.value = value;
    }

    public CardType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }
}
