package me.hatter.tools.jtop.rmi;

import me.hatter.tools.commons.jmx.CustomJMXConnectTool;
import me.hatter.tools.jtop.agent.Agent;
import me.hatter.tools.jtop.management.JTopMXBean;

public class RmiClient {

    private String     pid;
    private JTopMXBean jtopMXBean;

    public RmiClient(String pid) {
        this.pid = pid;
    }

    synchronized public JTopMXBean getJTopMXBean() {
        if (jtopMXBean == null) {
            CustomJMXConnectTool tool = new CustomJMXConnectTool(pid, Agent.AGENT_INIT_KEY, Agent.class);
            jtopMXBean = tool.getCustomMXBean(JTopMXBean.class, JTopMXBean.JTOP_MXBEAN_NAME);
        }
        return jtopMXBean;
    }
}
