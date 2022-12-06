package Shuffle;

import DataType.Card;

import java.util.*;

public interface ShuffleVersions {
    List<Card> shuffle(List<Card> discard, List<Card> drawingPile, List<Card> trashPile);
    List<Card> getDrawingPile();
    List<Card> getTrashPile();
}
