package racingcar;

import java.util.List;

public class RacingGame {

    private final Cars cars;

    public RacingGame(Cars cars) {
        this.cars = cars;
    }

    public RacingResult run(MovingStrategy strategy, int rounds) {
        for (int i = 0; i < rounds; i++) {
            cars.moveAll(strategy);
        }

        List<String> winners = cars.getWinners();

        return RacingResult.of(winners);
    }
}
