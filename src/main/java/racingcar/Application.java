package racingcar;

import java.util.List;

public class Application {
    public static void main(String[] args) {
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
            System.out.println("[ERROR] " + e.getMessage());
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
