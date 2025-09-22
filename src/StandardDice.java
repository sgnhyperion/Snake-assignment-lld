import java.util.Random;

public class StandardDice implements Dice {
    private final int faces;
    private final Random random;

    public StandardDice(int faces) {
        if (faces < 1)
            throw new IllegalArgumentException("faces must be >= 1");
        this.faces = faces;
        this.random = new Random();
    }

    public StandardDice(int faces, long seed) {
        if (faces < 1)
            throw new IllegalArgumentException("faces must be >= 1");
        this.faces = faces;
        this.random = new Random(seed);
    }

    @Override
    public int roll() {
        return 1 + random.nextInt(faces);
    }
}