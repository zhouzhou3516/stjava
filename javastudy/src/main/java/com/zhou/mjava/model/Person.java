package com.zhou.mjava.model;

/**
 * Created by liqingzhou on 17/6/3.
 */
public class Person {

    private String name;
    private int age;

    public static int compareByAge(Person p1, Person p2) {
        p2.test();
        return new Integer(p1.getAge()).compareTo(new Integer(p2.getAge()));

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int compareByName(Person p1, Person p2) {
        return p1.name.compareToIgnoreCase(p2.name);

    }

    private void test() {
    }
}
