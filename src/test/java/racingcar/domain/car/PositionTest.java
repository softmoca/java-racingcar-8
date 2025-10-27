package racingcar.domain.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Position 클래스 테스트")
class PositionTest {

    @Test
    @DisplayName("초기 위치는 0이다")
    void initialPositionIsZero() {
        // given
        Position position = Position.initial();

        // when
        int value = position.getValue();

        // then
        assertThat(value).isEqualTo(0);
    }

    @Test
    @DisplayName("전진하면 위치가 1 증가한다")
    void moveForwardIncreasesPositionByOne() {
        // given
        Position position = Position.initial();

        // when
        Position movedPosition = position.moveForward();

        // then
        assertThat(movedPosition.getValue()).isEqualTo(1);
    }

    @Test
    @DisplayName("여러 번 전진할 수 있다")
    void moveForwardMultipleTimes() {
        // given
        Position position = Position.initial();

        // when
        Position result = position
                .moveForward()
                .moveForward()
                .moveForward();

        // then
        assertThat(result.getValue()).isEqualTo(3);
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
        assertThat(original.getValue()).isEqualTo(0);
        assertThat(moved.getValue()).isEqualTo(1);
    }

    @Test
    @DisplayName("같은 값을 가진 Position은 동등하다")
    void equalsSameValue() {
        // given
        Position position1 = Position.initial().moveForward();
        Position position2 = Position.initial().moveForward();

        // then
        assertThat(position1).isEqualTo(position2);
    }

    @Test
    @DisplayName("다른 값을 가진 Position은 동등하지 않다")
    void equalsDifferentValue() {
        // given
        Position position1 = Position.initial().moveForward();
        Position position2 = Position.initial().moveForward().moveForward();

        // then
        assertThat(position1).isNotEqualTo(position2);
    }
}