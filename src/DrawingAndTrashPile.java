import DataType.Card;
import Shuffle.ShuffleVersions;
import Shuffle.VersionOne;
import Enumeration.CardType;

import java.util.*;

public class DrawingAndTrashPile {
    private List<Card> drawingPile;
    private List<Card> trashPile;
    private List<Card> cardsDiscardedThisTurn;
    private ShuffleVersions shuffleVersion;

    public DrawingAndTrashPile() {
        drawingPile = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            drawingPile.add(new Card(CardType.King, 0));
        }

        for (int i = 0; i < 4; i++) {
            drawingPile.add(new Card(CardType.Knight, 0));
            drawingPile.add(new Card(CardType.SleepingPotion, 0));
            for (int j = 1; j <= 10; i++) {
                drawingPile.add(new Card(CardType.Number, j));
            }
        }

        for (int i = 0; i < 3; i++) {
            drawingPile.add(new Card(CardType.MagicWand, 0));
            drawingPile.add(new Card(CardType.Dragon, 0));
        }
        Collections.shuffle(drawingPile);

        trashPile = new ArrayList<>();
        cardsDiscardedThisTurn = new ArrayList<>();
        shuffleVersion = new VersionOne();
    }

    public List<Card> discardAndDraw(List<Card> discard) {
        List<Card> draw = new ArrayList<>();
        cardsDiscardedThisTurn = discard;
        if (drawingPile.size() > discard.size()) {
            trashPile.addAll(discard);
            for (int i = 0; i < discard.size(); i++) {
                draw.add(drawingPile.get(0));
                drawingPile.remove(0);
            }
        } else {
            if (shuffleVersion == null) {
                shuffleVersion = new VersionOne();
            }
            draw = shuffleVersion.shuffle(discard, drawingPile,trashPile);
            drawingPile = shuffleVersion.getDrawingPile();
            trashPile = shuffleVersion.getTrashPile();
        }
        return draw;
    }

    public List<Card> drawStartingDeck() {
        List<Card> draw = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            draw.add(drawingPile.get(0));
            drawingPile.remove(0);
        }
        return draw;
    }

    public void newTurn() {
        cardsDiscardedThisTurn.clear();
    }

    public List<Card> getCardsDiscardedThisTurn() {
        return null;
    }

    public void setShuffleVersion(ShuffleVersions shuffleVersion) {
        this.shuffleVersion = shuffleVersion;
    }
}
