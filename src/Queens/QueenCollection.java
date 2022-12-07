package Queens;

import DataType.Position.Position;
import DataType.Queen;

import java.util.*;

public interface QueenCollection {
    void addQueen(Queen queen);
    Optional<Queen> removeQueen(Position position);
    Map<Position, Queen> getQueens();
}
