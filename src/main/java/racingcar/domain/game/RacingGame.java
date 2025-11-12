package racingcar.domain.game;

import java.util.ArrayList;
import java.util.List;
import racingcar.domain.car.Cars;
import racingcar.domain.strategy.MovingStrategy;

public class RacingGame {

    private final Cars cars;

    public RacingGame(Cars cars) {
        this.cars = cars;
    }

    public RacingResult run(MovingStrategy strategy, int rounds) {
        List<List<Integer>> roundHistory = new ArrayList<>();
        for (int i = 0; i < rounds; i++) {
            cars.moveAll(strategy);

            List<Integer> currentPositions = cars.getPositions();
            roundHistory.add(currentPositions);
        }

        List<String> winners = cars.getWinners();

        return RacingResult.of(winners, roundHistory);
    }
}
