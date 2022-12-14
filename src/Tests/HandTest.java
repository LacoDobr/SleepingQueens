package Tests;

import DataType.Card;
import DataType.Position.HandPosition;
import Main.DrawingAndTrashPile;
import Main.Hand;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

public class HandTest {
    private Hand hand;
    private List<HandPosition> handPositions;


    private void setUp() {
        DrawingAndTrashPile drawingAndTrashPile = new DrawingAndTrashPile();
        hand = new Hand(1, drawingAndTrashPile);
        handPositions = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            handPositions.add(new HandPosition(i,1));
        }
    }

    @Test
    public void getCardsTest() {
        setUp();
        assertEquals(5, hand.getCards().size());
    }

    @Test
    public void pickCardsTest() {
        setUp();
        Optional<List<Card>> cards = hand.pickCards(handPositions);
        cards.ifPresent(cardList -> assertEquals(3, cardList.size()));
    }

    @Test
    public void removePickedCardsAndRedrawTest() {
        setUp();
        hand.pickCards(handPositions);
        Map<HandPosition,Card > map = hand.removePickedCardsAndRedraw();
        assertEquals(3, map.size());
        int i = 5 - handPositions.size();
        for (HandPosition position : map.keySet()) {
            assertEquals(position.getCardIndex(), i);
            i++;
        }
    }
}
