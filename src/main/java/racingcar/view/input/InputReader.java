package racingcar.view.input;

import java.util.List;
import racingcar.domain.game.Rounds;

public interface InputReader {
    List<String> readCarNames();

    Rounds readRounds();
}
