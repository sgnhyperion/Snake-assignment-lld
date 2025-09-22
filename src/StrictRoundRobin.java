public class StrictRoundRobin implements TurnStrategy {
    @Override
    public boolean extraTurn(int roll) {
        return false;
    }
}