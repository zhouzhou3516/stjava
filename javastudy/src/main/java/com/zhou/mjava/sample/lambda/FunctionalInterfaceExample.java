package com.zhou.mjava.sample.lambda;

import java.util.function.Consumer;

/**
 * Created by liqingzhou on 17/6/3.
 */
public interface FunctionalInterfaceExample {
    // java 8 supports static mehtod for interface
    public static void test(){
        System.out.println("hello functionalInterfaceExample");
    }

    default void defaultFun(){
        System.out.println("default function");
    }



}
