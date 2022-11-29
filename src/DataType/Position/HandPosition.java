package DataType.Position;

public class HandPosition {
    private int cardIndex;
    private int playerIndex;

    public HandPosition(int cardIndex, int playerIndex) {
        this.cardIndex = cardIndex;
        this.playerIndex = playerIndex;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public int getCardIndex() {
        return cardIndex;
    }
}
