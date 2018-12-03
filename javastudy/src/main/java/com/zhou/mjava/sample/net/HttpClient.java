package com.zhou.mjava.sample.net;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by liqingzhou on 17/7/31.
 */
public class HttpClient {

    private SocketClient socketClient;
    private URL url;
    private String defaultProtocal = "http";

    public HttpClient(String url, int port) {
        try {
            this.url = new URL(defaultProtocal + "://" + url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        socketClient = new SocketClient(url, port);
    }

    public String doGet() {
        initHeaders();

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = socketClient.readLine()) != null) {
            response.append(line + "\n");
        }
        socketClient.close();
        return response.toString();
    }

    private void initHeaders() {
        socketClient.writeln("GET https://www.baidu.com  HTTP/1.0\r\n");
        socketClient.writeln("Host: " + url.getHost() + "\r\n");
        socketClient.writeln("Accept:text/html\r\n");
        socketClient.writeln("Accept-Language: zh-CN\r\n");
        socketClient.writeln("\r\n", true);
    }

}
