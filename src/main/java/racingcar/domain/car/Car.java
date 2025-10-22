package racingcar.domain.car;

public class Car {
    private final String name;
    private Position position;

    public Car(String name) {
        this.name = name;
        this.position = Position.initial();
    }

    public void move(boolean shouldMove) {
        if (shouldMove) {
            position = position.moveForward();
        }
    }

    public String getStatusDisplay() {
        return name + " : " + position.toDisplayString();
    }

    public Position getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

}