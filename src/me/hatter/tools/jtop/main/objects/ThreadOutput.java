package me.hatter.tools.jtop.main.objects;

public class ThreadOutput {

    private Thread.State state;
    private long         cpuTime;
    private long         userTime;

    public Thread.State getState() {
        return state;
    }

    public void setState(Thread.State state) {
        this.state = state;
    }

    public long getCpuTime() {
        return cpuTime;
    }

    public void setCpuTime(long cpuTime) {
        this.cpuTime = cpuTime;
    }

    public long getUserTime() {
        return userTime;
    }

    public void setUserTime(long userTime) {
        this.userTime = userTime;
    }
}
