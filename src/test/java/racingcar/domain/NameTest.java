package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import racingcar.domain.car.Name;

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

    @Test
    void 같은_이름이면_동등하다() {
        Name name1 = Name.from("pobi");
        Name name2 = Name.from("pobi");

        assertThat(name1).isEqualTo(name2);
    }

}
