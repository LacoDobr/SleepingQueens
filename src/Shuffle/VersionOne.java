package Shuffle;

import DataType.Card;

import java.util.*;

public class VersionOne implements ShuffleVersions {
    private List<Card> drawingPile;
    private List<Card> trashPile;

    public VersionOne() {
        this.drawingPile = new ArrayList<>();
        this.trashPile = new ArrayList<>();
    }

    @Override
    public List<Card> shuffle(List<Card> discard, List<Card> drawingPile, List<Card> trashPile) {
        trashPile.addAll(discard);
        List<Card> draw = new ArrayList<>(drawingPile);
        int toDraw = discard.size() - drawingPile.size();
        this.drawingPile = trashPile;
        this.trashPile.clear();
        Collections.shuffle(this.drawingPile);
        for (int i = 0; i < toDraw; i++) {
            draw.add(this.drawingPile.get(0));
            this.drawingPile.remove(0);
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
