package racingcar.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import racingcar.domain.strategy.MovingStrategy;
import racingcar.view.input.InputReader;

class RacingControllerTest {

    @Test
    void 게임을_정상적으로_실행한다() {
        // given
        InputReader inputReader = new FixedInputReader(
                Arrays.asList("pobi", "woni", "jun"),
                3
        );
        MockOutputWriter outputWriter = new MockOutputWriter();

        MovingStrategy strategy = () -> true;
        GameInitializer gameInitializer = new GameInitializer(strategy);

        RacingController controller = new RacingController(
                inputReader,
                outputWriter,
                gameInitializer
        );

        // when
        controller.run();

        // then
        assertThat(outputWriter.isHeaderPrinted()).isTrue();
        assertThat(outputWriter.getRoundCount()).isEqualTo(3);
        assertThat(outputWriter.isWinnersPrinted()).isTrue();
    }

    @Test
    void 잘못된_입력시_에러를_출력한다() {
        // given
        InputReader inputReader = new InputReader() {
            @Override
            public List<String> readCarNames() {
                throw new IllegalArgumentException("잘못된 입력");
            }

            @Override
            public int readRounds() {
                return 0;
            }
        };

        MockOutputWriter outputWriter = new MockOutputWriter();
        GameInitializer gameInitializer = new GameInitializer(() -> true);

        RacingController controller = new RacingController(
                inputReader,
                outputWriter,
                gameInitializer
        );

        // when
        controller.run();

        // when & then
        assertThatThrownBy(() -> controller.run())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 입력");

        // 예외 발생 전에 에러 메시지가 출력되었는지 확인
        assertThat(outputWriter.getErrorMessage()).isEqualTo("잘못된 입력");
    }

}
