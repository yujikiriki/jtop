有一个专门处理任务型的应用发现有停止处理业务逻辑。

通过top看到，系统几个核CPU占用率非常高（100%），且不处理正常业务：

```
top - 03:07:48 up 232 days,  5:14,  2 users,  load average: 3.77, 3.49, 3.43
Tasks: 127 total,   1 running, 126 sleeping,   0 stopped,   0 zombie
Cpu0  : 10.6%us,  0.3%sy,  0.0%ni, 89.0%id,  0.0%wa,  0.0%hi,  0.0%si,  0.0%st
Cpu1  :  0.0%us,  0.0%sy,  0.0%ni, 98.7%id,  1.3%wa,  0.0%hi,  0.0%si,  0.0%st
Cpu2  :  0.0%us,  0.0%sy,  0.0%ni,100.0%id,  0.0%wa,  0.0%hi,  0.0%si,  0.0%st
Cpu3  :100.0%us,  0.0%sy,  0.0%ni,  0.0%id,  0.0%wa,  0.0%hi,  0.0%si,  0.0%st
Cpu4  :100.0%us,  0.0%sy,  0.0%ni,  0.0%id,  0.0%wa,  0.0%hi,  0.0%si,  0.0%st
Cpu5  :  0.0%us,  0.3%sy,  0.0%ni, 99.7%id,  0.0%wa,  0.0%hi,  0.0%si,  0.0%st
Cpu6  :100.0%us,  0.0%sy,  0.0%ni,  0.0%id,  0.0%wa,  0.0%hi,  0.0%si,  0.0%st
Cpu7  :  0.0%us,  0.0%sy,  0.0%ni,100.0%id,  0.0%wa,  0.0%hi,  0.0%si,  0.0%st
Mem:  10485760k total,  9227556k used,  1258204k free,   201184k buffers
Swap:  2097144k total,      116k used,  2097028k free,  1643884k cached
```

从上面top数据可以看到，有三个核 CPU3,4,6 占用的CPU一直是 100%，那发生了什么事情呢？

接着使用jtop查看，命令如下：
```
$ java -jar jtop.jar -thread 3 -stack 8 <PID> 2000 100
```

得到的输出如下：
```
DefaultQuartzScheduler_Worker-8  TID=86  STATE=RUNNABLE  CPU_TIME=2110 (99.90%)  USER_TIME=2110 (99.90%) Allocted: 0
        java.util.WeakHashMap.put(WeakHashMap.java:405)
        org.aspectj.weaver.Dump.registerNode(Dump.java:253)
        org.aspectj.weaver.World.<init>(World.java:150)
        org.aspectj.weaver.reflect.ReflectionWorld.<init>(ReflectionWorld.java:50)
        org.aspectj.weaver.tools.PointcutParser.setClassLoader(PointcutParser.java:221)
        org.aspectj.weaver.tools.PointcutParser.<init>(PointcutParser.java:207)
        org.aspectj.weaver.tools.PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(PointcutParser.java:128)
        org.springframework.aop.aspectj.AspectJExpressionPointcut.<init>(AspectJExpressionPointcut.java:100)

DefaultQuartzScheduler_Worker-10  TID=88  STATE=RUNNABLE  CPU_TIME=2110 (99.90%)  USER_TIME=2110 (99.90%) Allocted: 0
        java.util.WeakHashMap.put(WeakHashMap.java:405)
        org.aspectj.weaver.Dump.registerNode(Dump.java:253)
        org.aspectj.weaver.World.<init>(World.java:150)
        org.aspectj.weaver.reflect.ReflectionWorld.<init>(ReflectionWorld.java:50)
        org.aspectj.weaver.tools.PointcutParser.setClassLoader(PointcutParser.java:221)
        org.aspectj.weaver.tools.PointcutParser.<init>(PointcutParser.java:183)
        org.aspectj.weaver.reflect.InternalUseOnlyPointcutParser.<init>(InternalUseOnlyPointcutParser.java:22)
        org.aspectj.weaver.reflect.Java15ReflectionBasedReferenceTypeDelegate.getDeclaredPointcuts(Java15ReflectionBasedReferenceTypeDelegate.java:243)

DefaultQuartzScheduler_Worker-6  TID=84  STATE=RUNNABLE  CPU_TIME=2100 (99.43%)  USER_TIME=2100 (99.43%) Allocted: 0
        java.util.WeakHashMap.put(WeakHashMap.java:405)
        org.aspectj.weaver.Dump.registerNode(Dump.java:253)
        org.aspectj.weaver.World.<init>(World.java:150)
        org.aspectj.weaver.reflect.ReflectionWorld.<init>(ReflectionWorld.java:50)
        org.aspectj.weaver.tools.PointcutParser.setClassLoader(PointcutParser.java:221)
        org.aspectj.weaver.tools.PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(PointcutParser.java:129)
        org.springframework.aop.aspectj.AspectJExpressionPointcut.<init>(AspectJExpressionPointcut.java:100)
        org.springframework.aop.aspectj.AspectJExpressionPointcut.<init>(AspectJExpressionPointcut.java:109)
```

发现这一行`java.util.WeakHashMap.put(WeakHashMap.java:405)`消耗CPU极高，接近100%， 通过查看JDK源代码，看到：
```java
399.    public V put(K key, V value) {
400.        K k = (K) maskNull(key);
401.        int h = HashMap.hash(k.hashCode());
402.        Entry[] tab = getTable();
403.        int i = indexFor(h, tab.length);
404.
405.        for (Entry<K,V> e = tab[i]; e != null; e = e.next) {
406.            if (h == e.hash && eq(k, e.get())) {
407.                V oldValue = e.value;
408.                if (value != oldValue)
409.                    e.value = value;
410.                return oldValue;
411.            }
412.        }
413.
414.        modCount++;
415.    Entry<K,V> e = tab[i];
416.        tab[i] = new Entry<K,V>(k, value, queue, h, e);
417.        if (++size >= threshold)
418.            resize(tab.length * 2);
419.        return null;
420.    }
```

从这里我们知道`WeakHashMap`是线程不安全的，而且这个版本的`aspectj`没有很好的使用这个类，再通过`jstack`查看完整的堆栈信息：
```
   java.lang.Thread.State: RUNNABLE
        at java.util.WeakHashMap.put(WeakHashMap.java:405)
        at org.aspectj.weaver.Dump.registerNode(Dump.java:253)
        at org.aspectj.weaver.World.<init>(World.java:150)
        at org.aspectj.weaver.reflect.ReflectionWorld.<init>(ReflectionWorld.java:50)
        at org.aspectj.weaver.tools.PointcutParser.setClassLoader(PointcutParser.java:221)
        at org.aspectj.weaver.tools.PointcutParser.<init>(PointcutParser.java:183)
        at org.aspectj.weaver.reflect.InternalUseOnlyPointcutParser.<init>(InternalUseOnlyPointcutParser.java:22)
        at org.aspectj.weaver.reflect.Java15ReflectionBasedReferenceTypeDelegate.getDeclaredPointcuts(Java15ReflectionBasedRef
erenceTypeDelegate.java:243)
        at org.aspectj.weaver.ReferenceType.getDeclaredPointcuts(ReferenceType.java:740)
        at org.aspectj.weaver.ResolvedType$7.get(ResolvedType.java:531)
        at org.aspectj.weaver.ResolvedType$7.get(ResolvedType.java:528)
        at org.aspectj.weaver.Iterators$3$1.hasNext(Iterators.java:128)
        at org.aspectj.weaver.Iterators$3.hasNext(Iterators.java:144)
        at org.aspectj.weaver.ResolvedType.findPointcut(ResolvedType.java:539)
        at org.aspectj.weaver.patterns.ReferencePointcut.resolveBindings(ReferencePointcut.java:149)
        at org.aspectj.weaver.patterns.Pointcut.resolve(Pointcut.java:196)
        at org.aspectj.weaver.tools.PointcutParser.resolvePointcutExpression(PointcutParser.java:314)
        at org.aspectj.weaver.tools.PointcutParser.parsePointcutExpression(PointcutParser.java:295)
        at org.springframework.aop.aspectj.AspectJExpressionPointcut.buildPointcutExpression(AspectJExpressionPointcut.java:15
9)
        at org.springframework.aop.aspectj.AspectJExpressionPointcut.checkReadyToMatch(AspectJExpressionPointcut.java:149)
        at org.springframework.aop.aspectj.AspectJExpressionPointcut.getClassFilter(AspectJExpressionPointcut.java:134)
        at org.springframework.aop.support.AopUtils.canApply(AopUtils.java:165)
        at org.springframework.aop.support.AopUtils.canApply(AopUtils.java:225)
        at org.springframework.aop.support.AopUtils.findAdvisorsThatCanApply(AopUtils.java:255)
        at org.springframework.aop.framework.autoproxy.AbstractAdvisorAutoProxyCreator.findEligibleAdvisors(AbstractAdvisorAut
oProxyCreator.java:73)
        at org.springframework.aop.framework.autoproxy.AbstractAdvisorAutoProxyCreator.getAdvicesAndAdvisorsForBean(AbstractAd
visorAutoProxyCreator.java:57)
        at org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator.postProcessAfterInitialization(AbstractAutoPro
xyCreator.java:255)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsAfterInitializa
tion(AbstractAutowireCapableBeanFactory.java:312)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapable
BeanFactory.java:1033)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBean
Factory.java:421)
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:264)
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:156)
        at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:640)
```

知道问题原因那么解决就简单了，原本想通过升级`aspectj`来解决，但因为一些特殊的历史原因无法简单的通过升级来解决，然后就采用了一种比较简单的解决方案，将调用`spring`的`getBean`的方法加上了`synchronized`修饰，问题解决。



