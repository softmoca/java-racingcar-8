package racingcar.domain.car;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        return IntStream.range(0, value)
                .mapToObj(i -> POSITION_MARK)
                .collect(Collectors.joining());
    }

    public boolean isGreaterThan(Position other) {
        return this.value > other.value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return value == position.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    public int getValue() {
        return value;
    }
}