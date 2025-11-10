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


}
