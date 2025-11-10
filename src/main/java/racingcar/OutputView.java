package racingcar;

import java.util.List;

public class OutputView {

    private static final String RESULT_HEADER = "\n실행 결과";
    private static final String WINNER_PREFIX = "최종 우승자 : ";
    private static final String POSITION_SEPARATOR = " : ";
    private static final String WINNER_DELIMITER = ", ";

    public static void printHeader() {
        System.out.println(RESULT_HEADER);
    }

    public static void printRound(List<String> names, List<Integer> positions) {
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            int position = positions.get(i);

            System.out.println(name + POSITION_SEPARATOR +
                    PositionFormatter.format(position));
        }
        System.out.println();
    }

    public static void printWinners(List<String> winners) {
        String winnersText = String.join(WINNER_DELIMITER, winners);
        System.out.println(WINNER_PREFIX + winnersText);
    }
}
