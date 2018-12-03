package com.zhou.mjava.sample.net;

/**
 * Created by liqingzhou on 17/7/31.
 */
public class MainClient {

    public static void main(String[] args) {
        t2();
    }

    //HTTP Client
    public static void t1() {
        String url = "localhost";
        HttpClient httpClient = new HttpClient(url, 7777);
        String response = httpClient.doGet();
        System.out.println(response);
    }

    // Socket Client
    public static void t2() {


    }
}
