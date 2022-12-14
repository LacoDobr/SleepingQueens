package Main;

import DataType.Card;
import DataType.GameState;
import DataType.Position.AwokenQueenPosition;
import DataType.Position.HandPosition;
import DataType.Position.Position;
import DataType.Position.SleepingQueenPosition;
import DataType.Queen;
import Queens.SleepingQueens;

import java.util.*;

public class Game {
    private GameState gameState;
    private List<Player> players;
    private DrawingAndTrashPile drawingAndTrashPile;
    private SleepingQueens sleepingQueens;
    private GameFinished gameFinished;

    public Game(int onTurn, int numberOfPlayers) {
        players = new ArrayList<>();
        sleepingQueens = new SleepingQueens();
        Set<Position> temp = new LinkedHashSet<>(sleepingQueens.getQueens().keySet());
        Set<SleepingQueenPosition> sleepingQueensSet = new LinkedHashSet<>();
        for (Position x : temp) {
            sleepingQueensSet.add((SleepingQueenPosition)x);
        }

        drawingAndTrashPile = new DrawingAndTrashPile();

        Map<HandPosition, Optional<Card>> cards = new LinkedHashMap<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            Hand hand = new Hand(i, drawingAndTrashPile);
            players.add(new Player(i, hand, sleepingQueens));
            List<Card> draw = new ArrayList<>(drawingAndTrashPile.drawStartingDeck());
            for (int j = 0; j < 5; j++) {
                cards.put(new HandPosition(j, i), Optional.of(draw.get(j)));
            }
        }

        Map<AwokenQueenPosition, Queen> awokenQueens = new LinkedHashMap<>();
        List<Card> cardsDiscardedLastTurn = new ArrayList<>();

        gameState = new GameState(numberOfPlayers, onTurn, sleepingQueensSet, cards, awokenQueens, cardsDiscardedLastTurn);

        gameFinished = new GameFinished(this);
    }

    public GameState getGameState() {
        return gameState;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public DrawingAndTrashPile getDrawingAndTrashPile() {
        return drawingAndTrashPile;
    }

    public SleepingQueens getSleepingQueens() {
        return sleepingQueens;
    }

    public void update() {
        Map<HandPosition, Optional<Card>> cards = new LinkedHashMap<>();
        for(Player player : players){
            int i = 0;
            for (Card card : player.getHand().getCards()){
                cards.put(new HandPosition(i, player.getPlayerIdx()), Optional.ofNullable(card));
                i++;
            }
        }
        gameState.cards = cards;

        Set<SleepingQueenPosition> sleepingQueenPositions = new LinkedHashSet<>();
        for (Position position : sleepingQueens.getQueens().keySet()) {
            sleepingQueenPositions.add((SleepingQueenPosition) position);
        }
        gameState.sleepingQueens = sleepingQueenPositions;

        Map<AwokenQueenPosition, Queen> playersQueens = new LinkedHashMap<>();
        for(Player player : players){
            int i = 0;
            for (Queen queen: player.getAwokenQueens().getQueens().values()){
                playersQueens.put(new AwokenQueenPosition(i,player.getPlayerIdx()),queen);
                i++;
            }
        }
        gameState.awokenQueens = playersQueens;
    }


    public Optional<GameState> play(int playerIdx, List<Position> cards) {
        if (playerIdx == gameState.onTurn) {
            if (players.get(playerIdx).play(cards)) {
                update();
                gameState.onTurn = (gameState.onTurn + 1) % gameState.numberOfPlayers;
                gameState.cardsDiscardedLastTurn = drawingAndTrashPile.getCardsDiscardedThisTurn();
                drawingAndTrashPile.newTurn();
                Optional<Integer> winner = gameFinished.isFinished();

                if (winner.isPresent()) {
                    System.out.println("Winner: " + winner.get());
                }
                return Optional.of(gameState);
            }
        }
        return Optional.empty();
    }
}
