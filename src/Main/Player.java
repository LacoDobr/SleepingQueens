package Main;

import DataType.Card;
import DataType.PlayerState;
import DataType.Position.AwokenQueenPosition;
import DataType.Position.HandPosition;
import DataType.Position.Position;
import DataType.Position.SleepingQueenPosition;
import DataType.Queen;
import Queens.AwokenQueens;
import Queens.SleepingQueens;
import Enumeration.CardType;

import java.util.*;

public class Player {
    private final int playerIdx;
    private PlayerState playerState;
    private Hand hand;
    private AwokenQueens awokenQueens;
    private SleepingQueens sleepingQueens;
    private EvaluateAttack evaluateAttack;
    private MoveQueen moveQueen;


    public Player(int playerIdx, Hand hand, SleepingQueens sleepingQueens) {
        this.playerIdx = playerIdx;
        this.hand = hand;
        this.sleepingQueens = sleepingQueens;
        awokenQueens = new AwokenQueens(playerIdx);
        uptadePlayerState();
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

        Map<Integer, Queen> queens = new LinkedHashMap<>();
        for (Position position : awokenQueens.getQueens().keySet()) {
            queens.put(position.getCardIndex(), awokenQueens.getQueens().get(position));
        }

        playerState = new PlayerState(cards, queens);
    }

    public void setEvaluateAttack(EvaluateAttack evaluateAttack) {
        this.evaluateAttack = evaluateAttack;
    }

    public void setMoveQueen(MoveQueen moveQueen) {
        this.moveQueen = moveQueen;
    }

    public boolean play(List<Position> cards) {
        if ((cards.isEmpty()) || !(cards.get(0) instanceof HandPosition) || (((HandPosition) cards.get(0)).getPlayerIndex() != playerIdx)) {
            return false;
        }

        List<HandPosition> handPositions = new ArrayList<>();

        if (cards.size() == 1) {
            if (hand.getCards().get(cards.get(0).getCardIndex()).getType() != CardType.Number) {
                return false;
            } else {
                handPositions.add((HandPosition) cards.get(0));
            }
        } else if (cards.size() == 2) {
            Card firstCard = hand.getCards().get(cards.get(0).getCardIndex());

            Position targetQueen = cards.get(1);
            switch (firstCard.getType()) {
                case King:
                    if (targetQueen instanceof SleepingQueenPosition) {
                        moveQueen.setQueenCollection(awokenQueens);
                        if (!moveQueen.play(targetQueen)) {
                            return false;
                        } else {
                            handPositions.add((HandPosition) cards.get(0));
                        }
                    } else {
                        return false;
                    }
                    break;
                case Knight:
                    if (targetQueen instanceof AwokenQueenPosition) {
                        if (((AwokenQueenPosition) targetQueen).getPlayerIndex() == playerIdx) {
                            return false;
                        }
                        evaluateAttack.setDefenseCardType(CardType.Dragon);
                        evaluateAttack.setQueenCollection(awokenQueens);
                        if (!evaluateAttack.play(targetQueen, ((AwokenQueenPosition) targetQueen).getPlayerIndex())) {
                            return false;
                        }
                        handPositions.add((HandPosition) cards.get(0));
                    } else {
                        return false;
                    }
                    break;
                case SleepingPotion:
                    if (targetQueen instanceof AwokenQueenPosition) {
                        if (((AwokenQueenPosition) targetQueen).getPlayerIndex() == playerIdx){
                            return false;
                        }
                        evaluateAttack.setDefenseCardType(CardType.MagicWand);
                        evaluateAttack.setQueenCollection(sleepingQueens);
                        if (!evaluateAttack.play(targetQueen, ((AwokenQueenPosition) targetQueen).getPlayerIndex())) {
                            return false;
                        }
                        handPositions.add((HandPosition) cards.get(0));
                    } else {
                        return false;
                    }
                    break;
                default:
                    if (!(cards.get(1) instanceof HandPosition)) {
                        return false;
                    }

                    Card secondCard = hand.getCards().get(cards.get(1).getCardIndex());
                    if ((firstCard.getType() != CardType.Number) || (secondCard.getType() != CardType.Number)) {
                        return false;
                    } else {
                        EvaluateNumberedCards evaluateNumberedCards = new EvaluateNumberedCards();
                        List<Card> eval = new ArrayList<>();
                        eval.add(firstCard);
                        eval.add(secondCard);
                        if (!evaluateNumberedCards.play(eval)) {
                            return false;
                        }
                        handPositions.add((HandPosition) cards.get(0));
                        handPositions.add((HandPosition) cards.get(1));
                    }
            }
        } else {
            ArrayList<Card> numberCards = new ArrayList<>();
            ArrayList<Integer> tempCardPositions = new ArrayList<>();

            for (Position position : cards) {
                if (!(position instanceof HandPosition)  || (((HandPosition) position).getPlayerIndex() != playerIdx)) {
                    return false;
                }
                Card card = hand.getCards().get(cards.get(0).getCardIndex());

                if(!tempCardPositions.contains(position.getCardIndex())) {
                    tempCardPositions.add(position.getCardIndex());
                    if(card.getType() != CardType.Number){
                        return false;
                    } else {
                        numberCards.add(card);
                        handPositions.add((HandPosition) position);
                    }
                }
            }

            EvaluateNumberedCards evaluateNumberedCards = new EvaluateNumberedCards();
            if (!evaluateNumberedCards.play(numberCards)) {
                return false;
            }
        }

        hand.pickCards(handPositions);
        hand.removePickedCardsAndRedraw();
        uptadePlayerState();
        return true;
    }
}
