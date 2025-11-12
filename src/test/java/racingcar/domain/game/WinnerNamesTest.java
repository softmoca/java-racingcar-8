package racingcar.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class WinnerNamesTest {
    @Test
    void 우승자_목록을_반환한다() {
        // given
        List<String> names = Arrays.asList("pobi", "woni", "jun");
        WinnerNames winners = WinnerNames.of(names);

        // when
        List<String> result = winners.getNames();

        // then
        assertThat(result).containsExactly("pobi", "woni", "jun");
    }

    @Test
    void 반환된_목록은_순서를_유지한다() {
        // given
        List<String> names = Arrays.asList("jun", "pobi", "woni");
        WinnerNames winners = WinnerNames.of(names);

        // when
        List<String> result = winners.getNames();

        // then
        assertThat(result).containsExactly("jun", "pobi", "woni");
    }


    @Test
    void 같은_우승자면_동등하다() {
        // given
        WinnerNames winners1 = WinnerNames.of(Arrays.asList("pobi", "woni"));
        WinnerNames winners2 = WinnerNames.of(Arrays.asList("pobi", "woni"));

        // when & then
        assertThat(winners1).isEqualTo(winners2);
    }

    @Test
    void 순서가_달라도_같은_우승자면_동등하다() {
        // given
        WinnerNames winners1 = WinnerNames.of(Arrays.asList("pobi", "woni"));
        WinnerNames winners2 = WinnerNames.of(Arrays.asList("woni", "pobi"));

        // when & then
        assertThat(winners1).isEqualTo(winners2);
    }
}
