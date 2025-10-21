# 📚 2주차 미션 - 자동차 경주 게임

## 🎯 유즈케이스 (Use Cases)

### UC-01: 자동차 이름 입력
**목적:** 경주에 참가할 자동차들의 이름을 입력받는다.  
**사전조건:** 게임이 시작된다.  
**사후조건:** 유효한 자동차 이름들이 등록되어 자동차 객체들이 생성된다.

**주 시나리오:**
1. 시스템이 자동차 이름 입력을 요청한다.
2. 사용자가 쉼표(`,`)로 구분된 자동차 이름을 입력한다.
3. 시스템이 입력값을 검증한다.
4. 시스템이 각 자동차 객체를 생성한다.

**예외 시나리오:**
- a. 자동차 이름이 5자를 초과하는 경우
- b. 자동차 이름이 비어있는 경우
- c. 입력 형식이 잘못된 경우
    - 쉽표가 아닌 구분자
    - 연속된 쉼표
  

**입력 예시:**
```
pobi,woni,jun
```

---

### UC-02: 시도 횟수 입력
**목적:** 경주를 진행할 횟수를 입력받는다.  
**사전조건:** 자동차 이름이 유효하게 입력되었다.  
**사후조건:** 경주 시도 횟수가 설정된다.

**주 시나리오:**
1. 시스템이 시도 횟수 입력을 요청한다.
2. 사용자가 시도 횟수를 입력한다.
3. 시스템이 입력값을 검증한다.
4. 시스템이 경주 게임 설정을 완료한다.

**예외 시나리오:**
- a. 숫자가 아닌 값을 입력한 경우
- b. 0 이하의 값을 입력한 경우

**입력 예시:**
```
5
```

---

### UC-03: 자동차 경주 진행
**목적:** 설정된 횟수만큼 자동차 경주를 진행한다.  
**사전조건:**
- 자동차들이 생성되었다.
- 시도 횟수가 설정되었다.  

**사후조건:**
- 모든 라운드가 완료된다.
- 각 자동차의 최종 위치가 확정된다.

**주 시나리오:**
1. 시스템이 "실행 결과"를 출력한다.
2. **[설정된 횟수만큼 반복]**
    - 2.1. 각 자동차에 대해 0~9 사이의 무작위 값을 생성한다.
    - 2.2. 무작위 값이 4 이상이면 해당 자동차를 전진시킨다.
    - 2.3. 각 자동차의 현재 위치를 출력한다.
        - 형식: `{자동차이름} : {-의 개수}`
    - 2.4. 빈 줄을 출력한다.
3. 경주가 종료된다.

**출력 예시:**
```
실행 결과
pobi : -
woni : 
jun : -

pobi : --
woni : -
jun : --
```

---

### UC-04: 우승자 결정
**목적:** 경주 완료 후 우승자를 결정하고 출력한다.  
**사전조건:** 자동차 경주가 완료되었다.  
**사후조건:** 우승자가 출력된다.

**주 시나리오:**
1. 시스템이 모든 자동차의 최종 위치를 조회한다.
2. 시스템이 최대 위치값을 찾는다.
3. 시스템이 최대 위치에 있는 모든 자동차를 찾는다.
4. **[우승자가 1명인 경우]**
    - "최종 우승자 : {이름}" 형식으로 출력한다.
5. **[우승자가 여러 명인 경우]**
    -  "최종 우승자 : {이름}, {이름}, ..." 형식으로 출력한다.

**출력 예시:**
- 단독 우승: `최종 우승자 : pobi`
- 공동 우승: `최종 우승자 : pobi, jun`

---

## 📋 구현할 기능 목록

### 1. 입력 기능
- [ ] 자동차 이름 입력 받기
    - `Console.readLine()`을 사용하여 입력 받음
    - 입력 프롬프트: `"경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)"`
- [ ] 시도 횟수 입력 받기
    - `Console.readLine()`을 사용하여 입력 받음
    - 입력 프롬프트: `"시도할 횟수는 몇 회인가요?"`

### 2. 입력 검증 기능
- [ ] 자동차 이름 검증
    - [ ] 이름이 비어있지 않은지 확인
    - [ ] 각 이름이 5자 이하인지 확인
    - [ ] 중복된 이름이 없는지 확인
    - [ ] 검증 실패 시 `IllegalArgumentException` 발생
- [ ] 시도 횟수 검증
    - [ ] 숫자 형식인지 확인
    - [ ] 양의 정수인지 확인 (1 이상)
    - [ ] 검증 실패 시 `IllegalArgumentException` 발생

### 3. 자동차 생성 기능
- [ ] 이름을 받아 자동차 객체 생성
- [ ] 자동차 초기 위치는 0으로 설정
- [ ] 여러 자동차를 리스트로 관리

### 4. 이동 기능
- [ ] 무작위 값 생성 (0~9)
    - `Randoms.pickNumberInRange(0, 9)` 사용
- [ ] 무작위 값이 4 이상인지 판단
- [ ] 조건을 만족하면 자동차 전진
- [ ] 조건을 만족하지 않으면 정지

### 5. 경주 진행 기능
- [ ] 설정된 횟수만큼 라운드 반복
- [ ] 각 라운드마다 모든 자동차의 이동 시도
- [ ] 각 라운드 종료 후 결과 출력

### 6. 출력 기능
- [ ] 실행 결과 헤더 출력
    - `"실행 결과"` 출력
- [ ] 각 라운드 결과 출력
    - 형식: `{자동차이름} : {-의 개수}`
    - 위치 0이면 이름 뒤에 하이픈 없음
    - 각 라운드 후 빈 줄 출력
- [ ] 최종 우승자 출력
    - 단독 우승: `"최종 우승자 : {이름}"`
    - 공동 우승: `"최종 우승자 : {이름}, {이름}, ..."`

### 7. 우승자 결정 기능
- [ ] 모든 자동차의 위치 조회
- [ ] 최대 위치 계산
- [ ] 최대 위치를 가진 자동차들 필터링
- [ ] 우승자 리스트 반환

### 8. 예외 처리
- [ ] 모든 예외는 `IllegalArgumentException` 발생
- [ ] 예외 발생 시 애플리케이션 종료
- [ ] 명확한 예외 메시지 

---

## 🧾 자동차 경주 게임 — RDD(책임-주도 설계) 관점 정리

### 1️⃣ 협력(대화) 시나리오 — 메시지 흐름

#### 📌 게임 시작 및 초기화 시나리오

| **송신자**           | **수신자**                | **메시지**                              | **설명**                                    |
|-------------------|------------------------|--------------------------------------|-------------------------------------------|
| 사용자               | **InputView**          | 입력 제공                                | 자동차 이름을 쉼표로 구분하여 입력                      |
| **Controller**    | **InputView**          | `readCarNames()`                     | 자동차 이름 입력 요청                             |
| **Controller**    | **CarNameValidator**   | `validate(List<String> names)`       | 입력된 이름들의 유효성 검증 요청                       |
| **Controller**    | **CarFactory**         | `createCars(List<String> names)`     | 검증된 이름으로 자동차 객체들 생성 요청                   |
| **CarFactory**    | **Car**                | `new Car(String name)`               | 각 이름마다 자동차 객체 생성 *(여러 번 반복)*            |
| **Car**           | **Position**           | `Position.initial()`                 | 초기 위치(0) 생성                              |
| **Controller**    | **InputView**          | `readAttemptCount()`                 | 시도 횟수 입력 요청                              |
| **Controller**    | **AttemptValidator**   | `validate(String input)`             | 시도 횟수 유효성 검증 요청                          |
| **Controller**    | **RacingGame**         | `new RacingGame(cars, attemptCount)` | 게임 객체 생성 *(자동차 목록 + 시도 횟수)*            |

---

#### 📌 경주 진행 시나리오

| **송신자**         | **수신자**           | **메시지**                           | **설명**                              |
|-----------------|-------------------|----------------------------------|------------------------------------|
| **Controller**  | **ResultView**    | `printRoundHeader()`             | "실행 결과" 헤더 출력 요청                   |
| **Controller**  | **RacingGame**    | `startAndGetRoundResults()`      | 경주 시작 및 모든 라운드 결과 요청               |
| **RacingGame**  | **자신(self)**      | `playRound()`                    | 각 라운드 진행 *(횟수만큼 반복)*              |
| **RacingGame**  | **RandomGenerator**| `generate()`                    | 0~9 사이 난수 생성 요청                    |
| **RandomGenerator**| **RacingGame**  | **return** `int`                 | 생성된 난수 반환                          |
| **RacingGame**  | **자신(self)**      | 이동 여부 판단                         | 난수가 4 이상인지 확인                      |
| **RacingGame**  | **Car**           | `move(boolean shouldMove)`       | 각 자동차에게 이동 지시 *(모든 자동차 반복)*       |
| **Car**         | **Position**      | `moveForward()`                  | 전진 요청 *(이동 시)*                     |
| **Position**    | **Car**           | **return** `Position`            | 새로운 위치 반환 *(불변 객체)*                |
| **RacingGame**  | **Controller**    | **return** `List<List<Car>>`     | 모든 라운드 결과 반환                       |
| **Controller**  | **ResultView**    | `printRoundResult(List<Car>)`    | 각 라운드 결과 출력 요청 *(반복)*             |
| **ResultView**  | **Car**           | `getName()`                      | 자동차 이름 조회                          |
| **ResultView**  | **Car**           | `getPosition()`                  | 자동차 위치 조회                          |
| **ResultView**  | **Position**      | `toDisplayString()`              | 위치를 "-" 문자열로 변환 요청                 |

---

#### 📌 우승자 결정 시나리오

| **송신자**        | **수신자**        | **메시지**                     | **설명**                        |
|----------------|----------------|----------------------------|-----------------------------|
| **Controller** | **RacingGame** | `getWinners()`             | 우승자 찾기 요청                    |
| **RacingGame** | **WinnerFinder**| `findWinners(List<Car>)`  | 우승자 결정 로직 위임                 |
| **WinnerFinder**| **Car**       | `getPosition()`            | 각 자동차의 위치 조회 *(모든 자동차 반복)*   |
| **WinnerFinder**| **Position**  | `getValue()`               | 위치 값 조회                      |
| **WinnerFinder**| **자신(self)**  | 최대 위치 계산                    | 가장 큰 위치값 찾기                  |
| **WinnerFinder**| **자신(self)**  | 최대 위치 자동차 필터링               | 최대 위치를 가진 자동차들만 선택           |
| **WinnerFinder**| **RacingGame**| **return** `List<Car>`    | 우승자 목록 반환                    |
| **RacingGame** | **Controller** | **return** `List<Car>`    | 우승자 목록 반환                    |
| **Controller** | **ResultView** | `printWinners(List<Car>)` | 우승자 출력 요청                    |
| **ResultView** | **Car**        | `getName()`                | 우승자 이름 조회 *(각 우승자마다)*        |

> 💡 **협력의 핵심**
> - 메시지가 인터페이스를 결정한다 *(메시지 기반 설계)*
> - 각 객체는 자율적으로 내부 구현을 선택한다 *(캡슐화)*
> - "어떻게(How)"가 아닌 "무엇을(What)" 요청한다 *(Tell, Don't Ask)*

---

### 2️⃣ 🧩 역할 · 책임 · 메시지 (Role–Responsibility–Message)

| **역할(Role)**           | **책임(Responsibility)**                                  | **공개 메시지(Interface)**                                                                  | **계층**       |
|------------------------|--------------------------------------------------------|--------------------------------------------------------------------------------------|--------------|
| **InputView**          | 사용자 입력 수집                                              | `List<String> readCarNames()`<br>`String readAttemptCount()`                        | View         |
| **ResultView**         | 경주 결과 및 우승자 출력                                         | `void printRoundHeader()`<br>`void printRoundResult(List<Car>)`<br>`void printWinners(List<Car>)` | View         |
| **Controller**         | 게임 전체 흐름 제어 *(입력 → 검증 → 게임 → 출력)*                     | `void run()`                                                                         | Controller   |
| **RacingGame**         | 경주 게임 진행 총괄 *(라운드 관리 + 이동 판단)*                        | `List<List<Car>> startAndGetRoundResults()`<br>`List<Car> getWinners()`            | Domain       |
| **Car**                | 개별 자동차 표현 *(위치 관리)*                                    | `void move(boolean)`<br>`String getName()`<br>`Position getPosition()`              | Domain       |
| **Position**           | 자동차의 위치 표현 *(값 객체, 불변)*                               | `Position moveForward()`<br>`int getValue()`<br>`String toDisplayString()`<br>`boolean isGreaterThan(Position)` | Domain       |
| **WinnerFinder**       | 우승자 결정 로직 *(최대 위치 계산)*                                | `List<Car> findWinners(List<Car> cars)`                                             | Domain       |
| **CarFactory**         | 자동차 객체 생성 책임                                           | `List<Car> createCars(List<String> names)`                                          | Domain       |
| **CarNameValidator**   | 자동차 이름 유효성 검증 *(5자 이하, 비어있지 않음, 중복 없음)*             | `void validate(List<String> names)`                                                  | Domain       |
| **AttemptValidator**   | 시도 횟수 유효성 검증 *(양의 정수)*                                | `int validate(String input)`                                                         | Domain       |

---



