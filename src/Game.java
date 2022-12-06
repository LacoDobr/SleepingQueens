import DataType.Card;
import DataType.GameState;
import DataType.PlayerState;
import DataType.Position.AwokenQueenPosition;
import DataType.Position.HandPosition;
import DataType.Position.Position;
import DataType.Position.SleepingQueenPosition;
import DataType.Queen;

import java.util.*;

public class Game {
    public GameState gameState;
    public List<PlayerState> playerStates;

    public Game(int onTurn, int numberOfPlayers) {
        Set<SleepingQueenPosition> sleepingQueens = new HashSet<>();
        for (int i = 1; i <= 12; i++) {
            sleepingQueens.add(new SleepingQueenPosition(i));
        }

        Map<HandPosition, Optional<Card>> cards = new HashMap<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            for (int j = 0; j < 5; j++) {
                //HandPosition temp = new HandPosition();
                //cards.put(temp, null);
            }
        }
        //naplnit-------------------------------------------------


        Map<AwokenQueenPosition, Queen> awokenQueens = new HashMap<>();
        List<Card> cardsDiscardedLastTurn = new ArrayList<>();

        gameState = new GameState(numberOfPlayers, onTurn, sleepingQueens, cards, awokenQueens, cardsDiscardedLastTurn);

        playerStates = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            //naplnit-------------------------------------------------
        }
    }

    public void play(int playerIdx, List<Position> cards) {

    }
}
