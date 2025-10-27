package racingcar.domain.strategy;


public class FixedMoveStrategy implements MoveStrategy {
    private final boolean moveDecision;

    public FixedMoveStrategy(boolean moveDecision) {
        this.moveDecision = moveDecision;
    }

    @Override
    public boolean shouldMove() {
        return moveDecision;
    }

    public static FixedMoveStrategy alwaysMove() {
        return new FixedMoveStrategy(true);
    }

    public static FixedMoveStrategy neverMove() {
        return new FixedMoveStrategy(false);
    }
}
