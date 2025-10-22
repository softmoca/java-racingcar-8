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
        Position maxPosition = cars.get(0).getPosition();
        for (Car car : cars) {
            Position currentPosition = car.getPosition();
            if (currentPosition.isGreaterThan(maxPosition)) {
                maxPosition = currentPosition;
            }
        }
        return maxPosition;
    }

    private List<Car> filterWinners(List<Car> cars, Position maxPosition) {
        List<Car> winners = new ArrayList<>();
        for (Car car : cars) {
            if (car.getPosition().equals(maxPosition)) {
                winners.add(car);
            }
        }
        return winners;
    }


}