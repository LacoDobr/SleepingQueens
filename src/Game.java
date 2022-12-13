import DataType.Card;
import DataType.GameState;
import DataType.Position.AwokenQueenPosition;
import DataType.Position.HandPosition;
import DataType.Position.Position;
import DataType.Position.SleepingQueenPosition;
import DataType.Queen;
import Queens.QueenCollection;
import Queens.SleepingQueens;

import java.util.*;

public class Game {
    private GameState gameState;
    private DrawingAndTrashPile drawingAndTrashPile;
    private List<Player> players;
    private SleepingQueens sleepingQueens;
    private GameFinished gameFinished;

    public Game(int onTurn, int numberOfPlayers) {
        QueenCollection sleepingQueens = new SleepingQueens();
        Set<Position> temp = new HashSet<>(sleepingQueens.getQueens().keySet());
        Set<SleepingQueenPosition> sleepingQueensSet = new HashSet<>();
        for (Position x : temp) {
            sleepingQueensSet.add((SleepingQueenPosition)x);
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

        gameState = new GameState(numberOfPlayers, onTurn, sleepingQueensSet, cards, awokenQueens, cardsDiscardedLastTurn);
    }

    public Optional<GameState> play(int playerIdx, List<Position> cards) {
        if (playerIdx == gameState.onTurn) {
            return null;
        } else {
            return Optional.empty();
        }
    }
}
