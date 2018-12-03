package com.zhou.mjava.consult.doctor;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.lang.StringUtils;

/**
 * @author liqingzhou on 18/2/26
 */
public class Client {

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.fun1();
    }

    public void fun1() throws IOException {
        File file = new File(getClass().getClassLoader().getResource("doctorNo.txt").getFile());
        File countFile = new File(
                getClass().getClassLoader().getResource("doctorNoCount.txt").getFile());
        File allDocFile = new File(
                getClass().getClassLoader().getResource("doctor_name.txt").getFile());
        Map<String, Integer> map = Maps.newHashMap();
        Files.readLines(file, Charsets.UTF_8, new LineProcessor<Object>() {
            @Override
            public boolean processLine(String line) throws IOException {
                map.put(line, 0);
                return true;
            }

            @Override
            public Object getResult() {
                return null;
            }
        });

        Files.readLines(countFile, Charsets.UTF_8, new LineProcessor<Object>() {
            @Override
            public boolean processLine(String line) throws IOException {
                String[] args = line.split("\t");

                map.put(args[0], Integer.valueOf(args[1]));
                return true;
            }

            @Override
            public Object getResult() {
                return null;
            }
        });
        System.out.println("map=" + map);
        System.out.println(map.size());
        List<Item> list = Lists.newArrayList();
        map.forEach((key, value) -> {
            Item item = new Item();
            item.setCount(value);
            item.setDoctorNo(key);
            list.add(item);
        });

        Files.readLines(allDocFile, Charsets.UTF_8, new LineProcessor<Object>() {
            @Override
            public boolean processLine(String line) throws IOException {
                String[] args = line.split("\t");
                Item find = find(list, args[0]);
                if (find != null) {
                    find.setName(args[1]);
                    String phone = args[2].substring(1,args[2].length()-1);
                    find.setPhone(phone);
                }
                return true;
            }

            @Override
            public Object getResult() {
                return null;
            }
        });

        list.sort(Comparator.comparing(Item::getCount));

        list.forEach(item -> System.out.println(item.getDoctorNo() + " "+item.getName()+" " + item.getCount()+" "+item.getPhone()));
    }

    public boolean contains(List<Item> list, String key) {

        return list.stream().filter(item -> StringUtils.equals(item.getDoctorNo(), key)).findFirst()
                .isPresent();
    }

    public Item find(List<Item> list, String key) {
        Optional<Item> optional = list.stream()
                .filter(item -> StringUtils.equals(item.getDoctorNo(), key)).findFirst();
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
}
