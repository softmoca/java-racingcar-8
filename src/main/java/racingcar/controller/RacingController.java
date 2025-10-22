package racingcar.controller;

import java.util.List;
import racingcar.domain.car.Car;
import racingcar.domain.car.CarFactory;
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
        List<String> carNames = readAndValidateCarNames();
        int attemptCount = readAndValidateAttemptCount();
        List<Car> cars = carFactory.createCars(carNames);



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

}