package racingcar;

import org.junit.jupiter.api.Test;

class CarTest {

    @Test
    void 이름으로_자동차를_생성한다() {
        // when
        Car car = new Car("pobi");

        // then
        assertThat(car.getName()).isEqualTo("pobi");
    }


}
