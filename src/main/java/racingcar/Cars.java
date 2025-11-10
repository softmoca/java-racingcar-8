package racingcar;

import java.util.List;
import java.util.stream.Collectors;

public class Cars {

    private final List<Car> cars;

    private Cars(List<Car> cars) {
        this.cars = cars;
    }

    public static Cars from(List<String> names) {
        List<Car> cars = names.stream()
                .map(Car::new)
                .collect(Collectors.toList());

        return new Cars(cars);
    }

    public int size() {
        return cars.size();
    }


    public List<String> getNames() {
        return cars.stream()
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    public void moveAll(MovingStrategy strategy) {
        cars.forEach(car -> {
            boolean shouldMove = strategy.shouldMove();
            car.move(shouldMove);
        });
    }

    public List<Integer> getPositions() {
        return cars.stream()
                .map(Car::getPosition)
                .collect(Collectors.toList());
    }

}
