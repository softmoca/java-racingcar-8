package racingcar;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class CarsTest {

    @Test
    void 이름_목록으로_여러_대의_자동차를_생성한다() {
        // given
        List<String> names = Arrays.asList("pobi", "woni", "jun");

        // when
        Cars cars = Cars.from(names);

        // then
        assertThat(cars.size()).isEqualTo(3);
    }

    @Test
    void 생성된_자동차들의_이름을_확인할_수_있다() {
        // given
        List<String> names = Arrays.asList("pobi", "woni", "jun");

        // when
        Cars cars = Cars.from(names);

        // then
        List<String> carNames = cars.getNames();
        assertThat(carNames).containsExactly("pobi", "woni", "jun");
    }

    @Test
    void 모든_자동차가_동시에_이동한다() {
        // given: 3대의 자동차
        Cars cars = Cars.from(Arrays.asList("pobi", "woni", "jun"));

        // given: 항상 전진하는 전략
        MovingStrategy alwaysMove = () -> true;

        // when: 모두 이동
        cars.moveAll(alwaysMove);

        // then
        List<Integer> positions = cars.getPositions();
        assertThat(positions).containsExactly(1, 1, 1);
    }

    @Test
    void 각_자동차는_전진_조건에_따라_다르게_이동한다() {
        // given
        Cars cars = Cars.from(Arrays.asList("pobi", "woni", "jun"));

        // given
        MovingStrategy alternateStrategy = new MovingStrategy() {
            private int count = 0;

            @Override
            public boolean shouldMove() {
                return count++ % 2 == 0;
            }
        };

        // when
        cars.moveAll(alternateStrategy);

        // then
        assertThat(cars.getPositions()).containsExactly(1, 0, 1);
    }

    @Test
    void 가장_멀리_간_자동차가_우승자다() {
        // given: 3대의 자동차
        Cars cars = Cars.from(Arrays.asList("pobi", "woni", "jun"));

        // when: pobi만 2번, 나머지는 1번 전진
        cars.moveAll(() -> true);
        cars.moveAll(new MovingStrategy() {
            private int count = 0;

            @Override
            public boolean shouldMove() {
                return count++ == 0;
            }
        });

        // then
        List<String> winners = cars.getWinners();
        assertThat(winners).containsExactly("pobi");
    }

    @Test
    void 공동_우승자가_있을_수_있다() {
        // given
        Cars cars = Cars.from(Arrays.asList("pobi", "woni", "jun"));

        // when
        cars.moveAll(new MovingStrategy() {
            private int count = 0;

            @Override
            public boolean shouldMove() {
                return count++ % 2 == 0;  // 0, 2번째만 true
            }
        });

        // then
        List<String> winners = cars.getWinners();
        assertThat(winners).containsExactly("pobi", "jun");
    }


}
