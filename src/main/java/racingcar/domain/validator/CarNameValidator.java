package racingcar.domain.validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarNameValidator {
    private static final int MAX_NAME_LENGTH = 5;
    private static final String EMPTY_NAME_ERROR = "자동차 이름은 비어있을 수 없습니다.";
    private static final String LONG_NAME_ERROR = "자동차 이름은 5자 이하여야 합니다: ";
    private static final String DUPLICATE_NAME_ERROR = "자동차 이름은 중복될 수 없습니다: ";

    public void validate(List<String> names) {
        validateNotEmpty(names);
        validateLength(names);
        validateDuplication(names);
    }

    private void validateNotEmpty(List<String> names) {
        for (String name : names) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException(EMPTY_NAME_ERROR);
            }
        }
    }

    private void validateLength(List<String> names) {
        for (String name : names) {
            if (name.length() > MAX_NAME_LENGTH) {
                throw new IllegalArgumentException(LONG_NAME_ERROR + name);
            }
        }
    }

    private void validateDuplication(List<String> names) {
        Set<String> uniqueNames = new HashSet<>();
        for (String name : names) {
            if (!uniqueNames.add(name)) {
                throw new IllegalArgumentException(DUPLICATE_NAME_ERROR + name);
            }
        }
    }
}