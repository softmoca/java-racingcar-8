package racingcar.controller.fixture;

import java.util.List;
import racingcar.view.input.InputReader;


public class FixedInputReader implements InputReader {

    private final List<String> carNames;
    private final int rounds;

    public FixedInputReader(List<String> carNames, int rounds) {
        this.carNames = carNames;
        this.rounds = rounds;
    }

    @Override
    public List<String> readCarNames() {
        return carNames;
    }

    @Override
    public int readRounds() {
        return rounds;
    }
}
