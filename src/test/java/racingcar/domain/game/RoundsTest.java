package racingcar.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class RoundsTest {

    @Test
    void 양수로_라운드를_생성한다() {
        // given & when
        Rounds rounds = Rounds.from(5);

        // then
        assertThat(rounds.getValue()).isEqualTo(5);
    }

    @Test
    void 최소값_1로_라운드를_생성한다() {
        // given & when
        Rounds rounds = Rounds.from(1);

        // then
        assertThat(rounds.getValue()).isEqualTo(1);
    }

    @Test
    void 시도_횟수가_0이면_예외가_발생한다() {
        // when & then
        assertThatThrownBy(() -> Rounds.from(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1 이상");
    }

    @Test
    void 시도_횟수가_음수면_예외가_발생한다() {
        // when & then
        assertThatThrownBy(() -> Rounds.from(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1 이상");
    }

    @Test
    void 같은_횟수면_동등하다() {
        // given
        Rounds rounds1 = Rounds.from(5);
        Rounds rounds2 = Rounds.from(5);

        // when & then
        assertThat(rounds1).isEqualTo(rounds2);
    }
}
