package com.abyiyi.tools.tmp.builder;

/**
 * Created by dongqingsong on 2019/1/28.
 */
public class Test {
    public static void main(String[] argv){
        Person person = new Person.Builder().name("StephenHe").age(20).sex(true).build();
        System.out.println(person.getName() + "\n" + person.getAge() + "\n" + person.isSex());
    }
}
