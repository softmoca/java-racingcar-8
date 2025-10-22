package racingcar.domain.car;

public class Position {

    private final int value;

    private Position(int value) {
        this.value = value;
    }

    public static Position initial() {
        return new Position(0);
    }

    public Position moveForward() {
        return new Position(this.value + 1);
    }

}