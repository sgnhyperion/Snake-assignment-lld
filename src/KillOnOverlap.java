import java.util.List;

public class KillOnOverlap implements PenaltyStrategy {
    private final int sendToPosition;

    public KillOnOverlap() {
        this(0);
    }

    public KillOnOverlap(int sendToPosition) {
        this.sendToPosition = sendToPosition;
    }

    @Override
    public void apply(List<Player> players, Player current) {
        int pos = current.getPosition();
        for (Player p : players) {
            if (!p.getId().equals(current.getId()) && p.getPosition() == pos) {
                p.setPosition(sendToPosition);
                p.setStarted(false);
            }
        }
    }
}