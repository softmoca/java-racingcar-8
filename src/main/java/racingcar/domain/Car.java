package racingcar.domain;


public class Car {
    private static final int MAX_NAME_LENGTH = 5;
    private final Name name;
    private Position position;

    public Car(String name) {
        this.name = Name.from(name);
        this.position = Position.start();
    }

    public void move(boolean shouldMove) {
        if (shouldMove) {
            position = position.increase();
        }
    }

    public String getName() {
        return name.getValue();
    }

    public int getPosition() {
        return position.getValue();
    }


}
