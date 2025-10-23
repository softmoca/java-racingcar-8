package racingcar.domain.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Position 클래스 테스트")
class PositionTest {

    @Test
    @DisplayName("초기 위치는 0이다")
    void initialPositionIsZero() { // getter로 테스트를 해야하지 않을까?
        // given
        Position position = Position.initial();

        // when
        String displayString = position.toDisplayString();

        // then
        assertThat(displayString).isEmpty();
    }

    @Test
    @DisplayName("전진하면 위치가 1 증가한다")
    void moveForwardIncreasesPositionByOne() {
        // given
        Position position = Position.initial();

        // when
        Position movedPosition = position.moveForward();
        String displayString = movedPosition.toDisplayString();

        // then
        assertThat(displayString).isEqualTo("-");
    }

    @Test
    @DisplayName("여러 번 전진할 수 있다")
    void moveForwardMultipleTimes() {
        // given
        Position position = Position.initial();

        // when
        Position position1 = position.moveForward();
        Position position2 = position1.moveForward();
        Position position3 = position2.moveForward();

        // then
        assertThat(position3.toDisplayString()).isEqualTo("---");
    }

    @Test
    @DisplayName("위치 비교 - 더 큰 위치를 올바르게 판단한다")
    void isGreaterThan() {
        // given
        Position position1 = Position.initial().moveForward();
        Position position2 = Position.initial().moveForward().moveForward();
        Position position3 = Position.initial().moveForward();

        // when & then
        assertThat(position2.isGreaterThan(position1)).isTrue();
        assertThat(position1.isGreaterThan(position2)).isFalse();
        assertThat(position1.isGreaterThan(position3)).isFalse();
    }

    @Test
    @DisplayName("Position은 불변 객체이다")
    void positionIsImmutable() {
        // given
        Position original = Position.initial();

        // when
        Position moved = original.moveForward();

        // then
        assertThat(original.toDisplayString()).isEmpty();
        assertThat(moved.toDisplayString()).isEqualTo("-");
    }

    @Test
    @DisplayName("위치를 문자열로 표시한다")
    void toDisplayString() {// 이미 위에서 문자열로 표시하는걸 테스트했지만 기능문서로서의 역할로서는 어떨지
        // given
        Position position = Position.initial();

        // when & then
        assertThat(position.toDisplayString()).isEqualTo("");
        assertThat(position.moveForward().toDisplayString()).isEqualTo("-");
        assertThat(position.moveForward().moveForward().toDisplayString()).isEqualTo("--");
        assertThat(position.moveForward().moveForward().moveForward().toDisplayString()).isEqualTo("---");
    }
}