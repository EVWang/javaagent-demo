package com.ev.demo;

import java.lang.management.ManagementFactory;

/**
 * @Author: wangyw
 * @Date: 2019/10/8 9:36 PM
 * @Desc:
 * @Version 1.0
 */
public class Base {

    public static void main(String[] args) {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String s = name.split("@")[0];
        //打印当前Pid
        System.out.println("pid:"+s);
        while (true) {
            try {
                Thread.sleep(5000L);
            } catch (Exception e) {
                break;
            }
            process();
        }
    }


    public static void process(){
        System.out.println("process!");
    }

//    public String process1(String param1, String param2){
//        System.out.println(param1+"process!");
//
//        return param1;
//    }


}
