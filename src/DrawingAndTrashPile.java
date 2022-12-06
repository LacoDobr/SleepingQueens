import DataType.Card;
import Shuffle.ShuffleVersions;
import Shuffle.VersionOne;

import java.util.*;

public class DrawingAndTrashPile {
    private List<Card> drawingPile;
    private List<Card> trashPile;
    private List<Card> cardsDiscardedThisTurn;
    private ShuffleVersions shuffleVersion;

    public DrawingAndTrashPile() {
        drawingPile = new ArrayList<>();
        //naplnit-----
        trashPile = new ArrayList<>();
        cardsDiscardedThisTurn = new ArrayList<>();
        shuffleVersion = new VersionOne();
    }

    public List<Card> discardAndDraw(List<Card> discard) {
        List<Card> draw = new ArrayList<>();
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
