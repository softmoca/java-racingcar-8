package racingcar.domain.game;

import java.util.Comparator;
import java.util.List;
import racingcar.domain.car.Car;
import racingcar.domain.car.Position;

public class WinnerFinder {

    public List<Car> findWinners(List<Car> cars) {
        Position maxPosition = findMaxPosition(cars);
        return filterWinners(cars, maxPosition);
    }

    private Position findMaxPosition(List<Car> cars) {
        return cars.stream()
                .map(Car::getPosition)
                .max(Comparator.comparingInt(Position::getValue))
                .get();
    }

    private List<Car> filterWinners(List<Car> cars, Position maxPosition) {
        return cars.stream()
                .filter(car -> car.getPosition().equals(maxPosition))
                .toList();
    }
}
