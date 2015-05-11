package me.hatter.tools.jtop.rmi.interfaces;

import java.beans.ConstructorProperties;
import java.io.Serializable;

public class JMemoryInfo implements Serializable {

    private static final long serialVersionUID = 1585393014793775503L;
    private JMemoryUsage      heap;
    private JMemoryUsage      nonHeap;

    @ConstructorProperties({ "heap", "nonHeap" })
    public JMemoryInfo(JMemoryUsage heap, JMemoryUsage nonHeap) {
        this.heap = heap;
        this.nonHeap = nonHeap;
    }

    public JMemoryUsage getHeap() {
        return heap;
    }

    public void setHeap(JMemoryUsage heap) {
        this.heap = heap;
    }

    public JMemoryUsage getNonHeap() {
        return nonHeap;
    }

    public void setNonHeap(JMemoryUsage nonHeap) {
        this.nonHeap = nonHeap;
    }
}
