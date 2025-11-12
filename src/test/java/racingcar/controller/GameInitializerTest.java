package racingcar;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import racingcar.controller.GameInitializer;
import racingcar.domain.strategy.MovingStrategy;
import racingcar.domain.strategy.RacingGame;
import racingcar.domain.strategy.RacingResult;

class GameInitializerTest {

    @Test
    void 자동차_이름들로_게임을_초기화한다() {
        // given
        List<String> carNames = Arrays.asList("pobi", "woni", "jun");
        MovingStrategy strategy = () -> true;

        GameInitializer initializer = new GameInitializer(strategy);

        // when
        RacingGame game = initializer.createGame(carNames);

        // then
        assertThat(game).isNotNull();
    }

    @Test
    void 생성된_게임을_실행할_수_있다() {
        // given
        List<String> carNames = Arrays.asList("pobi", "woni");
        MovingStrategy alwaysMove = () -> true;

        GameInitializer initializer = new GameInitializer(alwaysMove);
        RacingGame game = initializer.createGame(carNames);

        // when
        RacingResult result = game.run(alwaysMove, 3);

        // then
        assertThat(result.getWinners()).containsExactly("pobi", "woni");
    }
}
