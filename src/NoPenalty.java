import java.util.List;

public class NoPenalty implements PenaltyStrategy {
    @Override
    public void apply(List<Player> players, Player current) {
        // do nothing
    }
}