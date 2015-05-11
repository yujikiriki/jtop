package me.hatter.tools.jtop.rmi.interfaces;

import java.beans.ConstructorProperties;
import java.io.Serializable;

public class JGCInfo implements Serializable {

    private static final long serialVersionUID = -2275633151997422389L;
    private String            name;
    private boolean           isValid;
    private String[]          memoryPoolNames;
    private long              collectionCount;
    private long              collectionTime;

    public JGCInfo() {
    }

    @ConstructorProperties({ "name", "isValid", "memoryPoolNames", "collectionCount", "collectionTime" })
    public JGCInfo(String name, boolean isValid, String[] memoryPoolNames, long collectionCount, long collectionTime) {
        this.name = name;
        this.isValid = isValid;
        this.memoryPoolNames = memoryPoolNames;
        this.collectionCount = collectionCount;
        this.collectionTime = collectionTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public String[] getMemoryPoolNames() {
        return memoryPoolNames;
    }

    public void setMemoryPoolNames(String[] memoryPoolNames) {
        this.memoryPoolNames = memoryPoolNames;
    }

    public long getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(long collectionCount) {
        this.collectionCount = collectionCount;
    }

    public long getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(long collectionTime) {
        this.collectionTime = collectionTime;
    }
}
