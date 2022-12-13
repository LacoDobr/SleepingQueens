import DataType.Card;
import DataType.PlayerState;
import DataType.Position.HandPosition;
import DataType.Position.Position;
import DataType.Queen;
import Queens.AwokenQueens;
import Queens.SleepingQueens;

import java.util.*;

public class Player {
    private final int playerIdx;
    private PlayerState playerState;
    private Hand hand;
    private AwokenQueens awokenQueens;
    private SleepingQueens sleepingQueens;
    private EvaluateAttack evaluateAttack;


    public Player(int playerIdx, Hand hand) {
        this.playerIdx = playerIdx;
        this.hand = hand;
        awokenQueens = new AwokenQueens(playerIdx);
        uptadePlayerState();
    }

    public void uptadePlayerState() {
        Map<Integer, Optional<Card>> cards = new LinkedHashMap<>();
        List<Card> onHand = hand.getCards();
        for (int i = 0; i < onHand.size(); i++) {
            if (i < onHand.size()) {
                cards.put(i, Optional.of(onHand.get(i)));
            } else {
                cards.put(i, Optional.empty());
            }
        }

        Map<Integer, Queen> queens = new HashMap<>();
        for (Position position : awokenQueens.getQueens().keySet()) {
            queens.put(position.getCardIndex(), awokenQueens.getQueens().get(position));
        }

        playerState = new PlayerState(cards, queens);
    }

    public int getPlayerIdx() {
        return playerIdx;
    }

    public AwokenQueens getAwokenQueens() {
        return awokenQueens;
    }

    public Hand getHand() {
        return hand;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public boolean play(List<Position> cards) {
        if ((cards.isEmpty()) || !(cards.get(0) instanceof HandPosition)) {
            return false;
        }

        List<HandPosition> handPositions = new ArrayList<>();
        if (cards.size() == 1) {
            handPositions.add((HandPosition) cards.get(0));
        } else if (cards.size() == 2) {
            Optional<Card> firstCard = playerState.getCards().get(cards.get(0).getCardIndex());
            if (firstCard.isEmpty()) {
                return false;
            }

            switch (firstCard.get().getType()) {
                case King:

                    break;
            }
        }

        return true;
    }
}
