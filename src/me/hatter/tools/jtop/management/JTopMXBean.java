package me.hatter.tools.jtop.management;

import me.hatter.tools.jtop.rmi.interfaces.JClassLoadingInfo;
import me.hatter.tools.jtop.rmi.interfaces.JGCInfo;
import me.hatter.tools.jtop.rmi.interfaces.JMemoryInfo;
import me.hatter.tools.jtop.rmi.interfaces.JThreadInfo;

public interface JTopMXBean {

    public static final String JTOP_MXBEAN_NAME = "me.hatter.management:type=JTop";

    JMemoryInfo getMemoryInfo();

    JGCInfo[] getGCInfos();

    JClassLoadingInfo getClassLoadingInfo();

    JThreadInfo[] listThreadInfos();
}
