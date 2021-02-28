package com.abyiyi.tools.javaassist;

import javassist.ClassPool;
import javassist.LoaderClassPath;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongqingsong on 2020/2/11.
 */
public class JavassistUtil {

    private static final ClassPool rootcp = new ClassPool();

    private static final List<ClassLoader> cpcls = new ArrayList<>();

    private static final List<String> added_imports = new ArrayList<>();

    static {
        addClassLoader(JavassistUtil.class.getClassLoader());
    }


    public  static synchronized void addClassLoader(ClassLoader cl){
        if(cl != null && !cpcls.contains(cl)){
            cpcls.add(cl);
            rootcp.insertClassPath(new LoaderClassPath(cl));
        }
    }

    public static void addImports(String[] imports){
        if(imports != null){
            for (int i = 0; i < imports.length; i++) {
                String str = imports[i];
                if(!added_imports.contains(str)){
                    rootcp.importPackage(str);
                    added_imports.add(str);
                }
            }
        }
    }

    public static ClassPool getClassPool(){
        return rootcp;
    }


}

