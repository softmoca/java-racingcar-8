package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;

public class ConsoleInputReader implements InputReader {

    private static final String REQUEST_CAR_NAMES =
            "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String REQUEST_ROUNDS =
            "시도할 횟수는 몇 회인가요?";

    @Override
    public List<String> readCarNames() {
        System.out.println(REQUEST_CAR_NAMES);
        String input = Console.readLine();
        return CarNameParser.parse(input);
    }

    @Override
    public int readRounds() {
        System.out.println(REQUEST_ROUNDS);
        String input = Console.readLine();
        return parseRounds(input);
    }

    private int parseRounds(String input) {
        try {
            int rounds = Integer.parseInt(input);
            validateRounds(rounds);
            return rounds;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
    }

    private void validateRounds(int rounds) {
        if (rounds <= 0) {
            throw new IllegalArgumentException("시도 횟수는 1 이상이어야 합니다.");
        }
    }
}
