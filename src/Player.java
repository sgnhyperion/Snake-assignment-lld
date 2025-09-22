public interface Player {
    String getId();

    String getName();

    int getPosition();

    void setPosition(int pos);

    boolean hasStarted();

    void setStarted(boolean started);

    default String shortDesc() {
        return getName() + "@" + getPosition();
    }
}