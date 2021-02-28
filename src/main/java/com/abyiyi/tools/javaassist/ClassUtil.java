package com.abyiyi.tools.javaassist;

import sun.reflect.Reflection;

/**
 * Created by dongqingsong on 2020/2/11.
 */
public class ClassUtil {
    public static ClassLoader getClassLoader(Class caller){
        ClassLoader callerCL = getCallerClass0(caller).getClassLoader();
        ClassLoader contextC1 = getContextClassLoader();
        if(callerCL ==contextC1){
            return callerCL;

        }
        if(contextC1 ==null){
            return callerCL;
        }
        if(ClassUtil.isAncestor(contextC1,callerCL)){
            return callerCL;
        }else{
            return contextC1;
        }
    }


    public static boolean isAncestor(ClassLoader src,ClassLoader dst){
        if(src == null || dst== null){
            return false;
        }
        ClassLoader cl = dst;
        do{
            cl = cl.getParent();
            if(cl ==src){
                return true;
            }
        }while (cl!=null);
        return false;
    }

    private static Class getCallerClass0(Class caller){
        int index = 3;
        boolean find_end = false;
        while (true){
            Class cls = Reflection.getCallerClass(index++);
            if(cls == null){
                return caller;

            }else if(cls !=caller){
                if(find_end){
                    return cls;
                }
            }else{
                find_end =true;
            }
        }
    }

    public static ClassLoader getContextClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }
}
