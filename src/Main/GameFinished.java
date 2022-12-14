package Main;

import DataType.Queen;

import java.util.*;

public class GameFinished implements GameFinishedStrategy {
    private Game game;
    private final int requiredQueens;
    private final int requiredScore;

    public GameFinished(Game game) {
        this.game = game;
        if ((game.getPlayers().size() >= 2) && (game.getPlayers().size() <= 3)) {
            requiredQueens = 5;
            requiredScore = 50;
        } else if ((game.getPlayers().size() >= 4) && (game.getPlayers().size() <= 5)) {
            requiredQueens = 4;
            requiredScore = 40;
        } else {
            requiredQueens = 0;
            requiredScore = 0;
        }
    }

    @Override
    public Optional<Integer> isFinished() {
        int bestScore = -1;
        int bestIdx = -1;

        for (Player player : game.getPlayers()) {
            int idx = player.getPlayerIdx();
            int queenCount = 0;
            int score = 0;
            for (Queen queen : player.getAwokenQueens().getQueens().values()) {
                queenCount++;
                score += queen.getPoint();
            }

            if ((queenCount >= requiredQueens) || score >= requiredScore){
                return Optional.of(idx);
            }

            if (score > bestScore) {
                bestIdx = idx;
                bestScore = score;
            }
        }

        if (game.getSleepingQueens().getQueens().size() == 0) {
            return Optional.of(bestIdx);
        } else {
            return Optional.empty();
        }
    }
}
