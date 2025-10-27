package racingcar.controller;

import java.util.List;
import racingcar.domain.car.Car;
import racingcar.domain.car.CarFactory;
import racingcar.domain.game.RacingGame;
import racingcar.domain.strategy.RandomMoveStrategy;
import racingcar.domain.validator.AttemptValidator;
import racingcar.domain.validator.CarNameValidator;
import racingcar.view.InputView;
import racingcar.view.ResultView;

public class RacingController {
    private final InputView inputView;
    private final ResultView resultView;
    private final CarNameValidator carNameValidator;
    private final AttemptValidator attemptValidator;
    private final CarFactory carFactory;

    public RacingController() {
        this.inputView = new InputView();
        this.resultView = new ResultView();
        this.carNameValidator = new CarNameValidator();
        this.attemptValidator = new AttemptValidator();
        this.carFactory = new CarFactory();
    }

    public void run() {

        try {
            List<String> carNames = readAndValidateCarNames();
            int attemptCount = readAndValidateAttemptCount();
            List<Car> cars = carFactory.createCars(carNames);

            RacingGame game = new RacingGame(cars, attemptCount, new RandomMoveStrategy());
            playGame(game);
            announceWinners(game);
        } catch (IllegalArgumentException e) {
            resultView.printError(e.getMessage());
            throw e;
        }
    }

    private List<String> readAndValidateCarNames() {
        List<String> carNames = inputView.readCarNames();
        carNameValidator.validate(carNames);
        return carNames;
    }

    private int readAndValidateAttemptCount() {
        String input = inputView.readAttemptCount();
        return attemptValidator.validate(input);
    }

    private void playGame(RacingGame game) {
        resultView.printRoundHeader();
        game.start(resultView::printRoundResult);
    }

    private void announceWinners(RacingGame game) {
        List<Car> winners = game.getWinners();
        resultView.printWinners(winners);
    }
}