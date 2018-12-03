package com.zhou.mjava.sample.proxy;

/**
 * @author liqingzhou on 18/9/20
 */
public class UserServiceImpl implements UserService {

    @Override
    public String getUser() {
        System.out.println("getUser");
        return "liqingzhou";
    }
}
