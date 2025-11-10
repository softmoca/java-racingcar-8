package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
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

    @Test
    void 각_라운드의_자동차_위치를_기록한다() {
        // given
        Cars cars = Cars.from(Arrays.asList("pobi", "woni", "jun"));
        RacingGame game = new RacingGame(cars);
        MovingStrategy alwaysMove = () -> true;

        // when
        RacingResult result = game.run(alwaysMove, 3);

        // then
        List<List<Integer>> rounds = result.getRounds();
        assertThat(rounds).hasSize(3);
        assertThat(rounds.get(0)).containsExactly(1, 1, 1);
        assertThat(rounds.get(1)).containsExactly(2, 2, 2);
        assertThat(rounds.get(2)).containsExactly(3, 3, 3);
    }

}
