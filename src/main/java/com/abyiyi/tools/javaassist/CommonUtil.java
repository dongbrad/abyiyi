package com.abyiyi.tools.javaassist;

/**
 * Created by dongqingsong on 2020/2/11.
 */
public class CommonUtil {


    public static String[] addString(String[] sa,String str) {
        String[] newsa ;
        if(sa == null){
            newsa = new String[]{str};
        }else{
            int len = sa.length;
            for (int i = 0; i < len; i++) {
                if(sa[i].equals(str)){
                    return sa;
                }
            }

            newsa = new String[len +1];
            System.arraycopy(sa,0,newsa,0,len);
            newsa[len]=str;
        }

        return newsa;
    }


    public static String[] addString(String[] sa,String str[]) {
        if(str == null ||str.length ==0){
            return sa;
        }
        for (int i = 0; i < str.length; i++) {
            sa =addString(sa,str[i]);
        }
        return sa;
    }


    public static String getPackageName(Class cls){
        return getPackageName(cls.getName());
    }

    public static String getPackageName(String name){
        int pos = name.lastIndexOf('.');
        return (pos ==-1)?"":name.substring(0,pos);
    }


    public static String lastPart(String name){
        int pos = name.lastIndexOf('.');
        return (pos ==-1)?"":name.substring(pos+1);
    }


}
