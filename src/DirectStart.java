public class DirectStart implements StartStrategy {
    @Override
    public boolean canStart(Player player, int roll) {
        if (!player.hasStarted())
            player.setStarted(true);
        return true;
    }
}