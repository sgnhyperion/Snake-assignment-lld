import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Integer> jumps = new HashMap<>();
        // ladders
        jumps.put(3, 22);
        jumps.put(5, 8);
        jumps.put(11, 26);
        // snakes
        jumps.put(27, 1);
        jumps.put(21, 9);
        jumps.put(17, 4);

        Board board = GameBuilder.createBoard(30, jumps);

        List<Player> players = new ArrayList<>();
        players.add(new AIPlayer("Shadow"));
        players.add(new HumanPlayer("Harsh"));

        Dice dice = new StandardDice(6, 12345L);

        StartStrategy startStrategy = new NeedsSixToStart(6);
        EndStrategy endStrategy = new ExactFinish();
        PenaltyStrategy penaltyStrategy = new KillOnOverlap(0);
        TurnStrategy turnStrategy = new ExtraTurnOnSix(6);

        Game game = new GameBuilder()
                .withBoard(board)
                .withPlayers(players)
                .withDice(dice)
                .withStartStrategy(startStrategy)
                .withEndStrategy(endStrategy)
                .withPenaltyStrategy(penaltyStrategy)
                .withTurnStrategy(turnStrategy)
                .build();

        game.play();
    }
}