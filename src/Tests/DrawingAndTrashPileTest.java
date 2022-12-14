package Tests;

import DataType.Card;
import Main.DrawingAndTrashPile;
import Enumeration.*;
import org.junit.Test;

import java.util.*;
import static org.junit.Assert.*;

public class DrawingAndTrashPileTest {
    private DrawingAndTrashPile drawingAndTrashPile = new DrawingAndTrashPile();
    public List<Card> discard = new ArrayList<>();

    private void setUp() {
        drawingAndTrashPile = new DrawingAndTrashPile();
        for (int i = 2; i < 5; i++) {
            discard.add(new Card(CardType.Number,i));
        }
    }

    @Test
    public void discardAndDrawTest() {
        setUp();
        List<Card> newCards = drawingAndTrashPile.discardAndDraw(discard);
        assertEquals(3, newCards.size());
    }

    @Test
    public void getCardsDiscardedThisTurnTest() {
        setUp();
        drawingAndTrashPile.discardAndDraw(discard);
        assertEquals(3, drawingAndTrashPile.getCardsDiscardedThisTurn().size());
        for (Card card: discard){
            assertTrue(drawingAndTrashPile.getCardsDiscardedThisTurn().contains(card));
        }

    }

    @Test
    public void newTurnTest() {
        drawingAndTrashPile.newTurn();
        assertTrue(drawingAndTrashPile.getCardsDiscardedThisTurn().isEmpty());
    }

    @Test
    public void drawStartingDeckTest() {
        List<Card> list = new ArrayList<>(drawingAndTrashPile.drawStartingDeck());
        assertEquals(5, list.size());
    }
}
