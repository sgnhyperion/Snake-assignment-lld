public class CrossFinish implements EndStrategy {
    @Override
    public boolean hasWon(Player player, int boardSize) {
        return player.getPosition() >= boardSize;
    }
}