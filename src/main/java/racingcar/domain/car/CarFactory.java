package racingcar.domain.car;

import java.util.ArrayList;
import java.util.List;

public class CarFactory {

    public List<Car> createCars(List<String> names) {
        List<Car> cars = new ArrayList<>();
        for (String name : names) {
            cars.add(new Car(name));
        }
        return cars;
    }
}