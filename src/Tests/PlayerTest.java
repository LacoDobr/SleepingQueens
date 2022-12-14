package Tests;

import DataType.Card;
import DataType.Position.HandPosition;
import DataType.Position.Position;
import Main.DrawingAndTrashPile;
import Main.Hand;
import Main.MoveQueen;
import Main.Player;
import Queens.SleepingQueens;
import Enumeration.*;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

public class PlayerTest {
    private Player player;
    private Hand hand;

    private void setUp(){
        SleepingQueens sleepingQueens = new SleepingQueens();

        DrawingAndTrashPile drawingAndTrashPile = new DrawingAndTrashPile();
        hand = new Hand(0, drawingAndTrashPile);

        player = new Player(0, hand, sleepingQueens);
        List<Player> players = new ArrayList<>();
        players.add(player);
        player.setMoveQueen(new MoveQueen(sleepingQueens, players));
    }

    public HandPosition findCard(CardType type, List<Card> cards, int playerIdx) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getType() == type) {
                return new HandPosition(i, playerIdx);
            }
        }
        return null;
    }

    @Test
    public void getPlayerState() {
        setUp();
        assertNotNull(player.getPlayerState()) ;
    }

    @Test
    public void playTest() {
        setUp();
        HandPosition handPosition = findCard(CardType.Number, hand.getCards(), 0);
        List<Position> list = new ArrayList<>();
        list.add(handPosition);
        assertTrue(player.play(list));
    }
}
