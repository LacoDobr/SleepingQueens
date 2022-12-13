import java.util.*;

public class GameFinished implements GameFinishedStrategy {

    private Game game;
    //private final int requiredScore;
    //private final int requiredQueens;
    public GameFinished(Game game) {
        this.game = game;
    }

    @Override
    public Optional<Integer> isFinished() {

        return Optional.empty();
    }
}
