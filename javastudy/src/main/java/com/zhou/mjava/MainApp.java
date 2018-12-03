package com.zhou.mjava;

import com.benmu.api.json.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;

/**
 * A Camel Application
 */
public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        int id = 123;
        String idStr = String.format("%011d", id);
        System.out.println(idStr);
        Set<String> stringSet = Sets.newHashSet("1", "2");
        List<String> stringList = Lists.newArrayList(stringSet);
        System.out.println("stringList=" + stringList.size());
        Integer a1 = 1;
        Integer b1 = 2;
        System.out.println(a1.compareTo(b1));

        System.out.println(
                "Optional.ofNullable(null).orElse(\"\") = " + Optional.ofNullable("asfdasfa")
                        .orElse(""));

        System.out.println(Runtime.getRuntime().availableProcessors());

        Map<String, Object> map = Maps.newHashMap();

        map.put("aaa", "123");
        map.put("bbb", "4444");
        System.out.println(JsonUtil.of(map));

        String content = "{\"content\":\"c#hello\"}";
        Map map1 = JsonUtil.of(content, new TypeReference<Map<String, Object>>() {

        });

        System.out.println("map1 = " + JsonUtil.of(map1));
        Number number = 3123123123123131231313131.131313131313123123123123123;
        System.out.println(number.toString());
        BigDecimal decimal = new BigDecimal(number.toString());
        System.out.println(decimal.toBigInteger());
        System.out.println(
                "new BigDecimal(number.toString()) = " + new BigDecimal(number.toString()));

        Integer a = 1235;
        BigDecimal bigDecimal = new BigDecimal("1235");
        System.out.println(" int equals bigdecimal = " + a.equals(bigDecimal));
    }


}

