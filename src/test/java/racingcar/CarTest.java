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


    @Test
    void 생성된_자동차의_초기_위치는_0이다() {
        Car car = new Car("pobi");

        assertThat(car.getPosition()).isEqualTo(0);
    }

    @Test
    void 전진_조건이_true면_위치가_1_증가한다() {
        // given
        Car car = new Car("pobi");

        // when
        car.move(true);

        // then
        assertThat(car.getPosition()).isEqualTo(1);
    }

    @Test
    void 전진_조건이_false면_위치가_변하지_않는다() {
        // given
        Car car = new Car("pobi");

        // when
        car.move(false);

        // then
        assertThat(car.getPosition()).isEqualTo(0);
    }

    @Test
    void 여러_번_전진할_수_있다() {
        // given
        Car car = new Car("pobi");

        // when
        car.move(true);
        car.move(true);
        car.move(true);

        // then
        assertThat(car.getPosition()).isEqualTo(3);
    }

    @Test
    void 이동전략인터페이스로_자동차를_움직일_수_있다() {
        // given
        Car car = new Car("pobi");
        MovingStrategy alwaysMove = () -> true;

        // when: 전략을 사용해서 이동
        car.move(alwaysMove.shouldMove());

        // then
        assertThat(car.getPosition()).isEqualTo(1);
    }


}
