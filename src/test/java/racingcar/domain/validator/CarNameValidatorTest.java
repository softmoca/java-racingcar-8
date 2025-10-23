package racingcar.domain.validator;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("CarNameValidator 클래스 테스트")
class CarNameValidatorTest {

    private CarNameValidator validator=new CarNameValidator();;

    @Test
    @DisplayName("유효한 자동차 이름들은 검증을 통과한다")
    void validateValidNames() {
        // given
        List<String> validNames = Arrays.asList("pobi", "woni", "jun");

        // when & then
        assertThatCode(() -> validator.validate(validNames))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("5자 이하의 이름은 유효하다")
    void validateNamesWithMaxLength() {
        // given
        List<String> validNames = Arrays.asList("a", "aa", "aaa", "aaaaa", "aaaa");

        // when & then
        assertThatCode(() -> validator.validate(validNames))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("빈 문자열(연속된 구분자)은 예외를 발생시킨다")
    void validateEmptyName() {
        // given
        List<String> invalidNames = Arrays.asList("pobi", "", "jun");

        // when & then
        assertThatThrownBy(() -> validator.validate(invalidNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 비어있을 수 없습니다");
    }

    @Test
    @DisplayName("공백만 있는 문자열은 예외를 발생시킨다")
    void validateBlankName() {
        // given
        List<String> invalidNames = Arrays.asList("pobi", "   ", "jun");

        // when & then
        assertThatThrownBy(() -> validator.validate(invalidNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 비어있을 수 없습니다");
    }

    @ParameterizedTest
    @ValueSource(strings = {"123456", "abcdef", "pobi123"})
    @DisplayName("5자를 초과하는 이름은 예외를 발생시킨다")
    void validateNameExceedingMaxLength(String longName) {
        // given
        List<String> invalidNames = Arrays.asList("pobi", longName);

        // when & then
        assertThatThrownBy(() -> validator.validate(invalidNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 5자 이하여야 합니다")
                .hasMessageContaining(longName);
    }

    @Test
    @DisplayName("중복된 이름은 예외를 발생시킨다")
    void validateDuplicateNames() {
        // given
        List<String> invalidNames = Arrays.asList("pobi", "woni", "pobi");

        // when & then
        assertThatThrownBy(() -> validator.validate(invalidNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 중복될 수 없습니다")
                .hasMessageContaining("pobi");
    }

    @Test
    @DisplayName("대소문자를 구분하여 검증한다")
    void validateCaseSensitiveNames() {
        // given
        List<String> validNames = Arrays.asList("pobi", "Pobi", "POBI");

        // when & then
        assertThatCode(() -> validator.validate(validNames))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("단일 자동차 이름도 검증할 수 있다")
    void validateSingleName() {
        // given
        List<String> singleName = Arrays.asList("pobi");

        // when & then
        assertThatCode(() -> validator.validate(singleName))
                .doesNotThrowAnyException();
    }
}