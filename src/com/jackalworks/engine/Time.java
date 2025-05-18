package jackalworks.engine;

public class Time {

    public static final long SECOND = 1_000_000_000L;

    private static double delta;

    public static long getTime() {
        return System.nanoTime();
    }

    public static double getDelta() {
        return delta;
    }

    public static void setDelta(double delta) {
        Time.delta = delta;
    }
}
