import DataType.Card;
import DataType.Position.HandPosition;
import Enumeration.CardType;

import java.util.*;

public class Hand {
    private int playerIdx;
    private DrawingAndTrashPile drawingAndTrashPile;
    private List<Card> cards;
    private List<Card> pickedCards;

    public Hand(int playerIdx, DrawingAndTrashPile drawingAndTrashPile) {
        this.playerIdx = playerIdx;
        this.drawingAndTrashPile = drawingAndTrashPile;
        cards = drawingAndTrashPile.drawStartingDeck();
        pickedCards = new ArrayList<>();
    }

    public Optional<List<Card>> pickCards(List<HandPosition> positions) {
        if (positions.isEmpty()) {
            return Optional.empty();
        } else {
            for (HandPosition x : positions) {
                pickedCards.add(cards.get(x.getCardIndex() - 1));
            }
            return Optional.ofNullable(pickedCards);
        }
    }

    public Map<HandPosition, Card> removePickedCardsAndRedraw() {
        cards.removeAll(pickedCards);
        Map<HandPosition, Card> x = new LinkedHashMap<>();
        List<Card> redraw = new ArrayList<>(drawingAndTrashPile.discardAndDraw(pickedCards));
        for (int i = 0; i < redraw.size(); i++) {
            x.put(new HandPosition(i + cards.size(), playerIdx), redraw.get(i));
        }
        cards.addAll(redraw);
        returnPickedCards();
        return x;
    }

    public void returnPickedCards() {
        pickedCards.clear();
    }

    public HandPosition HasCardOfType(CardType type) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getType() == type) {
                return new HandPosition(i, playerIdx);
            }
        }
        return null;
    }

    public List<Card> getCards() {
        return cards;
    }
}
