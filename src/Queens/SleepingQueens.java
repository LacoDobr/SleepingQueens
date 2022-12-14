package Queens;

import DataType.Position.Position;
import DataType.Position.SleepingQueenPosition;
import DataType.Queen;

import java.util.*;

public class SleepingQueens implements QueenCollection {
    private Map<Position, Queen> sleepingQueens;
    private Stack<Position> emptyPosition;

    public SleepingQueens() {
        emptyPosition = new Stack<>();
        sleepingQueens = new LinkedHashMap<>();
        List<Integer> points = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            points.add(5);
            points.add(10);
        }
        for (int i = 0; i < 3; i++) {
            points.add(15);
        }
        points.add(20);
        Collections.shuffle(points);
        for (int i = 0; i < 12; i++) {
            sleepingQueens.put(new SleepingQueenPosition(i), new Queen(points.get(i)));
        }
    }

    @Override
    public void addQueen(Queen queen) {
        if (!emptyPosition.isEmpty()) {
            sleepingQueens.put(emptyPosition.pop(), queen);
        }
    }

    @Override
    public Optional<Queen> removeQueen(Position position) {
        Optional<Queen> removedQueen = Optional.empty();
        for (Position pos : sleepingQueens.keySet()) {
            if (pos.getCardIndex() == position.getCardIndex()) {
                removedQueen = Optional.ofNullable(sleepingQueens.remove(pos));
                break;
            }
        }
        if (removedQueen.isPresent()) {
            emptyPosition.push(position);
        }
        return removedQueen;
    }

    @Override
    public Map<Position, Queen> getQueens() {
        return sleepingQueens;
    }
}
