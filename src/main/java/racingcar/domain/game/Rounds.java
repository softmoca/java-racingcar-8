package racingcar.domain.game;

import java.util.Objects;

public class Rounds {

    private static final int MIN_VALUE = 1;

    private final int value;

    private Rounds(int value) {
        validate(value);
        this.value = value;
    }

    public static Rounds from(int value) {
        return new Rounds(value);
    }

    private void validate(int value) {
        if (value < MIN_VALUE) {
            throw new IllegalArgumentException(
                    "시도 횟수는 " + MIN_VALUE + " 이상이어야 합니다."
            );
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rounds rounds = (Rounds) o;
        return value == rounds.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Rounds{" + value + "}";
    }
}
