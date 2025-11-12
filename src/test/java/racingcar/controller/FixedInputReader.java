package racingcar.controller;

import java.util.List;
import racingcar.domain.game.Rounds;
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
    public Rounds readRounds() {
        return Rounds.from(rounds);
    }
}
