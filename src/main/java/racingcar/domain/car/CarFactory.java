package racingcar.domain.car;

import java.util.ArrayList;
import java.util.List;

public class CarFactory {

    public List<Car> createCars(List<String> names) {
        return names.stream()
                .map(Car::new)
                .toList();
    }
}