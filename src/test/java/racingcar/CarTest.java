package racingcar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class CarTest {

    @Test
    void 이름으로_자동차를_생성한다() {
        // when
        Car car = new Car("pobi");

        // then
        assertThat(car.getName()).isEqualTo("pobi");
    }


    @Test
    void 이름이_5자를_초과하면_예외가_발생한다() {

        assertThatThrownBy(() -> new Car("pobi12"))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void 이름이_5자면_자동차가_생성된다() {
        Car car = new Car("abcde");

        assertThat(car.getName()).isEqualTo("abcde");
    }

    @Test
    void 이름이_1자면_자동차가_생성된다() {
        Car car = new Car("a");

        assertThat(car.getName()).isEqualTo("a");
    }


}
