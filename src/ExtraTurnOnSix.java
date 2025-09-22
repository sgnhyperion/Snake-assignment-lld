public class ExtraTurnOnSix implements TurnStrategy {
    private final int extraOnValue;

    public ExtraTurnOnSix(int extraOnValue) {
        this.extraOnValue = extraOnValue;
    }

    @Override
    public boolean extraTurn(int roll) {
        return roll == extraOnValue;
    }
}