import java.util.Collections;
import java.util.Map;

public class Board {
    private final int size;
    private final Map<Integer, Integer> jumps;

    public Board(int size, Map<Integer, Integer> jumps) {
        if (size <= 0)
            throw new IllegalArgumentException("Board size must be > 0");
        this.size = size;
        this.jumps = jumps == null ? Collections.emptyMap() : Collections.unmodifiableMap(jumps);
    }

    public int getSize() {
        return size;
    }

    public int getDestination(int pos) {
        return jumps.getOrDefault(pos, pos);
    }

    public Map<Integer, Integer> getJumps() {
        return jumps;
    }
}