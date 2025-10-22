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

    public List<List<Car>> startAndGetRoundResults() {
        List<List<Car>> allRoundResults = new java.util.ArrayList<>();

        for (int round = 0; round < attemptCount; round++) {
            playRound();
            allRoundResults.add(cars);
        }

        return allRoundResults;
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


}
