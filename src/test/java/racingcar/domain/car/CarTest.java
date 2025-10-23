package racingcar.domain.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Car 클래스 테스트")
class CarTest {

    @Test
    @DisplayName("자동차를 생성하면 초기 위치는 0이다")
    void createCarWithInitialPosition() {
        // given
        Car car = new Car("moca");

        // when
        String statusDisplay = car.getStatusDisplay();

        // then
        assertThat(statusDisplay).isEqualTo("moca : ");
    }



    @Test
    @DisplayName("이동 조건이 true이면 전진한다")
    void moveWhenConditionIsTrue() {
        // given
        Car car = new Car("moca");

        // when
        car.move(true);

        // then
        assertThat(car.getStatusDisplay()).isEqualTo("moca : -");
    }

    @Test
    @DisplayName("이동 조건이 false이면 정지한다")
    void stayWhenConditionIsFalse() {
        // given
        Car car = new Car("moca");

        // when
        car.move(false);

        // then
        assertThat(car.getStatusDisplay()).isEqualTo("moca : ");
    }

    @Test
    @DisplayName("여러 번 이동할 수 있다")
    void moveMultipleTimes() {
        // given
        Car car = new Car("moca");

        // when
        car.move(true);
        car.move(false);
        car.move(true);
        car.move(true);

        // then
        assertThat(car.getStatusDisplay()).isEqualTo("moca : ---");
    }

    @Test
    @DisplayName("자동차 상태를 표시 문자열로 반환한다")
    void getStatusDisplay() {
        // given
        Car car = new Car("moca");

        // when
        car.move(true);
        car.move(true);
        String statusDisplay = car.getStatusDisplay();

        // then
        assertThat(statusDisplay).isEqualTo("moca : --");
    }

    @Test
    @DisplayName("자동차의 위치를 조회할 수 있다")
    void getPosition() {
        // given
        Car car = new Car("moca");

        // when
        car.move(true);
        Position position = car.getPosition();

        // then
        assertThat(position.toDisplayString()).isEqualTo("-");
    }

    @Test
    @DisplayName("자동차 이름을 조회할 수 있다")
    void getCarName() {
        // given
        Car car = new Car("moca");

        // when
        String name = car.getName();

        // then
        assertThat(name).isEqualTo("moca");
    }

}