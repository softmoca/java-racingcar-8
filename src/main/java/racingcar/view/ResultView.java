package racingcar.view;

import java.util.ArrayList;
import java.util.List;
import racingcar.domain.car.Car;

public class ResultView {
    private static final String ROUND_HEADER = "\n실행 결과";
    private static final String WINNER_PREFIX = "최종 우승자 : ";
    private static final String NAME_DELIMITER = ", ";

    public void printRoundHeader() {
        System.out.println(ROUND_HEADER);
    }


    public void printRoundResult(List<Car> cars) {
        for (Car car : cars) {
            System.out.println(car.getStatusDisplay());
        }
        System.out.println();
    }

    public void printWinners(List<Car> winners) {
        List<String> winnerNames = extractNames(winners);
        System.out.println(WINNER_PREFIX + String.join(NAME_DELIMITER, winnerNames));
    }

    private List<String> extractNames(List<Car> cars) {
        List<String> names = new ArrayList<>();
        for (Car car : cars) {
            names.add(car.getName());
        }
        return names;
    }

}
