package racingcar.domain.game;

import java.util.List;

public class RacingResult {

    private final List<String> winners;
    private final List<List<Integer>> rounds;

    private RacingResult(List<String> winners, List<List<Integer>> rounds) {
        this.winners = winners;
        this.rounds = rounds;
    }

    public static RacingResult of(List<String> winners, List<List<Integer>> rounds) {
        return new RacingResult(winners, rounds);
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<List<Integer>> getRounds() {
        return rounds;
    }

}
