package com.ev.javaagent.attach;

import com.ev.demo.Base;

import java.lang.instrument.Instrumentation;

/**
 * @Author: wangyw
 * @Date: 2019/10/16 7:10 PM
 * @Desc:
 * @Version 1.0
 */
public class AttachAgent {

    public static void agentmain(String args, Instrumentation inst){
        //指定我们自己定义的Transformer，在其中利用Javassist做字节码替换. true表示可以重载
        inst.addTransformer(new AttachTransformer(),true);
        try {
            //重定义类并载入新的字节码
            inst.retransformClasses(Base.class);
            System.out.println("Agent Load Done.");
        } catch (Exception e) {
            System.out.println("agent load failed!");
        }

    }
}
