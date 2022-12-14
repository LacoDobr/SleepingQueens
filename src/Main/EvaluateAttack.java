package Main;

import DataType.Position.HandPosition;
import DataType.Position.Position;
import Enumeration.CardType;
import Queens.QueenCollection;

import java.util.*;

public class EvaluateAttack {
    private CardType defenseCardType;
    private List<Player> players;
    private QueenCollection queenCollection;
    private MoveQueen moveQueen;

    public EvaluateAttack(List<Player> players, MoveQueen moveQueen) {
        this.players = players;
        this.moveQueen = moveQueen;
    }

    public void setQueenCollection(QueenCollection queenCollection) {
        this.queenCollection = queenCollection;
    }

    public void setDefenseCardType(CardType defenseCardType) {
        this.defenseCardType = defenseCardType;
    }

    public boolean play(Position targetQueen, int targetPlayerIdx) {
        if (players.size() <= targetPlayerIdx) {
            return false;
        }

        boolean containsQueenPosition = false;
        for (Position position : players.get(targetPlayerIdx).getAwokenQueens().getQueens().keySet()) {
            if (position.getCardIndex() == targetQueen.getCardIndex()) {
                containsQueenPosition = true;
                break;
            }
        }
        if (!containsQueenPosition) {
            return false;
        }

        HandPosition defenceCard = players.get(targetPlayerIdx).getHand().HasCardOfType(defenseCardType);
        if (defenceCard == null) {
            moveQueen.setQueenCollection(queenCollection);
            moveQueen.play(targetQueen);
        } else {
            List<HandPosition> cards = new ArrayList<>();
            cards.add(defenceCard);
            players.get(targetPlayerIdx).getHand().pickCards(cards);
            players.get(targetPlayerIdx).getHand().removePickedCardsAndRedraw();
        }
        return true;
    }
}
