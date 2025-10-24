package racingcar.domain.car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CarFactory 통합 테스트")
class CarFactoryTest {

    private CarFactory carFactory;

    @BeforeEach
    void setUp() {
        carFactory = new CarFactory();
    }

    @Test
    @DisplayName("자동차 이름 목록으로 자동차들을 생성한다")
    void createCarsFromNames() {
        // given
        List<String> names = Arrays.asList("moca", "coco", "aa");

        // when
        List<Car> cars = carFactory.createCars(names);

        // then
        assertThat(cars).hasSize(3);
        assertThat(cars)
                .extracting(Car::getName)
                .containsExactly("moca", "coco", "aa");
    }

    @Test
    @DisplayName("생성된 모든 자동차는 초기 위치 0을 가진다")
    void carsHaveInitialPositionZero() {
        // given
        List<String> names = Arrays.asList("moca", "coco");

        // when
        List<Car> cars = carFactory.createCars(names);

        // then
        assertThat(cars)
                .allMatch(car -> car.getPosition().toDisplayString().isEmpty());
    }


    @Test
    @DisplayName("생성된 자동차들은 독립적으로 동작한다")
    void carsAreIndependent() {
        // given
        List<String> names = Arrays.asList("moca", "coco");
        List<Car> cars = carFactory.createCars(names);

        // when
        cars.get(0).move(true);
        cars.get(0).move(true);
        cars.get(1).move(true);

        // then
        assertThat(cars.get(0).getPosition().toDisplayString()).isEqualTo("--");
        assertThat(cars.get(1).getPosition().toDisplayString()).isEqualTo("-");
    }

}