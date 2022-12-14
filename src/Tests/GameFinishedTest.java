package Tests;

import DataType.Position.SleepingQueenPosition;
import DataType.Queen;
import Main.Game;
import Main.GameFinished;

import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

public class GameFinishedTest {
    public Game game;
    private GameFinished gameFinished;

    private void setUp(){
        game = new Game(1, 5);
        gameFinished = new GameFinished(game);
    }

    @Test
    public void test1() {
        setUp();
        assertTrue(gameFinished.isFinished().isEmpty());
    }

    @Test
    public void test2() {
        setUp();
        for (int i = 0; i < 4; i++){
            Optional<Queen> queen = game.getSleepingQueens().removeQueen(new SleepingQueenPosition(i));
            game.getPlayers().get(1).getAwokenQueens().addQueen(queen.get());
        }
        assertTrue(gameFinished.isFinished().isPresent());
    }

    @Test
    public void test3() {
        setUp();
        game.getSleepingQueens().getQueens().clear();
        assertTrue(gameFinished.isFinished().isPresent());
    }
}
