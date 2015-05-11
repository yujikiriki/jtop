# README

This is a java version `top`.

# Usage

Get jtop:
Get `jtop` from `https://bitbucket.org/hatterjiang/jtop/src`.

Usage:
```
$ java -jar jtop.jar 
[ERROR] pid is not assigned.
Usage[b121209]:
java -jar jtop.jar [options] <pid> [<interval> [<count>]]
-OR-
java -cp jtop.jar jtop [options] <pid> [<interval> [<count>]]
    -size <B|K|M|G|H>             Size, case insensitive (default: B, H for human)
    -thread <N>                   Thread Top N (default: 5)
    -stack <N>                    Stacktrace Top N (default: 8)
    -excludes                     Excludes (string.contains)
    -includes                     Includes (string.contains, excludes than includes)
    --color                       Display color (default: off)
    --sortmem                     Sort by memory allocted (default: off)
    --summaryoff                  Do not display summary (default: off)
    --advanced                    Do display like 'top' (default: off)
```

Use this command to view the eclipse's top:
```
$ java -jar jtop.jar -size h -thread 3 -stack 4 387
```

You would see:
```
NEW ROUND ================================================== 
Heap Memory: INIT=40.00M  USED=145.16M  COMMITED=278.57M  MAX=379.88M
NonHeap Memory: INIT=23.19M  USED=74.11M  COMMITED=118.62M  MAX=304.00M
GC ParNew  VALID  [Par Eden Space, Par Survivor Space]  GC=103  GCT=1532
GC ConcurrentMarkSweep  VALID  [Par Eden Space, Par Survivor Space, CMS Old Gen, CMS Perm Gen]  GC=7  GCT=75
ClassLoading LOADED=8776  TOTAL_LOADED=8780  UNLOADED=4
Total threads: 24  CPU=30 (1.49%)  USER=21 (1.09%)
NEW=0  RUNNABLE=7  BLOCKED=0  WAITING=10  TIMED_WAITING=7  TERMINATED=0  
main  TID=1  STATE=RUNNABLE  CPU_TIME=20 (1.03%)  USER_TIME=13 (0.68%)
        org.eclipse.swt.internal.cocoa.OS.objc_msgSend_bool(Native Method)
        org.eclipse.swt.internal.cocoa.NSRunLoop.runMode(NSRunLoop.java:42)
        org.eclipse.swt.widgets.Display.sleep(Display.java:4193)
        org.eclipse.ui.application.WorkbenchAdvisor.eventLoopIdle(WorkbenchAdvisor.java:364)

RMI TCP Connection(2)-10.18.214.175  TID=102  STATE=RUNNABLE  CPU_TIME=9 (0.45%)  USER_TIME=8 (0.40%)
        sun.management.ThreadImpl.dumpThreads0(Native Method)
        sun.management.ThreadImpl.dumpAllThreads(ThreadImpl.java:433)
        me.hatter.tools.jtop.rmi.RmiServer.listThreadInfos(RmiServer.java:104)
        sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)

Poller SunPKCS11-Darwin  TID=11  STATE=TIMED_WAITING  CPU_TIME=0 (0.01%)  USER_TIME=0 (0.00%)
        java.lang.Thread.sleep(Native Method)
        sun.security.pkcs11.SunPKCS11$TokenPoller.run(SunPKCS11.java:692)
        java.lang.Thread.run(Thread.java:680)
```

Output like 'top' use arguemnt --A or --advanced:
```
$ java -jar jtop.jar --A --C 387
-OR-
$ java -jar jtop.jar ---AC 387
```


![jtop.png](https://bitbucket.org/repo/E9aogx/images/19642114-jtop.png)