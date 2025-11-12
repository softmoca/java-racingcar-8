package racingcar.domain.car;

import java.util.Objects;

public class Name {

    private static final int MAX_LENGTH = 5;

    private final String value;

    private Name(String value) {
        validateValue(value);
        this.value = value;
    }

    public static Name from(String value) {
        return new Name(value);
    }

    private void validateValue(String value) {
        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(
                    "자동차 이름은 " + MAX_LENGTH + "자 이하여야 합니다."
            );
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name name = (Name) o;
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
