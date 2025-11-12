package racingcar.domain.strategy;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomMovingStrategy implements MovingStrategy {

    private static final int MIN_RANGE = 0;
    private static final int MAX_RANGE = 9;
    private static final int MOVE_THRESHOLD = 4;

    @Override
    public boolean shouldMove() {
        int randomValue = Randoms.pickNumberInRange(MIN_RANGE, MAX_RANGE);

        return randomValue >= MOVE_THRESHOLD;
    }
}
