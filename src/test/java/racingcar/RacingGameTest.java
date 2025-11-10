package racingcar;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class RacingGameTest {

    @Test
    void 게임을_실행하고_우승자를_반환한다() {
        // given
        Cars cars = Cars.from(Arrays.asList("pobi", "woni", "jun"));
        RacingGame game = new RacingGame(cars);
        MovingStrategy alwaysMove = () -> true;

        // when:
        RacingResult result = game.run(alwaysMove, 3);

        // then:
        assertThat(result.getWinners())
                .containsExactly("pobi", "woni", "jun");
    }
}
