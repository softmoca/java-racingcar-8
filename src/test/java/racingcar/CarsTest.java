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
}
