package racingcar;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;


    private static final String NO_DELIMITER_ERROR = "쉼표(,)로 구분된 입력이 필요합니다.";
    private static final String CONSECUTIVE_DELIMITER_ERROR = "연속된 쉼표는 허용되지 않습니다.";
    private static final String START_WITH_DELIMITER_ERROR = "쉼표로 시작할 수 없습니다.";
    private static final String END_WITH_DELIMITER_ERROR = "쉼표로 끝날 수 없습니다.";
    private static final String NOT_NUMBER_ERROR = "시도 횟수는 숫자여야 합니다.";
    private static final String NOT_POSITIVE_ERROR = "시도 횟수는 1 이상이어야 합니다.";


    @Test
    void 기능_테스트() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "1");
                    assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
                },
                MOVING_FORWARD, STOP
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("이름에 대한 예외 처리 - 쉼표가 아닌 경우")
    void shouldThrowExceptionWhenNameDelimiterIsNotComma() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("moca  pobi ee", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(NO_DELIMITER_ERROR)
        );
    }

    @Test
    @DisplayName("이름에 대한 예외 처리 - 쉼표가 아닌 경우 (다른 구분자 사용)")
    void shouldThrowExceptionWhenNameDelimiterIsOtherSymbol() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("moca#pobi#ee", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(NO_DELIMITER_ERROR)
        );
    }

    @Test
    @DisplayName("이름에 대한 예외 처리 - 구분자가 연속된 경우")
    void shouldThrowExceptionWhenDelimiterIsConsecutive() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("moca,,,coco", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(CONSECUTIVE_DELIMITER_ERROR)
        );
    }

    @Test
    @DisplayName("이름에 대한 예외 처리 - 쉼표로 시작하는 경우")
    void shouldThrowExceptionWhenNameStartsWithDelimiter() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(",mo,ja", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(START_WITH_DELIMITER_ERROR)
        );
    }

    @Test
    @DisplayName("이름에 대한 예외 처리 - 쉼표로 끝나는 경우")
    void shouldThrowExceptionWhenNameEndsWithDelimiter() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("cc,gege,", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(END_WITH_DELIMITER_ERROR)
        );
    }


    @Test
    @DisplayName("시도 횟수에 대한 예외 처리 - 빈 값인 경우")
    void shouldThrowExceptionWhenAttemptCountIsBlank() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("mo,ca", " "))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(NOT_NUMBER_ERROR)
        );
    }

    @Test
    @DisplayName("시도 횟수에 대한 예외 처리 - 숫자가 아닌 경우 (One)")
    void shouldThrowExceptionWhenAttemptCountIsNotNumber1() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("mo,ca", "One"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(NOT_NUMBER_ERROR)
        );
    }

    @Test
    @DisplayName("시도 횟수에 대한 예외 처리 - 숫자가 아닌 경우 (1a2)")
    void shouldThrowExceptionWhenAttemptCountIsNotNumber2() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("mo,caa", "1a2"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(NOT_NUMBER_ERROR)
        );
    }

    @Test
    @DisplayName("시도 횟수에 대한 예외 처리 - 음수인 경우")
    void shouldThrowExceptionWhenAttemptCountIsNegative() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("moca,coco", "-1"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(NOT_POSITIVE_ERROR)
        );
    }


    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
