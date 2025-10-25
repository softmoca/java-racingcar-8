package racingcar.domain.strategy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("MoveStrategy 구현체 테스트")
class MoveStrategyTest {

    @Test
    @DisplayName("FixedMoveStrategy - 항상 전진하는 전략")
    void alwaysMoveStrategy() {
        // given
        MoveStrategy strategy = FixedMoveStrategy.alwaysMove();

        // when & then
        for (int i = 0; i < 10; i++) {
            assertThat(strategy.shouldMove()).isTrue();
        }
    }

    @Test
    @DisplayName("FixedMoveStrategy - 절대 전진하지 않는 전략")
    void neverMoveStrategy() {
        // given
        MoveStrategy strategy = FixedMoveStrategy.neverMove();

        // when & then
        for (int i = 0; i < 10; i++) {
            assertThat(strategy.shouldMove()).isFalse();
        }
    }

    @Test
    @DisplayName("FixedMoveStrategy - true로 생성하면 항상 전진")
    void fixedMoveStrategyWithTrue() {
        // given
        MoveStrategy strategy = new FixedMoveStrategy(true);

        // when & then
        assertThat(strategy.shouldMove()).isTrue();
        assertThat(strategy.shouldMove()).isTrue();
        assertThat(strategy.shouldMove()).isTrue();
    }

    @Test
    @DisplayName("FixedMoveStrategy - false로 생성하면 절대 전진하지 않음")
    void fixedMoveStrategyWithFalse() {
        // given
        MoveStrategy strategy = new FixedMoveStrategy(false);

        // when & then
        assertThat(strategy.shouldMove()).isFalse();
        assertThat(strategy.shouldMove()).isFalse();
        assertThat(strategy.shouldMove()).isFalse();
    }

    @Test
    @DisplayName("RandomMoveStrategy - true 또는 false를 반환한다") // 이런 식의 테스트가 의미가 있을지 TODO
    void randomMoveStrategyReturnsBooleanValue() {
        // given
        MoveStrategy strategy = new RandomMoveStrategy();

        // when
        boolean result = strategy.shouldMove();

        // then - true 또는 false 중 하나
        assertThat(result).isIn(true, false);
    }

}