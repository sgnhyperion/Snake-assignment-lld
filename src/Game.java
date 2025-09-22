import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Game {
    private final Board board;
    private final List<Player> players;
    private final Dice dice;
    private final StartStrategy startStrategy;
    private final EndStrategy endStrategy;
    private final PenaltyStrategy penaltyStrategy;
    private final TurnStrategy turnStrategy;

    private boolean finished;
    private Player winner;

    public Game(Board board,
            List<Player> players,
            Dice dice,
            StartStrategy startStrategy,
            EndStrategy endStrategy,
            PenaltyStrategy penaltyStrategy,
            TurnStrategy turnStrategy) {
        this.board = Objects.requireNonNull(board);
        if (players == null || players.size() < 2)
            throw new IllegalArgumentException("At least two players required");
        this.players = new ArrayList<>(players);
        this.dice = Objects.requireNonNull(dice);
        this.startStrategy = Objects.requireNonNull(startStrategy);
        this.endStrategy = Objects.requireNonNull(endStrategy);
        this.penaltyStrategy = Objects.requireNonNull(penaltyStrategy);
        this.turnStrategy = Objects.requireNonNull(turnStrategy);
        this.finished = false;
        this.winner = null;
    }

    public void play() {
        System.out.println("Starting Game: boardSize=" + board.getSize() + ", players=" + players.size());
        int idx = 0;
        while (!finished) {
            Player current = players.get(idx);
            int roll = dice.roll();

            boolean allowedToMove = startStrategy.canStart(current, roll);

            if (!allowedToMove) {
                System.out.printf("%s rolled %d but cannot start yet.\n", current.getName(), roll);
                if (!turnStrategy.extraTurn(roll)) {
                    idx = (idx + 1) % players.size();
                }
                continue;
            }

            int from = current.getPosition();
            int tentative = from + roll;

            int to;
            if (tentative > board.getSize()) {
                if (endStrategy instanceof ExactFinish) {
                    to = from;
                } else {
                    to = tentative;
                }
            } else {
                to = tentative;
            }

            if (to >= 1 && to <= board.getSize()) {
                int dest = board.getDestination(to);
                if (dest != to) {
                    System.out.printf("%s encountered jump: %d -> %d\n", current.getName(), to, dest);
                }
                to = dest;
            }

            current.setPosition(to);

            penaltyStrategy.apply(players, current);

            boolean won = endStrategy.hasWon(current, board.getSize());
            if (won) {
                finished = true;
                winner = current;
                MoveResult result = new MoveResult(current, roll, from, current.getPosition(), true);
                System.out.println(result);
                System.out.printf("Winner is %s!\n", current.getName());
                break;
            } else {
                MoveResult result = new MoveResult(current, roll, from, current.getPosition(), false);
                System.out.println(result);
            }

            if (!turnStrategy.extraTurn(roll)) {
                idx = (idx + 1) % players.size();
            } else {
                System.out.printf("%s gets an extra turn!\n", current.getName());
            }
        }

        System.out.println("Game finished.");
        printFinalPositions();
    }

    public Player getWinner() {
        return winner;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    private void printFinalPositions() {
        System.out.println("Final Positions:");
        for (Player p : players) {
            System.out.printf("%s -> %d\n", p.getName(), p.getPosition());
        }
    }
}