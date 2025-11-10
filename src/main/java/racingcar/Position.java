package racingcar;


import java.util.Objects;

public class Position {

    private final int value;

    private Position(int value) {
        validateValue(value);
        this.value = value;
    }

    public static Position start() {
        return new Position(0);
    }

    public static Position from(int value) {
        return new Position(value);
    }

    private void validateValue(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("위치는 음수일 수 없습니다.");
        }
    }

    public Position increase() {
        return new Position(this.value + 1);
    }


    public int getValue() {
        return value;
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
}
