package me.hatter.tools.jtop.rmi.interfaces;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.lang.Thread.State;
import java.lang.management.ThreadInfo;

public class JThreadInfo implements Serializable {

    private static final long   serialVersionUID = -8648817024047827694L;

    private long                cpuTime;
    private long                userTime;

    private String              threadName;
    private long                threadId;
    private long                blockedTime;
    private long                blockedCount;
    private long                waitedTime;
    private long                waitedCount;
    private long                alloctedBytes;
    private String              lockName;
    private long                lockOwnerId;
    private String              lockOwnerName;
    private boolean             inNative;
    private boolean             suspended;
    private Thread.State        threadState;
    private StackTraceElement[] stackTrace;

    @ConstructorProperties({ "cpuTime", "userTime", "threadName", "threadId", "blockedTime", "blockedCount",
            "waitedTime", "waitedCount", "alloctedBytes", "lockName", "lockOwnerId", "lockOwnerName", "inNative",
            "suspended", "threadState", "stackTrace" })
    public JThreadInfo(long cpuTime, long userTime, String threadName, long threadId, long blockedTime,
                       long blockedCount, long waitedTime, long waitedCount, long alloctedBytes, String lockName,
                       long lockOwnerId, String lockOwnerName, boolean inNative, boolean suspended, State threadState,
                       StackTraceElement[] stackTrace) {
        this.cpuTime = cpuTime;
        this.userTime = userTime;
        this.threadName = threadName;
        this.threadId = threadId;
        this.blockedTime = blockedTime;
        this.blockedCount = blockedCount;
        this.waitedTime = waitedTime;
        this.waitedCount = waitedCount;
        this.alloctedBytes = alloctedBytes;
        this.lockName = lockName;
        this.lockOwnerId = lockOwnerId;
        this.lockOwnerName = lockOwnerName;
        this.inNative = inNative;
        this.suspended = suspended;
        this.threadState = threadState;
        this.stackTrace = stackTrace;
    }

    public JThreadInfo(ThreadInfo threadInfo, long cpuTime, long userTime, long alloctedBytes) {
        this.threadName = threadInfo.getThreadName();
        this.threadId = threadInfo.getThreadId();
        this.blockedTime = threadInfo.getBlockedTime();
        this.blockedCount = threadInfo.getBlockedCount();
        this.waitedTime = threadInfo.getWaitedTime();
        this.waitedCount = threadInfo.getWaitedCount();
        this.lockName = threadInfo.getLockName();
        this.lockOwnerId = threadInfo.getLockOwnerId();
        this.lockOwnerName = threadInfo.getLockOwnerName();
        this.inNative = threadInfo.isInNative();
        this.suspended = threadInfo.isSuspended();
        this.threadState = threadInfo.getThreadState();
        this.stackTrace = StackTraceElement.convert(threadInfo.getStackTrace());
        this.cpuTime = cpuTime;
        this.userTime = userTime;
        this.alloctedBytes = alloctedBytes;
    }

    public JThreadInfo(JThreadInfo jThreadInfo, long cpuTime, long userTime, long alloctedBytes) {
        this.threadName = jThreadInfo.getThreadName();
        this.threadId = jThreadInfo.getThreadId();
        this.blockedTime = jThreadInfo.getBlockedTime();
        this.blockedCount = jThreadInfo.getBlockedCount();
        this.waitedTime = jThreadInfo.getWaitedTime();
        this.waitedCount = jThreadInfo.getWaitedCount();
        this.lockName = jThreadInfo.getLockName();
        this.lockOwnerId = jThreadInfo.getLockOwnerId();
        this.lockOwnerName = jThreadInfo.getLockOwnerName();
        this.inNative = jThreadInfo.getInNative();
        this.suspended = jThreadInfo.getSuspended();
        this.threadState = jThreadInfo.getThreadState();
        this.stackTrace = jThreadInfo.getStackTrace();
        this.cpuTime = cpuTime;
        this.userTime = userTime;
        this.alloctedBytes = alloctedBytes;
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

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    public long getBlockedTime() {
        return blockedTime;
    }

    public void setBlockedTime(long blockedTime) {
        this.blockedTime = blockedTime;
    }

    public long getBlockedCount() {
        return blockedCount;
    }

    public void setBlockedCount(long blockedCount) {
        this.blockedCount = blockedCount;
    }

    public long getWaitedTime() {
        return waitedTime;
    }

    public void setWaitedTime(long waitedTime) {
        this.waitedTime = waitedTime;
    }

    public long getWaitedCount() {
        return waitedCount;
    }

    public void setWaitedCount(long waitedCount) {
        this.waitedCount = waitedCount;
    }

    public long getAlloctedBytes() {
        return alloctedBytes;
    }

    public void setAlloctedBytes(long alloctedBytes) {
        this.alloctedBytes = alloctedBytes;
    }

    public String getLockName() {
        return lockName;
    }

    public void setLockName(String lockName) {
        this.lockName = lockName;
    }

    public long getLockOwnerId() {
        return lockOwnerId;
    }

    public void setLockOwnerId(long lockOwnerId) {
        this.lockOwnerId = lockOwnerId;
    }

    public String getLockOwnerName() {
        return lockOwnerName;
    }

    public void setLockOwnerName(String lockOwnerName) {
        this.lockOwnerName = lockOwnerName;
    }

    public boolean getInNative() {
        return inNative;
    }

    public void setInNative(boolean inNative) {
        this.inNative = inNative;
    }

    public boolean getSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public Thread.State getThreadState() {
        return threadState;
    }

    public void setThreadState(Thread.State threadState) {
        this.threadState = threadState;
    }

    public StackTraceElement[] getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(StackTraceElement[] stackTrace) {
        this.stackTrace = stackTrace;
    }
}
