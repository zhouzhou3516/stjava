package com.zhou.mjava.sample.polymorphism;

/**
 * @author liqingzhou on 17/8/30
 */
public class Base {
    public void getKey(){
        System.out.println("Base getKey");
    }

    public void consume(){
        getKey();
    }
}
