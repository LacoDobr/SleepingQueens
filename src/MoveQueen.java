import DataType.Position.AwokenQueenPosition;
import DataType.Position.Position;
import DataType.Position.SleepingQueenPosition;
import DataType.Queen;
import Queens.QueenCollection;
import Queens.SleepingQueens;

import java.util.*;

public class MoveQueen {
    private QueenCollection queenCollection;
    private SleepingQueens sleepingQueens;
    private List<Player> players;

    public MoveQueen(SleepingQueens sleepingQueens, List<Player> players) {
        this.sleepingQueens = sleepingQueens;
        this.players = players;
    }

    public void setQueenCollection(QueenCollection queenCollection) {
        this.queenCollection = queenCollection;
    }

    public boolean play(Position targetQueen) {
        if (targetQueen instanceof AwokenQueenPosition) {
            int targetIdx = ((AwokenQueenPosition) targetQueen).getPlayerIndex();
            Optional<Queen> queen = players.get(targetIdx).getAwokenQueens().removeQueen(targetQueen);
            if (queen.isPresent()) {
                queenCollection.addQueen(queen.get());
                return true;
            } else {
                return false;
            }
        } else if (targetQueen instanceof SleepingQueenPosition) {
            Optional<Queen> queen = sleepingQueens.removeQueen(targetQueen);
            if (queen.isPresent()) {
                queenCollection.addQueen(queen.get());
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
