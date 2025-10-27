package racingcar.domain.validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarNameValidator {
    private static final int MAXIMUM_NAME_LENGTH = 5;
    static final String EMPTY_NAME_ERROR = "자동차 이름은 비어있을 수 없습니다.";
    static final String LONG_NAME_ERROR = "자동차 이름은 5자 이하여야 합니다: ";
    static final String DUPLICATE_NAME_ERROR = "자동차 이름은 중복될 수 없습니다: ";

    public void validate(List<String> names) {
        validateNotEmpty(names);
        validateLength(names);
        validateDuplication(names);
    }

    private void validateNotEmpty(List<String> names) {
        names.forEach(this::checkNameIsNotEmpty);
    }

    private void checkNameIsNotEmpty(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(EMPTY_NAME_ERROR);
        }
    }

    private void validateLength(List<String> names) {
        names.forEach(this::checkNameLength);
    }

    private void checkNameLength(String name) {
        if (name.length() > MAXIMUM_NAME_LENGTH) {
            throw new IllegalArgumentException(LONG_NAME_ERROR + name);
        }
    }

    private void validateDuplication(List<String> names) {
        Set<String> uniqueNames = new HashSet<>();
        names.forEach(name -> checkDuplication(name, uniqueNames));
    }

    private void checkDuplication(String name, Set<String> uniqueNames) {
        if (!uniqueNames.add(name)) {
            throw new IllegalArgumentException(DUPLICATE_NAME_ERROR + name);
        }
    }
}