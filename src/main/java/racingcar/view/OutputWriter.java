package racingcar.view;

import java.util.List;

public interface OutputWriter {
    void printHeader();

    void printRound(List<String> names, List<Integer> positions);

    void printWinners(List<String> winners);

    void printError(String message);
}
