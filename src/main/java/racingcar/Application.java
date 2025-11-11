package racingcar;

import racingcar.controller.RacingController;
import racingcar.view.ConsoleInputReader;
import racingcar.view.ConsoleOutputWriter;
import racingcar.view.InputReader;
import racingcar.view.OutputWriter;

public class Application {
    public static void main(String[] args) {
        InputReader inputReader = new ConsoleInputReader();
        OutputWriter outputWriter = new ConsoleOutputWriter();

        RacingController controller = new RacingController(
                inputReader,
                outputWriter
        );
        controller.run();
    }
}
