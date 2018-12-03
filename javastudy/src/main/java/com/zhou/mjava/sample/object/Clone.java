package com.zhou.mjava.sample.object;

/**
 * @author liqingzhou on 17/9/30
 */
public class Clone {

    public static void main(String[] args) {

        // 非引用的数组拷贝
        String[] strings = {"aa","bb"};
        String[] strings1 =strings.clone();

        strings1[0]="11";
        System.out.println("strings[0] = " + strings[0]);


        P[] ps = new P[2];
        Teacher t1 = new Teacher("teacher1");
        Teacher t2 = new Teacher("teacher2");
        P p1 = new P(20, "p1", t1);
        P p2 = new P(20, "p2", t2);

        ps[0] = p1;
        ps[1] = p2;
        P[] ps2 = ps.clone();
        ps2[0].setAge(66);
        System.out.println("ps[0].getAge() = " + ps[0].getAge());
        System.out.println("ps2 = " + ps2);
        System.out.println("p1 = " + p1);
        System.out.println("p1clone=" + p1.clone());
        System.out.println("ps[0]==ps2[0]" + ps[0] + "==" + ps2[0]);
        System.out.println("ps[0].t==ps2[0].t" + ps[0].getTeacher() + "==" + ps2[0].getTeacher());
    }

    private static class P implements Cloneable {

        int age;
        String name;
        Teacher teacher;

        public P(int age, String name, Teacher teacher) {
            this.age = age;
            this.name = name;
            this.teacher = teacher;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Teacher getTeacher() {
            return teacher;
        }

        public void setTeacher(Teacher teacher) {
            this.teacher = teacher;
        }

        @Override
        public P clone() {
            try {
                return (P) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }

    private static class Teacher {

        String name;

        public Teacher(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
