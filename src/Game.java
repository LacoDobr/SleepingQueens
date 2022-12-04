import DataType.Card;
import DataType.GameState;
import DataType.Position.AwokenQueenPosition;
import DataType.Position.HandPosition;
import DataType.Position.Position;
import DataType.Position.SleepingQueenPosition;
import DataType.Queen;

import java.util.*;

public class Game {
    public GameState gameState;

    public Game(int onTurn, int numberOfPlayers) {
        Set<SleepingQueenPosition> sleepingQueens = new HashSet<>();
        for (int i = 1; i <= 12; i++) {
            SleepingQueenPosition temp = new SleepingQueenPosition(i);
            sleepingQueens.add(temp);
        }
        Map<HandPosition, Optional<Card>> cards = new HashMap<>();
        //naplnit
        Map<AwokenQueenPosition, Queen> awokenQueens = new HashMap<>();
        List<Card> cardsDiscardedLastTurn = new ArrayList<>();

        gameState = new GameState(numberOfPlayers, onTurn, sleepingQueens, cards, awokenQueens, cardsDiscardedLastTurn);
    }

    public void play(int playerIdx, List<Position> cards) {

    }
}
