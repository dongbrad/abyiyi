package com.abyiyi.tools.tmp.test;

/**
 * Created by dongqingsong on 2018/3/28.
 */
public class People {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args){
        People p=new People();
        Class clazz=p.getClass();
        try {
            Class  cla = Class.forName("java.lang.String");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Class clazz3=People.class;
    }
}
