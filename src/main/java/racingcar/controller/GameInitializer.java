package racingcar.controller;

import java.util.List;
import racingcar.domain.car.Cars;
import racingcar.domain.game.RacingGame;
import racingcar.domain.strategy.MovingStrategy;

public class GameInitializer {

    private final MovingStrategy movingStrategy;

    public GameInitializer(MovingStrategy movingStrategy) {
        this.movingStrategy = movingStrategy;
    }

    public RacingGame createGame(List<String> carNames) {
        Cars cars = Cars.from(carNames);
        return new RacingGame(cars);
    }

    public MovingStrategy getMovingStrategy() {
        return movingStrategy;
    }
}
