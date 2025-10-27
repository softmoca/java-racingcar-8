package racingcar.domain.validator;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static racingcar.domain.validator.AttemptValidator.NOT_NUMBER_ERROR;
import static racingcar.domain.validator.AttemptValidator.NOT_POSITIVE_ERROR;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("AttemptValidator 클래스 테스트")
class AttemptValidatorTest {

    private AttemptValidator validator = new AttemptValidator();

    @ParameterizedTest
    @ValueSource(strings = {"1", "5", "10", "100"})
    @DisplayName("양의 정수는 검증을 통과한다")
    void validatePositiveInteger(String input) {
        // when
        int result = validator.validate(input);

        // then
        assertThat(result).isEqualTo(Integer.parseInt(input));
    }

    @Test
    @DisplayName("0은 예외를 발생시킨다")
    void validateZero() {
        // given
        String input = "0";

        // when & then
        assertThatThrownBy(() -> validator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NOT_POSITIVE_ERROR);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "-5", "-100"})
    @DisplayName("음수는 예외를 발생시킨다")
    void validateNegativeNumber(String input) {
        // when & then
        assertThatThrownBy(() -> validator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NOT_POSITIVE_ERROR);
    }

    @ParameterizedTest
    @ValueSource(strings = {"aaa", "test", "1a2b", "abc"})
    @DisplayName("숫자가 아닌 문자열은 예외를 발생시킨다")
    void validateNonNumericString(String input) {
        // when & then
        assertThatThrownBy(() -> validator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NOT_NUMBER_ERROR);
    }

    @Test
    @DisplayName("빈 문자열은 예외를 발생시킨다")
    void validateEmptyString() {
        // given
        String input = "";

        // when & then
        assertThatThrownBy(() -> validator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NOT_NUMBER_ERROR);
    }

    @Test
    @DisplayName("공백 문자열은 예외를 발생시킨다")
    void validateBlankString() {
        // given
        String input = "   ";

        // when & then
        assertThatThrownBy(() -> validator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NOT_NUMBER_ERROR);
    }

    @Test
    @DisplayName("앞뒤 공백이 있는 숫자는 예외를 발생시킨다")
    void validateNumberWithSpaces() {
        // given
        String input = " 5 ";

        // when & then
        assertThatThrownBy(() -> validator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NOT_NUMBER_ERROR);
    }

}