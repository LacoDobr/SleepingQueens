package Shuffle;

import DataType.Card;

import java.util.*;

public class VersionTwo implements ShuffleVersions {
    private List<Card> drawingPile;
    private List<Card> trashPile;

    public VersionTwo() {
        this.drawingPile = new ArrayList<>();
        this.trashPile = new ArrayList<>();
    }

    @Override
    public List<Card> shuffle(List<Card> discard, List<Card> drawingPile, List<Card> trashPile) {
        this.drawingPile = drawingPile;
        Collections.shuffle(trashPile);
        this.drawingPile.addAll(trashPile);
        this.trashPile.addAll(discard);
        List<Card> draw = new ArrayList<>();
        for (int i = 0; i < discard.size(); i++) {
            draw.add(drawingPile.get(0));
            drawingPile.remove(0);
        }
        return draw;
    }

    @Override
    public List<Card> getDrawingPile() {
        return drawingPile;
    }

    @Override
    public List<Card> getTrashPile() {
        return trashPile;
    }
}
