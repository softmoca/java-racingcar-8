package racingcar;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class CarNameParserTest {

    @Test
    void 쉼표로_구분된_이름을_파싱한다() {
        // given
        String input = "pobi,woni,jun";

        // when
        List<String> names = CarNameParser.parse(input);

        // then
        assertThat(names).containsExactly("pobi", "woni", "jun");
    }

    @Test
    void 이름_사이의_공백을_제거한다() {
        String input = "pobi, woni, jun";

        List<String> names = CarNameParser.parse(input);

        assertThat(names).containsExactly("pobi", "woni", "jun");
    }

    @Test
    void 이름_앞뒤의_공백을_제거한다() {
        String input = " pobi , woni , jun ";

        List<String> names = CarNameParser.parse(input);

        assertThat(names).containsExactly("pobi", "woni", "jun");
    }


}
