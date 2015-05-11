package me.hatter.tools.jtop.util;

import me.hatter.tools.commons.args.UnixArgsutil;

public class EnvUtil {

    public static String getPid() {
        return UnixArgsutil.ARGS.args()[0];
    }

    public static long getSleepMillis() {
        if (UnixArgsutil.ARGS.args().length > 1) {
            return Long.parseLong(UnixArgsutil.ARGS.args()[1]);
        }
        return 2000L;
    }

    public static int getDumpCount() {
        if (UnixArgsutil.ARGS.args().length > 2) {
            return Integer.parseInt(UnixArgsutil.ARGS.args()[2]);
        }
        return 1;
    }

    public static int getThreadTopN() {
        return getInt("thread", (getAdvanced() ? Integer.MAX_VALUE : 5));
    }

    public static int getStacktraceTopN() {
        return getInt("stack", 8);
    }

    public static String getSize() {
        return getStr("size", "b");
    }

    public static boolean getAdvanced() {
        return UnixArgsutil.ARGS.flags().containsAny("A", "advanced");
    }

    public static boolean getColor() {
        return UnixArgsutil.ARGS.flags().containsAny("C", "color");
    }

    public static boolean getSortMem() {
        return UnixArgsutil.ARGS.flags().containsAny("M", "sortmem");
    }

    public static String getStr(String key, String def) {
        String val = UnixArgsutil.ARGS.kvalue(key);
        return (val == null) ? def : val;
    }

    public static long getLong(String key, long def) {
        String val = UnixArgsutil.ARGS.kvalue(key);
        return (val == null) ? def : Long.parseLong(val);
    }

    public static int getInt(String key, int def) {
        String val = UnixArgsutil.ARGS.kvalue(key);
        return (val == null) ? def : Integer.parseInt(val);
    }
}
