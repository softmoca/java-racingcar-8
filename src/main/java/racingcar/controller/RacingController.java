package racingcar.controller;

import java.util.List;
import racingcar.domain.Cars;
import racingcar.domain.MovingStrategy;
import racingcar.domain.RacingGame;
import racingcar.domain.RacingResult;
import racingcar.domain.RandomMovingStrategy;
import racingcar.view.InputReader;
import racingcar.view.OutputWriter;

public class RacingController {

    private final InputReader inputReader;
    private final OutputWriter outputWriter;

    public RacingController(InputReader inputReader, OutputWriter outputWriter) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
    }


    public void run() {
        try {
            executeGame();
        } catch (IllegalArgumentException e) {
            outputWriter.printError(e.getMessage());
        }
    }

    private void executeGame() {
        // 1. 입력
        List<String> carNames = inputReader.readCarNames();
        int rounds = inputReader.readRounds();

        // 2. 게임 준비
        Cars cars = Cars.from(carNames);
        MovingStrategy strategy = new RandomMovingStrategy();
        RacingGame game = new RacingGame(cars);

        // 3. 게임 실행
        outputWriter.printHeader();
        RacingResult result = game.run(strategy, rounds);

        // 4. 결과 출력
        printGameProgress(carNames, result.getRounds());
        outputWriter.printWinners(result.getWinners());
    }

    private void printGameProgress(List<String> names,
                                   List<List<Integer>> rounds) {
        for (List<Integer> positions : rounds) {
            outputWriter.printRound(names, positions);
        }
    }
}
