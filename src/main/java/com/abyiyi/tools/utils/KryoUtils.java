package com.abyiyi.tools.utils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by dongqingsong on 2020/2/18.
 */
public class KryoUtils {

    private static final ThreadLocal<Kryo> kryos = new ThreadLocal<Kryo>(){
        @Override
        protected Kryo initialValue(){
        Kryo kryo=new Kryo();
            return kryo;
        }
    };

    public byte[] serialize(Object obj){
        Kryo kryo=kryos.get();
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        Output output=new Output(byteArrayOutputStream);
        kryo.writeClassAndObject(output,obj);
        output.close();
        return byteArrayOutputStream.toByteArray();
    }


    public <T> T deserialize(byte[] bytes){
        Kryo kryo = kryos.get();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Input input = new Input(byteArrayInputStream);
        input.close();
        return (T) kryo.readClassAndObject(input);
    }

    public static void main(String[] args){
        KryoUtils kryoUtils=new KryoUtils();
        for(int i=0;i<1000;i++){
            String person=new String("idea");
            byte[] bytes=kryoUtils.serialize(person);
            String newPerson=kryoUtils.deserialize(bytes);
            System.out.println(newPerson.toString());
        }
    }


}
