package DataType;

import DataType.Position.AwokenQueenPosition;
import DataType.Position.HandPosition;
import DataType.Position.SleepingQueenPosition;

import java.util.*;

public class GameState {
    public int numberOfPlayers;
    public int onTurn;
    public Set<SleepingQueenPosition> sleepingQueens;
    public Map<HandPosition, Optional<Card>> cards;
    public Map<AwokenQueenPosition, Queen> awokenQueens;
    public List<Card> cardsDiscardedLastTurn;

    public GameState(int numberOfPlayers, int onTurn, Set<SleepingQueenPosition> sleepingQueens, Map<HandPosition, Optional<Card>> cards,
                     Map<AwokenQueenPosition, Queen> awokenQueens, List<Card> cardsDiscardedLastTurn) {
        this.numberOfPlayers = numberOfPlayers;
        this.onTurn = onTurn;
        this.sleepingQueens = sleepingQueens;
        this.cards = cards;
        this.awokenQueens = awokenQueens;
        this.cardsDiscardedLastTurn = cardsDiscardedLastTurn;
    }

    public String toString() {
        return "numberOfPlayers:"+numberOfPlayers+" onTurn:"+onTurn+" ";
    }
}
