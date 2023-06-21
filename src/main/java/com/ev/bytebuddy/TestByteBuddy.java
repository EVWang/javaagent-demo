package com.ev.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.NamingStrategy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.Test;

/**
 * @Author: wangyw
 * @Date: 2019/11/10 2:20 PM
 * @Desc:
 * @Version 1.0
 */
public class TestByteBuddy {

    @Test
    public void test1() {
        try {
            Class<?> dynamicType = new ByteBuddy()
                    .subclass(Object.class)
                    .method(ElementMatchers.named("toString"))
                    .intercept(FixedValue.value("Hello world"))
                    .make()
                    .load(TestByteBuddy.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                    .getLoaded();
            Object instance = dynamicType.newInstance();
            String toString = instance.toString();
            System.out.println(toString);
            System.out.println(instance.getClass().getCanonicalName());
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Test
    public void test2(){
        ByteBuddyAgent.install();
        Foo foo = new Foo();
        new ByteBuddy()
                .redefine(Bar.class)
                .name(Foo.class.getName())
                .make()
                .load(Foo.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
        System.out.println(foo.m());
    }


    class Foo {
        String m() { return "foo"; }
    }

    class Bar {
        String m() { return "bar"; }
    }


    public static void main(String[] args) {
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .with(new NamingStrategy.AbstractBase() {
                    @Override
                    protected String name(TypeDescription typeDescription) {
                        return "i.love.ByteBuddy." + typeDescription.getSimpleName();
                    }

                })
                .subclass(Object.class)
                .make();

        DynamicType.Unloaded<?> dynamicType1 = new ByteBuddy()
                .with(new NamingStrategy.AbstractBase() {
                    @Override
                    protected String name(TypeDescription typeDescription) {
                        return "i.love.ByteBuddy." + typeDescription.getSimpleName();
                    }

                })
                .subclass(Object.class)
                .make();

            System.out.println(dynamicType.getTypeDescription().getCanonicalName());
            System.out.println(dynamicType1.getTypeDescription().getCanonicalName());

        ByteBuddy byteBuddy = new ByteBuddy();
            byteBuddy.with(new NamingStrategy.SuffixingRandom("suffix"));
        DynamicType.Unloaded<?> dynamicType3 = byteBuddy.subclass(Object.class).make();
            System.out.println(dynamicType3.getTypeDescription().getCanonicalName());

        ByteBuddy byteBuddy1 = new ByteBuddy();
        DynamicType.Unloaded<?> dynamicType4 = byteBuddy1.with(new NamingStrategy.SuffixingRandom("suffix")).
                subclass(Object.class).make();
            System.out.println(dynamicType4.getTypeDescription().getCanonicalName());

        ByteBuddy byteBuddy3 = new ByteBuddy();
        ByteBuddy suffix = byteBuddy3.with(new NamingStrategy.SuffixingRandom("suffix"));
        DynamicType.Unloaded<?> dynamicType5 = suffix.
                subclass(Object.class).make();
            System.out.println(dynamicType5.getTypeDescription().getCanonicalName());
    }


}
