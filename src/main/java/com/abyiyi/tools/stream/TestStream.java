package com.abyiyi.tools.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


/**
 * Created by dongqingsong on 2018/12/13.
 */
public class TestStream {
    public static void main(String[] args) {
        List<People> list = createPeople();
        Stream<People> stream = list.stream();
        Stream<People> stream1 = list.stream();
        Stream<People> stream2 = list.stream();
        stream.forEach(people -> {
            System.out.println(people.getAge());
        });

        stream1.filter(people -> people.getAge() >20).forEach(people -> {
            System.out.println(people.toString());
        });

        System.out.println(
                stream2.filter(people -> people.getAge() >20)
                .mapToDouble(peo ->peo.getAge())
                .average().getAsDouble()
        );
    }

    static List<People> createPeople(){
        ArrayList list = new ArrayList();
        list.add(new People("z",22));
        list.add(new People("q",11));
        list.add(new People("w",13));
        list.add(new People("e",44));
        list.add(new People("r",55));
        return list;
    }
}
