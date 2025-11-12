package racingcar.domain.game;

import java.util.List;

public class RacingResult {

    private final WinnerNames winners;
    private final List<List<Integer>> rounds;

    private RacingResult(WinnerNames winners, List<List<Integer>> rounds) {
        this.winners = winners;
        this.rounds = rounds;
    }

    public static RacingResult of(List<String> winnerList, List<List<Integer>> rounds) {
        return new RacingResult(
                WinnerNames.of(winnerList),
                rounds
        );
    }

    public List<String> getWinners() {
        return winners.getNames();
    }

    public List<List<Integer>> getRounds() {
        return rounds;
    }

}
