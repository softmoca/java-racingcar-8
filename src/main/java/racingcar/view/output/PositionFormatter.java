package racingcar.view.output;

public class PositionFormatter {

    private static final String POSITION_MARK = "-";

    public static String format(int position) {
        return POSITION_MARK.repeat(position);
    }
}
