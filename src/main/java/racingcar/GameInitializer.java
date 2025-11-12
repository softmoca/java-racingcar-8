package racingcar;

import java.util.List;
import racingcar.domain.Cars;
import racingcar.domain.MovingStrategy;
import racingcar.domain.RacingGame;

public class GameInitializer {

    private final MovingStrategy movingStrategy;

    public GameInitializer(MovingStrategy movingStrategy) {
        this.movingStrategy = movingStrategy;
    }

    public RacingGame createGame(List<String> carNames) {
        Cars cars = Cars.from(carNames);
        return new RacingGame(cars);
    }


}
