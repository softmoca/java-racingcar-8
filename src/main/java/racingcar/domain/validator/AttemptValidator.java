package racingcar.domain.validator;

public class AttemptValidator {
    static final String NOT_NUMBER_ERROR = "시도 횟수는 숫자여야 합니다.";
    static final String NOT_POSITIVE_ERROR = "시도 횟수는 1 이상이어야 합니다.";

    public int validate(String input) {
        int attemptCount = parseToInt(input);
        validatePositive(attemptCount);
        return attemptCount;
    }

    private int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER_ERROR);
        }
    }

    private void validatePositive(int attemptCount) {
        if (attemptCount < 1) {
            throw new IllegalArgumentException(NOT_POSITIVE_ERROR);
        }
    }
}
