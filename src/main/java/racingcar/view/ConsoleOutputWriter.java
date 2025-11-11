package racingcar.view;

import java.util.List;

public class ConsoleOutputWriter implements OutputWriter {

    private static final String RESULT_HEADER = "\n실행 결과";
    private static final String WINNER_PREFIX = "최종 우승자 : ";
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String POSITION_SEPARATOR = " : ";
    private static final String WINNER_DELIMITER = ", ";

    @Override
    public void printHeader() {
        System.out.println(RESULT_HEADER);
    }

    @Override
    public void printRound(List<String> names, List<Integer> positions) {
        for (int i = 0; i < names.size(); i++) {
            printCarPosition(names.get(i), positions.get(i));
        }
        System.out.println();
    }

    private void printCarPosition(String name, int position) {
        String positionMarks = PositionFormatter.format(position);
        System.out.println(name + POSITION_SEPARATOR + positionMarks);
    }

    @Override
    public void printWinners(List<String> winners) {
        String winnersText = String.join(WINNER_DELIMITER, winners);
        System.out.println(WINNER_PREFIX + winnersText);
    }

    @Override
    public void printError(String message) {
        System.out.println(ERROR_PREFIX + message);
    }
}
