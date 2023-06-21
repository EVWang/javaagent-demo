//package com.ev.javassist;
//
//import com.ev.demo.Base;
//import javassist.*;
//import javassist.bytecode.AccessFlag;
//import javassist.bytecode.CodeAttribute;
//import javassist.bytecode.LocalVariableAttribute;
//import javassist.bytecode.MethodInfo;
//
//import java.io.IOException;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @Author: wangyw
// * @Date: 2019/10/16 11:43 AM
// * @Desc:
// * @Version 1.0
// */
//public class JavassistTest extends ClassLoader {
//
//    public static void main(String[] args) throws Exception {
////        Base base = new Base();//JVM是不允许在运行时动态重载一个类的。
//        ClassPool cpool = ClassPool.getDefault();
//        CtClass ctClass = cpool.get(Base.class.getName());
//        String clazzName = ctClass.getName();
//
//        CtMethod ctMethod = ctClass.getDeclaredMethod("process1");
//        String methodName = ctMethod.getName();
//
//        // 方法信息：methodInfo.getDescriptor();
//        MethodInfo methodInfo = ctMethod.getMethodInfo();
//
//        // 方法：入参信息
//        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
//        //获取方法的入参的名称
//        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
//        //获取方法入参的类型
//        CtClass[] parameterTypes = ctMethod.getParameterTypes();
//
//        boolean isStatic = (methodInfo.getAccessFlags() & AccessFlag.STATIC) != 0;
//        int parameterSize = isStatic ? attr.tableLength() : attr.tableLength() - 1; // 静态类型取值
//        List<String> parameterNameList = new ArrayList<>(parameterSize);            // 入参名称
//        List<String> parameterTypeList = new ArrayList<>(parameterSize);            // 入参类型
//        StringBuilder parameters = new StringBuilder();                             // 参数组装；$1、$2...，$$可以获取全部，但是不能放到数组初始化
//
//        for (int i = 0; i < parameterSize; i++) {
//            parameterNameList.add(attr.variableName(i + (isStatic ? 0 : 1))); // 静态类型去掉第一个this参数
//            parameterTypeList.add(parameterTypes[i].getName());
//            if (i + 1 == parameterSize) {
//                //$1 $2 ... 用于获取不同位置的参数。$$ 可以获取全部入参，但是不太适合用在数值传递中。
//                parameters.append("$").append(i + 1);
//            } else {
//                parameters.append("$").append(i + 1).append(",");
//            }
//        }
//
//        CtClass returnType = ctMethod.getReturnType();
//        String returnTypeName = returnType.getName();
//
//        // 方法：生成方法唯一标识ID
//        int idx = Monitor.generateMethodId(clazzName, methodName, parameterNameList, parameterTypeList, returnTypeName);
//        ctMethod.addLocalVariable("startNanos", CtClass.longType);
//        //todo
//        ctMethod.addLocalVariable("parameterValues", cpool.get(Object[].class.getName()));
//
//        ctMethod.insertBefore("{ startNanos = System.nanoTime(); parameterValues = new Object[]{" + "$2" + "}; }");
////        ctMethod.insertBefore("{ System.out.println(\"start\"); }");
//        // 方法后加强
//        ctMethod.insertAfter("{ com.ev.javassist.Monitor.point(" + idx + ", startNanos, parameterValues, $_);}", false); // 如果返回类型非对象类型，$_ 需要进行类型转换
//
////        ctMethod.insertAfter("{ System.out.println(\"end\"); }");
//
////        Class clazz = ctClass.toClass();//JVM是不允许在运行时动态重载一个类的。
//
//        ctClass.writeFile("/Users/wangyiwei/IdeaProjects/Study/javaagentdemo/target/classes/");
//        ctClass.writeFile();
//
//        // 测试调用
//        byte[] bytes = ctClass.toBytecode();
//        Class<?> clazzNew = new JavassistTest().defineClass("com.ev.demo.Base", bytes, 0, bytes.length);
//
//        Method method = clazzNew.getMethod("process1",String.class, String.class);
//        Object obj_01 = method.invoke(clazzNew.newInstance(), "EVEEEEE","EVEEEEE2");
//        System.out.println("正确入参：" + obj_01);
//
////        Base h = (Base)clazz.newInstance();
////        h.process1("aaaaa");
//    }
//}
