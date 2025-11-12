package racingcar.controller;

import java.util.List;
import racingcar.view.output.OutputWriter;

class MockOutputWriter implements OutputWriter {
    private boolean headerPrinted = false;
    private int roundCount = 0;
    private boolean winnersPrinted = false;
    private String errorMessage = null;

    @Override
    public void printHeader() {
        headerPrinted = true;
    }

    @Override
    public void printRound(List<String> names, List<Integer> positions) {
        roundCount++;
    }

    @Override
    public void printWinners(List<String> winners) {
        winnersPrinted = true;
    }

    @Override
    public void printError(String message) {
        errorMessage = message;
    }

    public boolean isHeaderPrinted() {
        return headerPrinted;
    }

    public int getRoundCount() {
        return roundCount;
    }

    public boolean isWinnersPrinted() {
        return winnersPrinted;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
