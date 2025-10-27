package racingcar.view;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.car.Car;

public class ResultView {
    private static final String ROUND_HEADER = "\n실행 결과";
    private static final String WINNER_PREFIX = "최종 우승자 : ";
    private static final String NAME_DELIMITER = ", ";
    private static final String POSITION_MARK = "-";

    public void printRoundHeader() {
        System.out.println(ROUND_HEADER);
    }

    public void printWinners(List<Car> winners) {
        String winnerNames = winners.stream()
                .map(Car::getName)
                .collect(Collectors.joining(NAME_DELIMITER));
        System.out.println(WINNER_PREFIX + winnerNames);
    }

    public void printRoundResult(List<Car> cars) {
        cars.stream()
                .map(this::formatCarStatus)
                .forEach(System.out::println);
        System.out.println();
    }


    private String formatCarStatus(Car car) {
        String positionDisplay = formatPosition(car.getPosition().getValue());
        return car.getName() + " : " + positionDisplay;
    }

    private String formatPosition(int positionValue) {
        return POSITION_MARK.repeat(positionValue);
    }

    public void printError(String message) {
        System.out.println(message);
    }

}
