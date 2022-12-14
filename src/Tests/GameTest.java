package Tests;

import java.util.*;
import static org.junit.Assert.*;

import DataType.GameState;
import DataType.Position.Position;
import Main.DrawingAndTrashPile;
import Main.Game;
import Main.Player;
import org.junit.*;

public class GameTest {
    private Game game;

    @Test
    public void getDrawingAndTrashPileTest() {
        game = new Game(2, 5);
        DrawingAndTrashPile drawingAndTrashPile = game.getDrawingAndTrashPile();
        assertNotNull(drawingAndTrashPile);
    }

    @Test
    public void getSleepingQueensTest() {
        game = new Game(2, 5);
        assertNotNull(game.getSleepingQueens());
        assertEquals(12, game.getSleepingQueens().getQueens().size());
    }

    @Test
    public void getPlayersTest() {
        game = new Game(2, 5);
        List<Player> players = game.getPlayers();
        assertEquals(5, players.size());
    }

    @Test
    public void getGameStateTest() {
        game = new Game(2, 5);
        GameState gameState = game.getGameState();
        assertNotNull(gameState);
        assertEquals(2, gameState.onTurn);
    }

    @Test
    public void playTest() {
        game = new Game(2, 5);
        List<Position> list = new ArrayList<>();
        Optional<GameState> gameState = game.play(1,list);
        assertTrue(gameState.isEmpty());
    }
}
