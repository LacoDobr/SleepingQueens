package Queens;

import DataType.Position.AwokenQueenPosition;
import DataType.Position.Position;
import DataType.Queen;

import java.util.*;

public class AwokenQueens implements QueenCollection {
    private Map<Position, Queen> awokenQueens;
    private final int playerIdx;
    private Stack<Position> emptySpaces;

    public AwokenQueens(int playerIdx) {
        awokenQueens = new HashMap<>();
        this.playerIdx = playerIdx;
        emptySpaces = new Stack<>();
    }

    @Override
    public void addQueen(Queen queen) {
        if (emptySpaces.isEmpty()) {
            awokenQueens.put(new AwokenQueenPosition(awokenQueens.size(), playerIdx), queen);
        } else {
            awokenQueens.put(emptySpaces.pop(), queen);
        }
    }

    @Override
    public Optional<Queen> removeQueen(Position position) {
        Optional<Queen> removedQueen = Optional.ofNullable(awokenQueens.remove(position));
        if (removedQueen.isPresent()) {
            emptySpaces.push(position);
        }
        return removedQueen;
    }

    @Override
    public Map<Position, Queen> getQueens() {
        return awokenQueens;
    }
}
