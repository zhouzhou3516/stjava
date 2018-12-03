package com.zhou.mjava.sample.lambda;

import com.zhou.mjava.model.Person;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by liqingzhou on 17/6/3.
 */
public class LambdaExample {
    public static void main(String[] args) {
        //Any match
        testListMatch();
        //
        Runnable runnable;
        runnable = ()-> System.out.println("hello lambda~");
        new Thread(runnable).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        FunctionalInterfaceExample.test();



        Person[] people = new Person[10];
        for(int i =0;i<10;i++){
            people[i] = new Person();
            people[i].setName("Nmae"+i);
            people[i].setAge(i);
        }
        System.out.println(people.toString());
        //匿名内部类方式
        Arrays.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return new Integer(p1.getAge()).compareTo(new Integer(p1.getAge()));
            }
        });
        /******lambda 方式*****/
        Arrays.sort(people,(p1,p2)->new Integer(p1.getAge()).compareTo(new Integer(p1.getAge())));

        /*****通过 方法引用(Method Reference) 简写lambda表达式******/


        //Arrays.stream(people).forEach(p->{p = new Person();p.setAge(10);p.setName("hello");});


        // 1. 引用类静态方法
        Arrays.sort(people,Person::compareByAge);

        // 2. 引用对象的实例方法

        Person person = new Person();
        Arrays.sort(people,person::compareByName);

        // 3. 引用 类型对象(String Int Float ...) 的实例方法，必需是实例对象
        String[] strings = {"Hello","world"};
        // 使用lambda表达式
        Arrays.sort(strings,(a,b)->a.compareToIgnoreCase(b));
        // 使用类型对象的实例方法
        Arrays.sort(strings,String::compareToIgnoreCase);

        // 4. 引用构造方法

        //map() 用来转换原集合中的元素
        String[] words = {"this","is","a","nice","day"};
        List<String> wordList = Arrays.asList(words);
        wordList.stream().map((word)->word.toUpperCase()).collect(Collectors.toList()).forEach(System.out::println);
        wordList.stream().map(String::toUpperCase).map(word->word+"-").forEach(word->System.out.print(word));
    }

    class MString {
       public String s;
        public int compareToIgnoreCase (MString ms){
            return s.compareToIgnoreCase(ms.s);
        }
    }


    public  static void testListMatch(){
        String[] arr = {"111","222"};
        boolean result = Arrays.asList(arr).stream().anyMatch(s->"222".equals(s));
        System.out.println(result);
    }

}
