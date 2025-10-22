package racingcar.domain.game;

import racingcar.domain.car.Car;
import racingcar.domain.car.Position;
import java.util.ArrayList;
import java.util.List;

public class WinnerFinder {

    public List<Car> findWinners(List<Car> cars) {
        Position maxPosition = findMaxPosition(cars);
        return ;
    }

    private Position findMaxPosition(List<Car> cars) {
        Position maxPosition = cars.get(0).getPosition();
        for (Car car : cars) {
            Position currentPosition = car.getPosition();
            if (currentPosition.isGreaterThan(maxPosition)) {
                maxPosition = currentPosition;
            }
        }
        return maxPosition;
    }


}