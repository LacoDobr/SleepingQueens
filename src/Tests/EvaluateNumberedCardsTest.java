package Tests;

import java.util.*;

import DataType.Card;
import Enumeration.*;
import Main.EvaluateNumberedCards;
import org.junit.Test;
import static org.junit.Assert.*;

public class EvaluateNumberedCardsTest {
    private EvaluateNumberedCards evaluateNumberedCards;
    private List<Card> list;

    private void setUp(){
        evaluateNumberedCards = new EvaluateNumberedCards();
        list = new ArrayList<>();
    }

    @Test
    public void test1() {
        setUp();
        list.add(new Card(CardType.Number,2));
        assertTrue(evaluateNumberedCards.play(list));
    }

    @Test
    public void test2() {
        setUp();
        list.add(new Card(CardType.Number,5));
        list.add(new Card(CardType.Number,5));
        assertTrue(evaluateNumberedCards.play(list));
    }

    @Test
    public void test3() {
        setUp();
        list.add(new Card(CardType.Number,1));
        list.add(new Card(CardType.Number,2));
        list.add(new Card(CardType.Number,3));
        assertTrue(evaluateNumberedCards.play(list));
    }

    @Test
    public void test4() {
        setUp();
        list.add(new Card(CardType.Number,1));
        list.add(new Card(CardType.Number,1));
        list.add(new Card(CardType.Number,1));
        assertFalse(evaluateNumberedCards.play(list));
    }
}
