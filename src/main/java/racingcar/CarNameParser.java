package racingcar;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CarNameParser {

    private static final String DELIMITER = ",";

    public static List<String> parse(String input) {

        List<String> names = Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());

        return names;
    }


}
