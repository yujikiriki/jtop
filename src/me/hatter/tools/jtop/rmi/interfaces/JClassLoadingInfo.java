package me.hatter.tools.jtop.rmi.interfaces;

import java.beans.ConstructorProperties;
import java.io.Serializable;

public class JClassLoadingInfo implements Serializable {

    private static final long serialVersionUID = 8354447788997580832L;
    private long              totalLoadedClassCount;
    private int               loadedClassCount;
    private long              unloadedClassCount;

    @ConstructorProperties({ "totalLoadedClassCount", "loadedClassCount", "unloadedClassCount" })
    public JClassLoadingInfo(long totalLoadedClassCount, int loadedClassCount, long unloadedClassCount) {
        this.totalLoadedClassCount = totalLoadedClassCount;
        this.loadedClassCount = loadedClassCount;
        this.unloadedClassCount = unloadedClassCount;
    }

    public long getTotalLoadedClassCount() {
        return totalLoadedClassCount;
    }

    public void setTotalLoadedClassCount(long totalLoadedClassCount) {
        this.totalLoadedClassCount = totalLoadedClassCount;
    }

    public int getLoadedClassCount() {
        return loadedClassCount;
    }

    public void setLoadedClassCount(int loadedClassCount) {
        this.loadedClassCount = loadedClassCount;
    }

    public long getUnloadedClassCount() {
        return unloadedClassCount;
    }

    public void setUnloadedClassCount(long unloadedClassCount) {
        this.unloadedClassCount = unloadedClassCount;
    }
}
