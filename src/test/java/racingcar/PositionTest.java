package racingcar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    void 초기_위치_0으로_생성된다() {
        Position position = Position.start();

        assertThat(position.getValue()).isEqualTo(0);
    }

    @Test
    void 양수_위치로_생성할_수_있다() {
        Position position = Position.from(3);

        assertThat(position.getValue()).isEqualTo(3);
    }

    @Test
    void 음수_위치는_생성할_수_없다() {
        assertThatThrownBy(() -> Position.from(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 위치를_1_증가시킬_수_있다() {

        Position position = Position.start();
        Position moved = position.increase();

        assertThat(moved.getValue()).isEqualTo(1);
        assertThat(position.getValue()).isEqualTo(0);
    }

    @Test
    void 같은_위치면_동등하다() {
        Position position1 = Position.from(3);
        Position position2 = Position.from(3);

        assertThat(position1).isEqualTo(position2);
    }


}
