package racingcar.domain.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.car.Car;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("WinnerFinder 클래스 테스트")
class WinnerFinderTest {

    private WinnerFinder winnerFinder= new WinnerFinder();;

    @Test
    @DisplayName("단독 우승자를 찾는다")
    void findSingleWinner() {
        // given
        Car car1 = new Car("moca");
        Car car2 = new Car("coco");
        Car car3 = new Car("aa");

        car1.move(true);
        car1.move(true);
        car1.move(true);

        car2.move(true);
        car2.move(true);

        car3.move(true);

        List<Car> cars = Arrays.asList(car1, car2, car3);

        // when
        List<Car> winners = winnerFinder.findWinners(cars);

        // then
        assertThat(winners).hasSize(1);
        assertThat(winners.get(0).getName()).isEqualTo("moca");
    }

    @Test
    @DisplayName("공동 우승자를 찾는다 - 2명")
    void findMultipleWinners() {
        // given
        Car car1 = new Car("moca");
        Car car2 = new Car("coco");
        Car car3 = new Car("aaa");

        car1.move(true);
        car1.move(true);
        car1.move(true);

        car2.move(true);
        car2.move(true);
        car2.move(true);

        car3.move(true);

        List<Car> cars = Arrays.asList(car1, car2, car3);

        // when
        List<Car> winners = winnerFinder.findWinners(cars);

        // then
        assertThat(winners).hasSize(2);
        assertThat(winners)
                .extracting(Car::getName)
                .containsExactlyInAnyOrder("moca", "coco");
    }

    @Test
    @DisplayName("공동 우승자를 찾는다 - 3명")
    void findAllWinnersWhenTied() {
        // given
        Car car1 = new Car("moca");
        Car car2 = new Car("coco");
        Car car3 = new Car("aa");
        Car car4 = new Car("bb");

        car1.move(true);
        car1.move(true); // position: 2

        car2.move(true);
        car2.move(true); // position: 2

        car3.move(true);
        car3.move(true); // position: 2

        car4.move(true); // position: 1

        List<Car> cars = Arrays.asList(car1, car2, car3, car4);

        // when
        List<Car> winners = winnerFinder.findWinners(cars);

        // then
        assertThat(winners).hasSize(3);
        assertThat(winners)
                .extracting(Car::getName)
                .containsExactlyInAnyOrder("moca", "coco", "aa");
    }

    @Test
    @DisplayName("모든 자동차가 같은 위치면 모두 우승자다")
    void findAllWinnersWhenAllTied() {
        // given
        Car car1 = new Car("pobi");
        Car car2 = new Car("woni");
        Car car3 = new Car("jun");

        car1.move(true);
        car2.move(true);
        car3.move(true);

        List<Car> cars = Arrays.asList(car1, car2, car3);

        // when
        List<Car> winners = winnerFinder.findWinners(cars);

        // then
        assertThat(winners).hasSize(3);
        assertThat(winners)
                .extracting(Car::getName)
                .containsExactlyInAnyOrder("pobi", "woni", "jun");
    }


}