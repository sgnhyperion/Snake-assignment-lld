public class MoveResult {
    private final Player player;
    private final int roll;
    private final int from;
    private final int to;
    private final boolean won;

    public MoveResult(Player player, int roll, int from, int to, boolean won) {
        this.player = player;
        this.roll = roll;
        this.from = from;
        this.to = to;
        this.won = won;
    }

    public Player getPlayer() {
        return player;
    }

    public int getRoll() {
        return roll;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public boolean isWon() {
        return won;
    }

    @Override
    public String toString() {
        return String.format("%s rolled %d: %d -> %d%s", player.getName(), roll, from, to, (won ? " [WIN]" : ""));
    }
}