package com.zhou.mjava.sample.javautil;

/**
 * @author liqingzhou on 18/3/5
 */
public class ResourceClient {

    public static void main(String[] args) {
        ResourceConfig config = ResourceConfig.getOrNull("benmu-app.properties");
        String organization = config.getString("organization");
        System.out.println(organization);
    }
}
