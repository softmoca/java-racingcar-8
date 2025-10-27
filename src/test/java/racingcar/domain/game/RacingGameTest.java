package racingcar.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.car.Car;
import racingcar.domain.strategy.MoveStrategy;

@DisplayName("RacingGame 통합 테스트")
class RacingGameTest {
    private List<Car> cars;
    private MoveStrategy alwaysMove;
    private MoveStrategy neverMove;

    @BeforeEach
    void setUp() {
        cars = Arrays.asList(
                new Car("mo"),
                new Car("ca"),
                new Car("coco")
        );

        alwaysMove = () -> true;
        neverMove = () -> false;
    }

    @DisplayName("모든 자동차가 항상 전진하면 5라운드 후 모든 위치가 5가 된다")
    @Test
    void 모든_차가_항상_전진하면_5라운드_후_위치가_5() {
        // given - 항상 전진하는 전략으로 5라운드 경주
        RacingGame game = new RacingGame(cars, 5, alwaysMove);

        // when - 경주 진행
        game.start(roundCars -> {
        });

        // then - 모든 자동차의 위치가 5
        assertThat(cars)
                .extracting(car -> car.getPosition().getValue())
                .containsExactly(5, 5, 5);
    }

    @DisplayName("모든 자동차가 전혀 전진하지 않으면 5라운드 후에도 모든 위치가 0이다")
    @Test
    void 모든_차가_전혀_전진_안_하면_위치가_0() {
        // given - 전혀 전진하지 않는 전략으로 5라운드 경주
        RacingGame game = new RacingGame(cars, 5, neverMove);

        // when - 경주 진행
        game.start(roundCars -> {
        });

        // then - 모든 자동차의 위치가 0
        assertThat(cars)
                .extracting(car -> car.getPosition().getValue())
                .containsExactly(0, 0, 0);
    }

    @DisplayName("5라운드 경주 시 라운드 콜백이 정확히 5번 호출된다")
    @Test
    void 라운드_5번진행_시_콜백이_5번_호출() {
        // given - 라운드 결과를 저장할 리스트
        RacingGame game = new RacingGame(cars, 5, alwaysMove);
        List<List<Car>> roundResults = new ArrayList<>();

        // when - 각 라운드마다 결과 저장
        game.start(roundCars -> {
            roundResults.add(new ArrayList<>(roundCars));
        });

        // then - 5번 호출됨
        assertThat(roundResults).hasSize(5);
    }

    @DisplayName("항상 전진하는 경우 매 라운드마다 위치가 1씩 증가한다")
    @Test
    void 매_라운드마다_위치가_1씩_증가() {
        // given - 한 대의 자동차로 테스트
        List<Car> singleCar = Arrays.asList(new Car("moca"));
        RacingGame game = new RacingGame(singleCar, 5, alwaysMove);

        List<Integer> positions = new ArrayList<>();

        // when - 각 라운드마다 위치 기록
        game.start(roundCars -> {
            positions.add(roundCars.get(0).getPosition().getValue());
        });

        // then - 1, 2, 3, 4, 5로 증가
        assertThat(positions).containsExactly(1, 2, 3, 4, 5);
    }

    @DisplayName("가장 많이 전진한 자동차만 우승자가 된다")
    @Test
    void 가장_많이_전진한_차만_우승() {
        List<Car> twoCars = Arrays.asList(
                new Car("moca"),
                new Car("coco")
        );

        int[] callCount = {0};
        MoveStrategy alternating = () -> {
            return (callCount[0]++ % 2) == 0;  // 홀수번째만 true
        };

        RacingGame game = new RacingGame(twoCars, 5, alternating);

        // when - 경주 후 우승자 확인
        game.start(roundCars -> {
        });
        List<Car> winners = game.getWinners();

        // then - moca 우승 (5번 전진)
        assertThat(twoCars.get(0).getPosition().getValue()).isEqualTo(5);
        assertThat(twoCars.get(1).getPosition().getValue()).isEqualTo(0);

        assertThat(winners).hasSize(1);
        assertThat(winners.get(0).getName()).isEqualTo("moca");
    }
}