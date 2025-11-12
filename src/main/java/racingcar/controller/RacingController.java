package racingcar.controller;

import java.util.List;
import racingcar.domain.game.RacingGame;
import racingcar.domain.game.RacingResult;
import racingcar.domain.game.Rounds;
import racingcar.domain.strategy.MovingStrategy;
import racingcar.view.input.InputReader;
import racingcar.view.output.OutputWriter;

public class RacingController {

    private final InputReader inputReader;
    private final OutputWriter outputWriter;
    private final GameInitializer gameInitializer;

    public RacingController(
            InputReader inputReader,
            OutputWriter outputWriter,
            GameInitializer gameInitializer) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.gameInitializer = gameInitializer;
    }


    public void run() {
        try {
            executeGame();
        } catch (IllegalArgumentException e) {
            outputWriter.printError(e.getMessage());
            throw e;
        }
    }

    private void executeGame() {
        // 1. 입력
        List<String> carNames = inputReader.readCarNames();
        Rounds rounds = inputReader.readRounds();

        // 2. 게임 준비
        RacingGame game = gameInitializer.createGame(carNames);
        MovingStrategy strategy = gameInitializer.getMovingStrategy();

        // 3. 게임 실행
        outputWriter.printHeader();
        RacingResult result = game.run(strategy, rounds);

        // 4. 결과 출력
        ResultPresenter presenter = new ResultPresenter(carNames, outputWriter);
        presenter.present(result);
    }

}
