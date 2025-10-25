package racingcar.domain.game;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import racingcar.domain.car.Car;
import racingcar.domain.strategy.MoveStrategy;

public class RacingGame {
    private final List<Car> cars;
    private final int attemptCount;
    private final WinnerFinder winnerFinder;
    private final MoveStrategy moveStrategy;

    public RacingGame(List<Car> cars, int attemptCount, MoveStrategy moveStrategy) {
        this.cars = cars;
        this.attemptCount = attemptCount;
        this.winnerFinder = new WinnerFinder();
        this.moveStrategy = moveStrategy;
    }

    public void start( RoundResultCallback callback) {
        for (int round = 0; round < attemptCount; round++) {
            playRound();
            callback.onRoundComplete(cars);  // 라운드 끝날 때마다 호출
        }
    }

    private void playRound() {
        for (Car car : cars) {
            boolean shouldMove = moveStrategy.shouldMove();
            car.move(shouldMove);
        }
    }

    public List<Car> getWinners() {
        return winnerFinder.findWinners(cars);
    }

    public interface RoundResultCallback {
        void onRoundComplete(List<Car> cars);
    }

}
