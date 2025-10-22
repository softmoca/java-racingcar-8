package racingcar.domain.car;

public class Position {
    private static final String POSITION_MARK = "-";
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

    public String toDisplayString() {
        StringBuilder display = new StringBuilder();
        for (int i = 0; i < value; i++) {
            display.append(POSITION_MARK);
        }
        return display.toString();
    }

    public boolean isGreaterThan(Position other) {
        return this.value > other.value;
    }


}