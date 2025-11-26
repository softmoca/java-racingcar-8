# TDD로 다시 보는 자동차 경주

## 🎯 TDD가 제공한 실질적 이점

---

## 1. 요구사항 탐색 & 설계 피드백 도구로서의 TDD

### 무작위 전진 조건 테스트 불가능 문제 발견

#### 테스트 작성 시점

```java

@Test
void 전진하면_위치가_1_증가한다() {
    Car car = new Car("pobi");

    car.move();

    assertThat(car.getPosition()).isEqualTo(1);
}
```

- 자동차가 전진하면 위치가 증가하는지 확인하고 싶음
- `move()`를 호출해야 할 것 같긴 한데…!

#### 문제 인식

- 무작위 값이 4 이상일 경우 전진
- Random이 들어가면 테스트 결과가 매번 달라져 **테스트 자동화 불가능**

#### 요구사항 재탐색

- 0~9 사이의 무작위 값을 구한다
- 4 이상이면 전진, 4 미만이면 멈춤
- **테스트 가능하게 설계를 바꿔야겠다!**
    - → 전진 여부를 외부에서 주입받으면?
    - → `move(boolean shouldMove)`
    - → 테스트에서는 true/false를 직접 전달!

---

### 설계 개선 - 의존성 주입

**개선 전: 테스트 불가능**

```java
public void move() {
    if (Randoms.pickNumberInRange(0, 9) >= 4) {
        position++;
    }
}
```

**개선 후: 테스트 가능**

```java
public void move(boolean shouldMove) {
    if (shouldMove) {
        position++;
    }
}
```

#### 테스트 가능한 설계로 변경

```java

@Test
void 전진_조건이_true면_위치가_1_증가한다() {
    Car car = new Car("pobi");

    car.move(true);

    assertThat(car.getPosition()).isEqualTo(1);
}

@Test
void 전진_조건이_false면_위치가_변하지_않는다() {
    Car car = new Car("pobi");

    car.move(false);

    assertThat(car.getPosition()).isEqualTo(0);
}
```

---

### MovingStrategy 인터페이스 발견

- Car는 전진 여부만 받으면 되는데, 누가 판단하지?
- **책임 분리 필요성 인식**

| 책임               | 담당           |
|------------------|--------------|
| 전진하라는 명령을 받으면 전진 | Car          |
| 전진 조건 판단         | Car의 책임이 아님! |

> **전진 조건을 판단하는 객체가 필요하다!**

```java

@FunctionalInterface
public interface MovingStrategy {
    boolean shouldMove();
}
```

```java

@Test
void MovingStrategy로_자동차를_움직일_수_있다() {
    Car car = new Car("pobi");

    // 항상 전진하는 전략
    MovingStrategy alwaysMove = () -> true;

    car.move(alwaysMove.shouldMove());

    assertThat(car.getPosition()).isEqualTo(1);
}
```

- 인터페이스 설계 후 **람다로 아주 간편하게 테스트에서 사용 가능!**

---

### 배운 점

- **테스트 작성 = 요구사항 명확화**
    - 무작위 전진이라는 요구사항을 테스트하려다가 전진 조건 주입이라는 설계로 자연스럽게 개선
- **테스트 불가능 = 설계 문제 신호**
    - Random을 직접 사용하면 테스트 불가능
    - 의존성 주입으로 해결 → 더 좋은 설계
- **테스트 실패 = 조기 버그 발견**
    - 상태를 가진 테스트 더블의 동작 추적
    - 실제 게임 로직이 아닌 테스트 설계 문제도 발견

---

## 2. 리팩토링 안전망으로서의 TDD

### 의존성 주입 기반으로 입출력 및 컨트롤러 리팩토링

#### 리팩토링 전 코드

```java
public class RacingController {

    public static void run() {
        try {
            // 1. 입력
            List<String> carNames = InputView.readCarNames();
            int rounds = InputView.readRounds();

            // 2. 게임 준비
            Cars cars = Cars.from(carNames);
            MovingStrategy strategy = new RandomMovingStrategy();
            RacingGame game = new RacingGame(cars);

            // 3. 게임 실행 & 출력
            OutputView.printHeader();
            RacingResult result = game.run(strategy, rounds);
            printGameProgress(carNames, result.getRounds());
            OutputView.printWinners(result.getWinners());

        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }
    }
}
```

### 현재 코드 개선 사항 인식

- Static 메서드
    - 상태가 없어 다형성 불가능
    - 컨트롤러 입출력 테스트 어려움
    - OCP 원칙 위반
- 절차적 코드
    - 순차적 실행 및 "어떻게"에 집중
    - 객체에게 "시키는" 게 아니라 "직접 함"
- 책임 분산 부족
    - Controller가 너무 많이 알고 있음
    - InputView, OutputView, Cars, RacingGame 모두 알아야 함
- Controller가 모든 게임 준비 로직에 대해 너무 많이 알고 있다
    - Cars를 어떻게 만드는지
    - MovingStrategy를 어떻게 만드는지
    - RacingGame을 어떻게 만드는지
- `RandomMovingStrategy()` 하드코딩 → **OCP 위반**
    - 전략 바꾸려면 Controller 코드 수정 필요
    - 테스트에서 제어 불가능

> **살아있는 테스트가 있으니 TDD로 과감하게 리팩토링하자!!**

---

### 인터페이스 추출

#### InputReader 인터페이스

```java
public interface InputReader {
    List<String> readCarNames();

    int readRounds();
}
```

**ConsoleInputReader: 콘솔 입력**

```java
public class ConsoleInputReader implements InputReader {

    ...
}
```

**FixedInputReader: 테스트용 고정값**

```java
public class FixedInputReader implements InputReader {

    ...
}
```

#### OutputWriter 인터페이스

```java
public interface OutputWriter {
    void printHeader();

    void printRound(List<String> names, List<Integer> positions);

    void printWinners(List<String> winners);

    void printError(String message);
}
```

**ConsoleOutputWriter: 콘솔 출력**

```java
public class ConsoleOutputWriter implements OutputWriter {

    ...
}
```

**MockOutputWriter: 테스트용**

```java
class MockOutputWriter implements OutputWriter {
    ...
}
```

---

### 컨트롤러 테스트

```java
class RacingControllerTest {

    @Test
    void 게임을_정상적으로_실행한다() {
        // given
        InputReader inputReader = new FixedInputReader(
                Arrays.asList("pobi", "woni", "jun"),
                3
        );
        MockOutputWriter outputWriter = new MockOutputWriter();

        MovingStrategy strategy = () -> true;
        GameInitializer gameInitializer = new GameInitializer(strategy);

        RacingController controller = new RacingController(
                inputReader,
                outputWriter,
                gameInitializer
        );

        // when
        controller.run();

        // then
        assertThat(outputWriter.isHeaderPrinted()).isTrue();
        assertThat(outputWriter.getRoundCount()).isEqualTo(3);
        assertThat(outputWriter.isWinnersPrinted()).isTrue();
    }

    @Test
    void 잘못된_입력시_에러를_출력한다() {
        // given
        InputReader inputReader = new InputReader() {
            @Override
            public List<String> readCarNames() {
                throw new IllegalArgumentException("잘못된 입력");
            }

            @Override
            public Rounds readRounds() {
                return Rounds.from(0);
            }
        };

        MockOutputWriter outputWriter = new MockOutputWriter();
        GameInitializer gameInitializer = new GameInitializer(() -> true);

        RacingController controller = new RacingController(
                inputReader,
                outputWriter,
                gameInitializer
        );

        // when & then
        assertThatThrownBy(() -> controller.run())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 입력");

        assertThat(outputWriter.getErrorMessage()).isEqualTo("잘못된 입력");
    }
}
```

#### 개선된 점

- Cars생성 직접 → GameInitializer에 위임
- Gtrategy 직접 하드 코딩 생성 → 주입 받음    
  → 컨트롤러 테스트 가능 !!

---

### TDD의 살아있는 테스트가 안전망이 된 증거

- 대규모 구조 변경
    - static → 인스턴스
    - 클래스 3개 추가
    - 의존성 구조 완전 변경
- 기존 기능 보장
    - 도메인 테스트 모두 통과
    - 동작 변경 없음 확인
- 새 기능 검증
    - 새 클래스들 테스트 추가 후 즉각적 피드백

> **⇒ 과감한 리팩토링이 가능!!**

---

### 배운 점

- **TDD 사이클에 리팩토링이 자연스럽게 포함**
    - Red → Green → **Refactor**
    - 점진적으로 구조 개선 가능
    - 대규모 변경도 안전하게
- **테스트가 리팩토링의 안전망**
    - 기존 테스트 유지 = 동작 보장
    - 대담한 구조 변경 가능
    - 이렇게 바꿔도 될까? → 테스트 통과! 안전하다 !!
- **객체지향 원칙 적용**
    - **DIP**: 인터페이스에 의존 (InputReader, OutputWriter)
    - **SRP**: 각 클래스가 하나의 책임 (GameInitializer, ResultPresenter)
    - **OCP**: 전략 교체 가능 (MovingStrategy 주입)
    - **Tell, Don't Ask**: Controller는 위임만
- **심리적 안정 제공**
    - 테스트 없이는 "수정하면 깨질까봐" 두려움
    - 테스트 있으면 "일단 바꿔보고 확인하자" 자신감
    - **구조 변경의 족쇄가 아닌 안전망!**
- 트레이드 오프 인식
    - 장점
        - 테스트 용이, 확장 쉬움, 변경 영양 최소화, 책임 명확
    - 단점
        - 클래스 개수 증가, 의존성 조립 복잡, 코드 추적 어려움, 간단한 기능에 과한 설계
        - 테스트 코드 작성시 많고 복잡한 테스트 더블 필요

---

## 🤔 어떤 고민이 있었고, 왜 그런 결정을 했는지

---

## 1. 컨트롤러 테스트의 비효율

### 컨트롤러와 입출력 테스트를 위한 리팩토링

- 컨트롤러 테스트를 하기 위해 여러 클래스들과 인터페이스를 추가하며 리팩토링
- 많은 시간이 걸리며 컨트롤러 테스트를 작성
- 성과
    - 의존성 주입 가능
    - 테스트 가능한 구조
    - 컨트롤러가 단순해짐
    - 각 객체가 명확한 책임을 가짐
- 비용
    - 인터페이스 2개 추가
    - 구현체 4개 추가 (Console 2개, Test 2개)
    - 2개의 초기화 관련 클래스 추가
    - 모든 호출 코드 수정
    - 의존성 증가
    - 테스트해야하는 범위 자체가 증가

---

### 테스트 작성의 어려움

#### Mock 객체 관리의 복잡성

**Mock 생성 비용**

- MockOutputWriter 클래스 전체 구현 필요
- 모든 메서드 구현
- 상태 추적 코드 작성
- getter 메서드들 추가

**Mock 동작 정의: 각 테스트마다 상당한 시간 소요**

- 어떤 값을 반환해야 하지?
- 어떤 순서로 호출되지?
- 몇 번 호출되어야 하지?

> Mock이 점점 복잡해지며 **기존 테스트 코드보다 Mock 관리 코드가 더 많아짐**
> 어떤 Mock을 어떻게 써야 하는지 같은 관리 포인트에 고민하는데 많은 시간을 써야 함

---

### 컨트롤러 테스트의 가치 고민

#### 컨트롤러 테스트로 검증하는 것들

- 특정 input, output 메서드들이 잘 호출되었는지
- 순서에 맞게 호출되었는지
- 예외 발생 시 예외 메서드 잘 호출하는지

#### 문제점

- 기존 도메인 테스트에 걸린 시간보다 **몇 배는 많은 시간**이 필요
- 리소스와 집중도가 많이 필요
- 도메인 테스트의 중요도와 시간에 비해 **제거하는 게 좋다고 생각될 만큼 필요성이 없다고 판단**

---

### Mock 사용의 문제점

| 문제                  | 상세                                                            |
|---------------------|---------------------------------------------------------------|
| **구현에 강하게 결합**      | 메서드 이름 변경 시 Mock 수정, 테스트 수정 필요 → 리팩토링이 어려워짐, 테스트가 발목을 잡음      |
| **Mock 동작 정의의 어려움** | 뭘 반환해야 하지? 몇 번 호출되어야 하지? 어떤 순서로? → 사소한 잘못된 가정으로 테스트 작성 시간 증가  |
| **테스트의 가독성 저하**     | Mock 세팅 코드가 길어짐, 실제 검증 코드보다 준비 코드가 더 많음, "이게 뭘 테스트하는 거지?" 불명확 |

---

### 고전파 스타일과 런던파 스타일 분석

#### 기존 컨트롤러 입출력 테스트에서의 런던파 스타일

- 협력 객체 모두 Mock을 쓰며 행위 검증
- Mock 관리가 복잡하고 구현에 강하게 결합

#### 고전파 스타일 시도

- 출력 검증이 불가능, 여전히 `Console.readLine()` 문제 존재
- 고전파도 입출력 테스트는 테스트 더블이 필요하다!

> **⇒ Controller 테스트는 어느 쪽이든 공수가 많이 들고 까다롭다!**

---

### 최종 결정: Controller 테스트 생략

- Mock 관리 비용이 너무 커서 전체적으로 비효율
- **대안**
    - 통합 테스트로 일부 대체 가능
    - 수동 테스트

---

### 배운 점

#### 핵심 교훈

> **모든 것을 테스트할 필요는 없고, 테스트가 많다고 무조건 좋은 건 절대 아니다!**

#### 테스트의 가치 우선순위
- 비즈니스 로직 검증 > 흐름 검증
- 복잡한 로직 > 단순 조합
- 안정적 테스트 > 취약한 테스트

#### 전략

- 도메인에 집중
- Controller는 단순하게 유지
- 통합 테스트로 보완
- 실용적으로 판단

#### 가치 있는 테스트에 집중해야 한다

- ROI (투자 대비 효과)
- 유지보수 비용
- 리팩토링 내성
- 실제 버그 방지 효과

#### 좋은 테스트 vs 안 좋은 테스트

| 안 좋은 테스트             | 좋은 테스트            |
|----------------------|-------------------|
| Mock 세팅이 테스트 코드보다 길다 | 행위(결과)를 검증한다      |
| 리팩토링하면 테스트가 깨진다      | 리팩토링해도 테스트가 통과한다  |
| 뭘 테스트하는 건지 불명확하다     | 테스트 의도가 명확하다      |
| 구현 세부사항을 검증한다        | 실제 버그를 효율적으로 잡아준다 |

---

## 2. OOP와 TDD의 모순과 충돌

### 문제 인식 & 분석

추가 개선과 리팩토링을 진행하는 과정 중 **속도가 더뎌지고, 개발의 방향성을 잃고 있음**을 느꼈다.

#### TDD 원칙

- 필요한 것만 만든다
- 과도한 설계를 하지 않는다
- 현재 요구사항에 집중한다

#### OOP 학습 목표

- 유지보수성이 좋아지도록 객체지향 설계 적용
- 여러 확장 가능한 책임을 가진 구조 추가
- Value Object 활용 극대화
- 디미터 법칙 준수를 위해 노력

---

### 모순 발견!

| TDD         | OOP              |
|-------------|------------------|
| "필요한 것만"    | "미래를 위해 미리미리 개선" |
| 현재 요구사항에 집중 | 확장 가능한 구조 설계     |
| 과도한 설계 금지   | 유지보수성을 위한 추상화    |

---

### 배운 점

#### 핵심 깨달음

> **TDD와 OOP 학습은 다른 목표를 가지고 있어 충돌한다!**

- 과하게 해보며 **적절함을 알게 되는 경험**을 얻었다
- 개발자의 경험과 프로젝트 상황, 목적에 따라 방법론의 적용 정도가 달라진다

#### 리팩토링은 목적이 아니라 수단!

- 해당 상황에 맞는 더 나은 설계가 목적
- 무조건 한다고 좋지 않다!

#### TDD에서는 실제 사용하면서 문제를 발견하자

- "이론상 문제 vs 실제 문제"를 인지
- 미래를 과도하게 예측하지 말자

#### TDD에서는 자기 합리화가 아닌 진정한 YAGNI!

> **You Aren't Gonna Need It**

- 지금 필요 없으면 만들지 마라
- 나중에 필요해지면 그때 만들어라

#### 완벽한 설계는 없다

- 상황에 따라 다름
- 트레이드오프
- **적절한 균형점 찾기**
