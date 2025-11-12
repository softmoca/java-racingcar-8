package racingcar;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import racingcar.domain.RacingResult;

class ResultPresenterTest {

    @Test
    void 게임_결과를_출력한다() {
        // given
        List<String> carNames = Arrays.asList("pobi", "woni");
        MockOutputWriter outputWriter = new MockOutputWriter();

        ResultPresenter presenter = new ResultPresenter(
                carNames,
                outputWriter
        );

        List<List<Integer>> rounds = Arrays.asList(
                Arrays.asList(1, 0),
                Arrays.asList(2, 1)
        );
        List<String> winners = Arrays.asList("pobi");
        RacingResult result = RacingResult.of(winners, rounds);

        // when
        presenter.present(result);

        // then: 헤더, 라운드, 우승자 모두 출력됨
        assertThat(outputWriter.isHeaderPrinted()).isTrue();
        assertThat(outputWriter.getRoundCount()).isEqualTo(2);
        assertThat(outputWriter.isWinnersPrinted()).isTrue();
    }


}
