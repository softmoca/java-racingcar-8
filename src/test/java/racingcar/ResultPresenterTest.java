package racingcar;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import racingcar.domain.RacingResult;
import racingcar.view.OutputWriter;

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

    @Test
    void 각_라운드마다_자동차_위치를_출력한다() {
        // given
        List<String> carNames = Arrays.asList("pobi", "woni", "jun");
        RecordingOutputWriter outputWriter = new RecordingOutputWriter();

        ResultPresenter presenter = new ResultPresenter(
                carNames,
                outputWriter
        );

        List<List<Integer>> rounds = Arrays.asList(
                Arrays.asList(1, 0, 1),
                Arrays.asList(2, 1, 1),
                Arrays.asList(3, 1, 2)
        );
        RacingResult result = RacingResult.of(
                Arrays.asList("pobi"),
                rounds
        );

        // when
        presenter.present(result);

        // then
        List<RoundOutput> recorded = outputWriter.getRecordedRounds();

        assertThat(recorded).hasSize(3);
        assertThat(recorded.get(0).getPositions()).containsExactly(1, 0, 1);
        assertThat(recorded.get(1).getPositions()).containsExactly(2, 1, 1);
        assertThat(recorded.get(2).getPositions()).containsExactly(3, 1, 2);
    }

    static class RecordingOutputWriter implements OutputWriter {
        private final List<RoundOutput> recordedRounds = new ArrayList<>();

        @Override
        public void printHeader() {
        }

        @Override
        public void printRound(List<String> names, List<Integer> positions) {
            recordedRounds.add(new RoundOutput(positions));
        }

        @Override
        public void printWinners(List<String> winners) {
        }

        @Override
        public void printError(String message) {
        }

        public List<RoundOutput> getRecordedRounds() {
            return recordedRounds;
        }
    }

    static class RoundOutput {
        private final List<Integer> positions;

        RoundOutput(List<Integer> positions) {
            this.positions = positions;
        }


        public List<Integer> getPositions() {
            return positions;
        }
    }


}
