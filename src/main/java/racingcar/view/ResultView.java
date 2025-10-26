package racingcar.view;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.car.Car;

public class ResultView {
    private static final String ROUND_HEADER = "\n실행 결과";
    private static final String WINNER_PREFIX = "최종 우승자 : ";
    private static final String NAME_DELIMITER = ", ";

    public void printRoundHeader() {
        System.out.println(ROUND_HEADER);
    }


    public void printRoundResult(List<Car> cars) {
        cars.forEach(car -> System.out.println(car.getStatusDisplay()));
        System.out.println();
    }

    public void printWinners(List<Car> winners) {
        String winnerNames = winners.stream()
                .map(Car::getName)
                .collect(Collectors.joining(NAME_DELIMITER));
        System.out.println(WINNER_PREFIX + winnerNames);
    }

}
