public class NeedsSixToStart implements StartStrategy {
    // 6
    private final int starterValue;

    public NeedsSixToStart(int starterValue) {
        this.starterValue = starterValue;
    }

    @Override
    public boolean canStart(Player player, int roll) {
        if (player.hasStarted())
            return true;
        if (roll == starterValue) {
            player.setStarted(true);
            return true;
        }
        return false;
    }
}