package me.hatter.tools.jtop.main.objects;

import java.util.HashMap;
import java.util.Map;

import me.hatter.tools.jtop.rmi.interfaces.JClassLoadingInfo;
import me.hatter.tools.jtop.rmi.interfaces.JMemoryInfo;

public class MainOutput {

    private int                     round;
    private int                     totalThreadCount;
    private long                    totalCpuTime;
    private long                    totalUserTime;
    private JMemoryInfo             jMemoryInfo;
    private JClassLoadingInfo       jClassLoadingInfo;
    private Map<Long, ThreadOutput> threadMap = new HashMap<Long, ThreadOutput>();

    public MainOutput(int round) {
        this.round = round;
    }

    public int getRound() {
        return round;
    }

    public int getTotalThreadCount() {
        return totalThreadCount;
    }

    public void setTotalThreadCount(int totalThreadCount) {
        this.totalThreadCount = totalThreadCount;
    }

    public long getTotalCpuTime() {
        return totalCpuTime;
    }

    public void setTotalCpuTime(long totalCpuTime) {
        this.totalCpuTime = totalCpuTime;
    }

    public long getTotalUserTime() {
        return totalUserTime;
    }

    public void setTotalUserTime(long totalUserTime) {
        this.totalUserTime = totalUserTime;
    }

    public JMemoryInfo getjMemoryInfo() {
        return jMemoryInfo;
    }

    public void setjMemoryInfo(JMemoryInfo jMemoryInfo) {
        this.jMemoryInfo = jMemoryInfo;
    }

    public JClassLoadingInfo getjClassLoadingInfo() {
        return jClassLoadingInfo;
    }

    public void setjClassLoadingInfo(JClassLoadingInfo jClassLoadingInfo) {
        this.jClassLoadingInfo = jClassLoadingInfo;
    }

    public Map<Long, ThreadOutput> getThreadMap() {
        return threadMap;
    }

    public void setThreadMap(Map<Long, ThreadOutput> threadMap) {
        this.threadMap = threadMap;
    }
}
