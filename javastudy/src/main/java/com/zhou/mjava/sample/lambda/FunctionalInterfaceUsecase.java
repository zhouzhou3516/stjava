package com.zhou.mjava.sample.lambda;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by liqingzhou on 17/7/26.
 */
public class FunctionalInterfaceUsecase {

    public static void main(String[] args) {
        // consumer
        Consumer<String> consumer = s -> System.out.println(s);
        List<String> list = Lists.newArrayList("1","2","3");
        list.forEach(consumer);
        // supplier
        Supplier<String> supplier = () -> "a new supplier string";
        System.out.println("supplier.get() = " + supplier.get());
        // function
        BiConsumer<String, Integer> biConsumer = (a, b) -> System.out.println("a=" + a + " b=" + b);
        Map<String, Integer> map = Maps.newHashMap();
        map.put("li", 10);
        map.put("wang", 20);
        map.put("zhang", 30);
        map.forEach(biConsumer);
        // 二元操作
        BinaryOperator binaryOperator;

    }

    public static  void f1(){

    }
}
