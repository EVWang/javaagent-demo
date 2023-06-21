package com.ev.javaagent.premain;

import java.lang.instrument.Instrumentation;

/**
 * @Author: wangyw
 * @Date: 2019/9/22 7:46 PM
 * @Desc:
 * @Version 1.0
 */
public class PremainAgent {

    /**
     * public static void premain(String agentArgs, Instrumentation inst)
     * public static void premain(String agentArgs)
     * @param arg
     * @param instrumentation
     */
    public static void premain(String arg,Instrumentation instrumentation) {
        System.out.println("javaagent初始化=========premain方法执行========");
        System.err.println("agent startup , args is " + arg);
        instrumentation.addTransformer(new PremainAgentTransformer());

    }
}
