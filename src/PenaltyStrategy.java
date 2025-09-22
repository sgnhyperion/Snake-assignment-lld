import java.util.List;

public interface PenaltyStrategy {
    void apply(List<Player> players, Player current);
}