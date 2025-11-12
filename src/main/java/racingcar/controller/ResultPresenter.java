package racingcar.controller;

import java.util.List;
import racingcar.domain.game.RacingResult;
import racingcar.view.output.OutputWriter;

public class ResultPresenter {

    private final List<String> carNames;
    private final OutputWriter outputWriter;

    public ResultPresenter(List<String> carNames, OutputWriter outputWriter) {
        this.carNames = carNames;
        this.outputWriter = outputWriter;
    }

    public void present(RacingResult result) {
        printHeader();
        printProgress(result.getRounds());
        printWinners(result.getWinners());
    }

    private void printHeader() {
        outputWriter.printHeader();
    }

    private void printProgress(List<List<Integer>> rounds) {
        for (List<Integer> positions : rounds) {
            printRound(positions);
        }
    }

    private void printRound(List<Integer> positions) {
        outputWriter.printRound(carNames, positions);
    }

    private void printWinners(List<String> winners) {
        outputWriter.printWinners(winners);
    }
}
