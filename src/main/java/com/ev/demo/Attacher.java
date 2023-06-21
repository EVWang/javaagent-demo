package com.ev.demo;

import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

/**
 * @Author: wangyw
 * @Date: 2019/10/16 6:04 PM
 * @Desc:
 * @Version 1.0
 */
public class Attacher {
    public static void main(String[] args) throws AttachNotSupportedException {
        String pid = "17709";
        try {
            // 传入目标 JVM pid
            VirtualMachine vm = VirtualMachine.attach(pid);
            //加载代理
            vm.loadAgent("/Users/EvilWang/Documents/IntelliJWorkspace/github/Study/javaagentdemo/target/javaagent-demo-1.0-SNAPSHOT.jar");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
