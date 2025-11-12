package racingcar.domain.game;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class WinnerNames {

    private final Set<String> names;

    private WinnerNames(Set<String> names) {
        this.names = names;
    }

    public static WinnerNames of(List<String> names) {
        return new WinnerNames(new LinkedHashSet<>(names));
    }


    public List<String> getNames() {
        return new ArrayList<>(names);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WinnerNames that = (WinnerNames) o;
        return Objects.equals(names, that.names);
    }

    @Override
    public int hashCode() {
        return Objects.hash(names);
    }
}
