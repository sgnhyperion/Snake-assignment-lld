import java.util.UUID;

public class AIPlayer implements Player {
    private final String id;
    private final String name;
    private int position;
    private boolean started;

    public AIPlayer(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.position = 0;
        this.started = false;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int pos) {
        this.position = pos;
    }

    @Override
    public boolean hasStarted() {
        return started;
    }

    @Override
    public void setStarted(boolean started) {
        this.started = started;
    }
}