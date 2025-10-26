package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.*;

public class InputView {
    private static final String CAR_NAMES_PROMPT = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String ATTEMPT_COUNT_PROMPT = "시도할 횟수는 몇 회인가요?";
    private static final String DELIMITER = ",";


    public List<String> readCarNames() {
        System.out.println(CAR_NAMES_PROMPT);
        String input = Console.readLine();
        return parseCarNames(input);
    }

    //TODO 검증 로직 추가
    private List<String> parseCarNames(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .toList();
    }

    public String readAttemptCount() {
        System.out.println(ATTEMPT_COUNT_PROMPT);
        return Console.readLine();
    }

}