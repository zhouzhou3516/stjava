package com.zhou.mjava.consult.refreshNurseDoc;

import com.benmu.api.json.JsonUtil;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 * @author liqingzhou on 18/1/16
 */
public class Client {

    public static void main(String[] args) throws IOException {
        Client test = new Client();
        //test.fun1();
        test.fun1();

    }


    /**
     * 1.从es中查询需要结果集hits部分到a.json
     * 2.从a.json抽取{20%}提取订单号
     * 3.保存订单号
     * 4.调用bookingconsult1: door/chat.sh 获取整合的聊天记录out.txt
     * 5.调用fun2()获取html
     * 6.将html上传到服务器
     */

    public void fun1() {
        File file = new File(getClass().getClassLoader().getResource("nurse.txt").getFile());
        //File outFile = new File(getClass().getClassLoader().getResource("orderNo1.txt").getFile());
        File outFile = new File("/Users/liqingzhou/benmu/project/consult/orderNo.txt");

        try {
            List<String> lines = Files.readLines(file, Charsets.UTF_8);
            lines.forEach(line -> {
                String[] arr = line.split("\t");
                Map<String, Object> jsonMap = JsonUtil.of(arr[1], Map.class);
                List<Object> list = (List) jsonMap.get("doctorCatalogTagList");
                List<Object> ret = Lists.newArrayList();
//                System.out.println("array:"+list);
                list.forEach(tag -> {
                    Map<String, Object> tagMap = (Map) tag;
//                    System.out.println(tagMap.get("name"));
                    if (StringUtils.equals(tagMap.get("name").toString(), "初级医生") ||
                            StringUtils.equals(tagMap.get("name").toString(), "护士")) {
                        ret.add(tag);
                    }
                });
                jsonMap.put("doctorCatalogTagList",ret);
                System.out.println(arr[0]+"##"+JsonUtil.of(jsonMap));

            });
            if (outFile.exists() == false) {
                outFile.createNewFile();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
