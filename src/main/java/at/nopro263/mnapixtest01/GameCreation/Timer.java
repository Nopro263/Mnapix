package at.nopro263.mnapixtest01.GameCreation;

public class Timer {

    private int time = -1;
    private boolean started = false;

    public Timer() {

    }
    public void setTime(int t) {
        time = t;
        started = true;
    }
    public boolean hasEnded() {
        return time == 0;
    }
    public boolean hasStarted() {
        return started;
    }
    public int modulo() {
        return time % 20;
    }
    public int seconds() {
        return time / 20;
    }
    public void tick() {
        time--;
    }
}
