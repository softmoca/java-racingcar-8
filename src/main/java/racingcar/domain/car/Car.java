package racingcar.domain.car;

public class Car {
    private final String name;
    private Position position;

    public Car(String name) {
        this.name = name;
        this.position = Position.initial();
    }
}