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
    public DrawingAndTrashPile drawingAndTrashPile;

    public Game(int onTurn, int numberOfPlayers) {
        //prerobit do osobitnej classy
        Set<SleepingQueenPosition> sleepingQueens = new HashSet<>();
        for (int i = 1; i <= 12; i++) {
            sleepingQueens.add(new SleepingQueenPosition(i));
        }

        drawingAndTrashPile = new DrawingAndTrashPile();
        Map<HandPosition, Optional<Card>> cards = new HashMap<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            List<Card> draw = new ArrayList<>(drawingAndTrashPile.drawStartingDeck());
            for (int j = 0; j < 5; j++) {
                cards.put(new HandPosition(j, i), Optional.of(draw.get(j)));
            }
        }

        Map<AwokenQueenPosition, Queen> awokenQueens = new HashMap<>();
        List<Card> cardsDiscardedLastTurn = new ArrayList<>();

        gameState = new GameState(numberOfPlayers, onTurn, sleepingQueens, cards, awokenQueens, cardsDiscardedLastTurn);
    }

    public void play(int playerIdx, List<Position> cards) {

    }
}
