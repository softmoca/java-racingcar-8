package racingcar.domain.game;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import racingcar.domain.car.Car;

public class RacingGame {
    private final List<Car> cars;
    private final int attemptCount;
    private final WinnerFinder winnerFinder;

    public RacingGame(List<Car> cars, int attemptCount) {
        this.cars = cars;
        this.attemptCount = attemptCount;
        this.winnerFinder = new WinnerFinder();
    }

    public void start( RoundResultCallback callback) {
        for (int round = 0; round < attemptCount; round++) {
            playRound();
            callback.onRoundComplete(cars);  // 라운드 끝날 때마다 호출
        }
    }

    private void playRound() {
        for (Car car : cars) {
            int randomValue= Randoms.pickNumberInRange(0,9);
            boolean shouldMove = randomValue >= 4;
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
