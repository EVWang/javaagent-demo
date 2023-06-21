package com.ev.javaagent.premain;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.CodeAttribute;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @Author: wangyw
 * @Date: 2019/9/22 7:50 PM
 * @Desc:
 * @Version 1.0
 */
public class PremainAgentTransformer implements ClassFileTransformer {

    private final static String injectedClassname = "com.ev";//TestJavaagent

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        className = className.replaceAll("/",".");
//        System.out.println("transform>className:"+className);
        if(className.startsWith(injectedClassname)){
            CtClass ctClass = null;
            try {
                ClassPool pool = ClassPool.getDefault();
                ctClass = pool.get(className);//javassist获取字节码类
                CtMethod[] ctMethods = ctClass.getMethods();
                for(CtMethod ctMethod : ctMethods){
                    CodeAttribute ca = ctMethod.getMethodInfo2().getCodeAttribute();
                    if(ca == null){
                        continue;
                    }
                    if(!ctMethod.isEmpty()){
                        ctMethod.insertBefore("System.out.println(\"agenttransform>hello Im agent : " + ctMethod.getName() +" start\");");
                        ctMethod.insertAfter("System.out.println(\"agenttransform>hello Im agent : " + ctMethod.getName() +" end\");");
                    }
                }
                return ctClass.toBytecode();
            }catch (Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

        }

        return new byte[0];
    }
}

