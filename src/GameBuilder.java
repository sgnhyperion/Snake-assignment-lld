import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameBuilder {
    private Board board;
    private List<Player> players = new ArrayList<>();
    private Dice dice = new StandardDice(6);
    private StartStrategy startStrategy = new DirectStart();
    private EndStrategy endStrategy = new ExactFinish();
    private PenaltyStrategy penaltyStrategy = new NoPenalty();
    private TurnStrategy turnStrategy = new StrictRoundRobin();

    public GameBuilder withBoard(Board board) {
        this.board = board;
        return this;
    }

    public GameBuilder withPlayers(List<Player> players) {
        this.players = new ArrayList<>(players);
        return this;
    }

    public GameBuilder addPlayer(Player p) {
        this.players.add(p);
        return this;
    }

    public GameBuilder withDice(Dice dice) {
        this.dice = dice;
        return this;
    }

    public GameBuilder withStartStrategy(StartStrategy s) {
        this.startStrategy = s;
        return this;
    }

    public GameBuilder withEndStrategy(EndStrategy s) {
        this.endStrategy = s;
        return this;
    }

    public GameBuilder withPenaltyStrategy(PenaltyStrategy s) {
        this.penaltyStrategy = s;
        return this;
    }

    public GameBuilder withTurnStrategy(TurnStrategy s) {
        this.turnStrategy = s;
        return this;
    }

    public Game build() {
        if (board == null)
            throw new IllegalStateException("Board must be set");
        return new Game(board, players, dice, startStrategy, endStrategy, penaltyStrategy, turnStrategy);
    }

    public static Board createBoard(int size, Map<Integer, Integer> jumps) {
        return new Board(size, jumps);
    }
}