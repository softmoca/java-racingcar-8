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

        return names;
    }

    private static void validateInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("자동차 이름을 입력해주세요.");
        }
    }

}
