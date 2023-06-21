package com.ev.asm;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: wangyw
 * @Date: 2019/10/8 9:37 PM
 * @Desc: ASM 生成器
 * classReader读取字节码，然后交给MyClassVisitor类处理，处理完成后由ClassWriter写字节码并将旧的字节码替换掉。
 * @Version 1.0
 */
public class Generator {

    public static void main(String[] args) {
        try {
            //com/evjavastduy/jvm/asm/Base.evjavastduy
            ClassReader classReader = new ClassReader("com.ev.demo.Base");
            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            //handler
            ClassVisitor classVisitor = new MyClassVisitor(classWriter);

            classReader.accept(classVisitor,ClassReader.SKIP_DEBUG);
            byte[] data = classWriter.toByteArray();
            //output

            File f = new File("/Users/EvilWang/Documents/IntelliJWorkspace/github/Study/javaagentdemo/target/classes/com/ev/demo/Base.class");
            FileOutputStream fout = new FileOutputStream(f);
            fout.write(data);
            fout.close();
            System.out.println("asm 修改base类成功");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
