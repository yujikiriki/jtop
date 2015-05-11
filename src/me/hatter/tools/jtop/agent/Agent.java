package me.hatter.tools.jtop.agent;

import java.lang.instrument.Instrumentation;

import me.hatter.tools.jtop.management.JTopImpl;
import sun.misc.VMSupport;

@SuppressWarnings("restriction")
public class Agent {

    public static final String     AGENT_INIT_KEY = "me.hatter.tools.jtop.init.key";

    private static Instrumentation instrumentation;

    public static void premain(String agentArgs, Instrumentation inst) throws Exception {
        domain(agentArgs, inst);
    }

    public static void agentmain(String agentArgs, Instrumentation inst) throws Exception {
        domain(agentArgs, inst);
    }

    public static void domain(String agentArgs, Instrumentation inst) throws Exception {
        if (instrumentation == null) {
            instrumentation = inst;
        }
        System.out.println("[INFO] " + Agent.class.getName() + "#domain(" + agentArgs + ", " + inst + ")");
        if (VMSupport.getAgentProperties().getProperty(AGENT_INIT_KEY) == null) {
            JTopImpl.registerMXBean();
            VMSupport.getAgentProperties().setProperty(AGENT_INIT_KEY, Boolean.TRUE.toString());
        }
    }
}
