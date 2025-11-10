package racingcar.controller;

import java.util.List;
import racingcar.domain.Cars;
import racingcar.domain.MovingStrategy;
import racingcar.domain.RacingGame;
import racingcar.domain.RacingResult;
import racingcar.domain.RandomMovingStrategy;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingController {

    public static void run() {
        try {
            List<String> carNames = InputView.readCarNames();
            int rounds = InputView.readRounds();

            Cars cars = Cars.from(carNames);
            MovingStrategy strategy = new RandomMovingStrategy();
            RacingGame game = new RacingGame(cars);

            OutputView.printHeader();
            RacingResult result = game.run(strategy, rounds);

            printGameProgress(carNames, result.getRounds());
            OutputView.printWinners(result.getWinners());

        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            throw e;
        }
    }

    private static void printGameProgress(List<String> names,
                                          List<List<Integer>> rounds) {
        for (List<Integer> positions : rounds) {
            OutputView.printRound(names, positions);
        }
    }
}
