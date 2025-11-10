package racingcar;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PositionFormatterTest {

    @Test
    void 위치_0은_빈_문자열이다() {
        String result = PositionFormatter.format(0);
        assertThat(result).isEmpty();
    }

 
}
