package racingcar;

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
        Cars cars = Cars.from(Arrays.asList("pobi", "woni"));
        RacingGame game = new RacingGame(cars);

        MovingStrategy strategy = new MovingStrategy() {
            private int count = 0;

            @Override
            public boolean shouldMove() {
                return count++ == 0;
            }
        };

        // when
        RacingResult result = game.run(strategy, 2);

        // then: 라운드 정보 확인
        List<List<Integer>> rounds = result.getRounds();

        assertThat(rounds).hasSize(2);
        assertThat(rounds.get(0)).containsExactly(1, 0);
        assertThat(rounds.get(1)).containsExactly(1, 1);


    }

}
