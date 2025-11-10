package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;
import racingcar.view.CarNameParser;

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


    @Test
    void 빈_문자열은_예외가_발생한다() {
        String input = "";

        assertThatThrownBy(() -> CarNameParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 빈_이름이_있으면_예외가_발생한다() {
        String input = "pobi,,jun";

        assertThatThrownBy(() -> CarNameParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class);
    }


}
