package racingcar.domain.game;

import racingcar.domain.car.Car;
import racingcar.domain.car.Position;
import java.util.ArrayList;
import java.util.List;

public class WinnerFinder {

    public List<Car> findWinners(List<Car> cars) {
        Position maxPosition = findMaxPosition(cars);
        return filterWinners(cars, maxPosition);
    }

    private Position findMaxPosition(List<Car> cars) {
        return cars.stream()
                .map(Car::getPosition)
                .max((p1, p2) -> p1.isGreaterThan(p2) ? 1 : (p2.isGreaterThan(p1) ? -1 : 0))
                .get();
    }

    private List<Car> filterWinners(List<Car> cars, Position maxPosition) {
        return cars.stream()
                .filter(car -> car.getPosition().equals(maxPosition))
                .toList();
    }


}