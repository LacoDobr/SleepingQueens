import DataType.PlayerState;
import DataType.Position.HandPosition;
import DataType.Position.Position;

import java.util.*;

public class Player {
    public int playerIdx;
    private PlayerState playerState;

    public Player(int playerIdx, PlayerState playerState) {
        this.playerIdx = playerIdx;
        this.playerState = playerState;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void play(List<Position> cards) {
        for (int i = 0; i < cards.size(); i++) {
            Position card = cards.get(i);
            if (card.getSqp().isPresent()) {

            } else if (card.getAqp().isPresent()) {

            } else if (card.getHp().isPresent()) {
                int cardIndex = card.getHp().orElse(null).getCardIndex();

            }
        }
    }
}
