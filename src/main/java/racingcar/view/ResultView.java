package racingcar.view;

import java.util.List;
import racingcar.domain.car.Car;

public class ResultView {
    private static final String ROUND_HEADER = "\n실행 결과";

    public void printRoundHeader() {
        System.out.println(ROUND_HEADER);
    }


    public void printRoundResult(List<Car> cars) {
        for (Car car : cars) {
            System.out.println(car.getStatusDisplay());
        }
        System.out.println();
    }

}
