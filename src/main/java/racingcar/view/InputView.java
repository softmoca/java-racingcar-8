package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;

public class InputView {
    private static final String CAR_NAMES_PROMPT = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String ATTEMPT_COUNT_PROMPT = "시도할 횟수는 몇 회인가요?";

    public List<String> readCarNames() {
        System.out.println(CAR_NAMES_PROMPT);
        String input = Console.readLine();
        // TODO: 파싱 로직 구현
        return List.of();
    }

}