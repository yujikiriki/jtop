package me.hatter.tools.jtop.management;

import java.lang.management.ManagementFactory;

import javax.management.ObjectName;
import javax.management.StandardMBean;

import me.hatter.tools.jtop.rmi.RmiServer;
import me.hatter.tools.jtop.rmi.interfaces.JClassLoadingInfo;
import me.hatter.tools.jtop.rmi.interfaces.JGCInfo;
import me.hatter.tools.jtop.rmi.interfaces.JMemoryInfo;
import me.hatter.tools.jtop.rmi.interfaces.JThreadInfo;

public class JTopImpl extends StandardMBean implements JTopMXBean {

    private static RmiServer  rmiserver  = new RmiServer();
    private static JTopMXBean jTopMXBean = new JTopImpl();

    public JTopImpl() {
        super(JTopMXBean.class, true);
    }

    synchronized public static void registerMXBean() {
        try {
            ManagementFactory.getPlatformMBeanServer().registerMBean(jTopMXBean, new ObjectName(JTOP_MXBEAN_NAME));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JMemoryInfo getMemoryInfo() {
        return rmiserver.getMemoryInfo();
    }

    @Override
    public JGCInfo[] getGCInfos() {
        return rmiserver.getGCInfos();
    }

    @Override
    public JClassLoadingInfo getClassLoadingInfo() {
        return rmiserver.getClassLoadingInfo();
    }

    @Override
    public JThreadInfo[] listThreadInfos() {
        return rmiserver.listThreadInfos();
    }

}
