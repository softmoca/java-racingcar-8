package racingcar;

import java.util.List;

public class RacingResult {

    private final List<String> winners;

    private RacingResult(List<String> winners) {
        this.winners = winners;
    }

    public static RacingResult of(List<String> winners) {
        return new RacingResult(winners);
    }

    public List<String> getWinners() {
        return winners;
    }
}
