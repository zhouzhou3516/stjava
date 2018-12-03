package com.zhou.mjava.consult.chatreport;

import com.benmu.agile.Files;
import com.benmu.api.json.JsonUtil;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
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
        File file = new File(getClass().getClassLoader().getResource("a.json").getFile());
        //File outFile = new File(getClass().getClassLoader().getResource("orderNo1.txt").getFile());
        File outFile = new File("/Users/liqingzhou/benmu/project/consult/orderNo.txt");

        try {
            if(outFile.exists() == false){
                outFile.createNewFile();
            }
            FileWriter writer = new FileWriter(outFile, false);
            String content = Files.readString(file, "UTF-8");
            //System.out.println("content = " + content);
            OrderList outputs = JsonUtil.of(content, OrderList.class);
            List<Order> list = outputs.getHits();
            Map<String, List<String>> map = Maps.newHashMap();
            Map<String, List<String>> retMap = Maps.newConcurrentMap();
            list.forEach(item -> {
                OrderSource source = item.get_source();
                List<String> orderList = map
                        .computeIfAbsent(source.getDoc_no(), key -> Lists.newArrayList());
                orderList.add(source.getOrder_id());
            });
            map.forEach((key, value) -> {
                int total = value.size();
                String doc = key;
                int ret = total;
                //System.out.println(doc + ":" + total + ":" + ret);

                for (int i = 0; i < ret; i++) {
                    System.out.println(value.get(i));
                    try {
                        writer.write(value.get(i) + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            });

            System.out.println("outputs = " + outputs.getHits().size());
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fun2() {
        File file = new File(getClass().getClassLoader().getResource("out.txt").getFile());
        File outFile = new File(getClass().getClassLoader().getResource("report50.html").getFile());
        try {
            InputStream inputStream = new FileInputStream(file);
            String content = Files.readString(inputStream, "UTF-8");
            ReportOrderList outputs = JsonUtil.of(content, ReportOrderList.class);
            FileWriter writer = new FileWriter(outFile);

            System.out.println("s = " + outputs.getList().size());

            writeLine(writer, "<html><body>");
            writeLine(writer,
                    "<table border=\"solid 1px #000\"> <tr> <th width=\"80\">医生姓名</th><th>医生id</th><th>订单号</th><th width=\"30%\">病情描述</th><th>对话详情</th><th width=\"60\">评分</th><th width=\"80\">>评价详情</th></tr>\n");
            for (ReportOrder reportOrder : outputs.getList()) {
                OrderData data = reportOrder.getData();
                writeLine(writer, "<tr>");
                writeLine(writer, String.format("<td>%s</td>", data.getDocName()));
                writeLine(writer, String.format("<td>%s</td>", data.getDoctorNo()));
                writeLine(writer, String.format("<td>%s</td>", data.getOrderNo()));
                writeLine(writer, String.format("<td>%s</td>", data.getDesc()));

                writeLine(writer, String.format("<td>%s</td>", contertChat(data.getChatHistory())));
                writeLine(writer, String
                        .format("<td>%s</td>", data.getRate() == 0 ? "" : data.getRate()));
                writeLine(writer, String.format("<td>%s</td>",
                        StringUtils.isBlank(data.getRateContent()) ? "" : data.getRateContent()));

                writeLine(writer, "</tr>");
                writeLine(writer, "\n");
            }
            writeLine(writer, "</table>");
            writeLine(writer, "</html></body>");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeLine(FileWriter writer, String line) throws IOException {
        writer.write(line);
//        com.google.common.io.Files.write(line,);
    }

    public String contertChat(List<ChatHistory> histories) {
        StringBuilder builder = new StringBuilder();
        histories.forEach(item -> {
            if (item.getFromUserId().startsWith("D")) {
                builder.append("<span style=\"color:red\"><strong>医生:</strong></span>")
                        .append(convertContetn(item)).append("</br>");
            } else {
                builder.append("<strong>患者:</strong>").append(convertContetn(item)).append("</br>");
            }
        });
        return builder.toString();
    }

    private String convertContetn(ChatHistory history) {
        StringBuilder builder = new StringBuilder();
        if (StringUtils.equals(history.getContentType(), "RC:ImgMsg")) {
            builder.append("<img ").append("src=\"").append("data:image/jpeg;base64,")
                    .append(history.getContent()).append("\"").append("</img>");
        } else {
            builder.append(history.getContent());
        }
        return builder.toString();

    }


    public void clearInfoForFile(String fileName) {
        File file = new File(getClass().getClassLoader().getResource(fileName).getFile());
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
