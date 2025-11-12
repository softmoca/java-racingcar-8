package racingcar.view.input;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import racingcar.domain.game.Rounds;

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
    public Rounds readRounds() {
        System.out.println(REQUEST_ROUNDS);
        String input = Console.readLine();
        return parseRounds(input);
    }

    private Rounds parseRounds(String input) {
        try {
            int value = Integer.parseInt(input);
            return Rounds.from(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
    }

}
