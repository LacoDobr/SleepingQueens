package DataType.Position;

import java.util.Optional;

public class Position {
    private Optional<SleepingQueenPosition> sqp;
    private Optional<AwokenQueenPosition> aqp;
    private Optional<HandPosition> hp;

    public Position(Optional<SleepingQueenPosition> sqp, Optional<AwokenQueenPosition> aqp, Optional<HandPosition> hp){
        this.sqp = sqp;
        this.aqp = aqp;
        this.hp = hp;
    }

    public Optional<SleepingQueenPosition> getSqp() {
        return sqp;
    }

    public Optional<AwokenQueenPosition> getAqp() {
        return aqp;
    }

    public Optional<HandPosition> getHp() {
        return hp;
    }
}
