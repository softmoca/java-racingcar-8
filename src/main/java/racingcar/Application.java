package racingcar;

import racingcar.controller.GameInitializer;
import racingcar.controller.RacingController;
import racingcar.domain.strategy.MovingStrategy;
import racingcar.domain.strategy.RandomMovingStrategy;
import racingcar.view.input.ConsoleInputReader;
import racingcar.view.input.InputReader;
import racingcar.view.output.ConsoleOutputWriter;
import racingcar.view.output.OutputWriter;

public class Application {
    public static void main(String[] args) {
        InputReader inputReader = new ConsoleInputReader();
        OutputWriter outputWriter = new ConsoleOutputWriter();

        MovingStrategy strategy = new RandomMovingStrategy();
        GameInitializer gameInitializer = new GameInitializer(strategy);

        RacingController controller = new RacingController(
                inputReader,
                outputWriter,
                gameInitializer
        );
        controller.run();
    }
}
