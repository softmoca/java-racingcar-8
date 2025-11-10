package racingcar;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class NameTest {

    @Test
    void 이름으로_생성된다() {
        Name name = Name.from("pobi");

        assertThat(name.getValue()).isEqualTo("pobi");
    }

    @Test
    void 이름이_5자를_초과하면_예외가_발생한다() {
        assertThatThrownBy(() -> Name.from("pobi12"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("5자 이하");
    }

    @Test
    void 이름이_5자면_생성된다() {
        Name name = Name.from("abcde");

        assertThat(name.getValue()).isEqualTo("abcde");
    }

    @Test
    void 이름이_1자면_생성된다() {
        Name name = Name.from("a");

        assertThat(name.getValue()).isEqualTo("a");
    }

}
