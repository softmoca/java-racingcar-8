package racingcar;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CarNameParser {

    private static final String DELIMITER = ",";

    public static List<String> parse(String input) {
        validateInput(input);

        List<String> names = Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
        validateNames(names);
        return names;
    }

    private static void validateInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("자동차 이름을 입력해주세요.");
        }
    }

    private static void validateNames(List<String> names) {
        if (names.stream().anyMatch(String::isEmpty)) {
            throw new IllegalArgumentException("빈 이름은 입력할 수 없습니다.");
        }
    }

}
