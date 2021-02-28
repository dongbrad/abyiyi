package com.abyiyi.tools.tmp.demo.demo2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import sun.misc.ProxyGenerator;
import java.io.*;
/**
 * Java学习交流QQ群：589809992 我们一起学Java！
 */
public class DynamicProxy
{
    public static void main( String args[] )
    {
        RealSubject real = new RealSubject();
        Subject proxySubject = (Subject)Proxy.newProxyInstance(Subject.class.getClassLoader(),
                new Class[]{Subject.class},
                new ProxyHandler(real));

        proxySubject.doSomething();

        //write proxySubject class binary data to file
        createProxyClassFile();
    }

    public static void createProxyClassFile()
    {
        String name = "ProxySubject";
        byte[] data = ProxyGenerator.generateProxyClass( name, new Class[] { Subject.class } );
        try
        {
            FileOutputStream out = new FileOutputStream( name + ".class" );
            out.write( data );
            out.close();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}
