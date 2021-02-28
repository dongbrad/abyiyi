package com.abyiyi.tools.mask.chain;



import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dongqingsong on 2020/2/12.
 */
public class MaskExecutor implements IMaskExector {
    @Override
    public String execute(String report) {
        String regex = "<PB>(.+?)</PB>";
        //将规则封装成对象
        Pattern p = Pattern.compile(regex);
        //让正则表达式和想要作用的对象关联在一起
        Matcher m = p.matcher(report);
        //存储正则表达式匹配的结果
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        while(m.find()){
            System.out.println("-----"+m.group(0));
            //匹配的字符串
            String matcherString  = m.group(1);
            //匹配的字符串总长度
            int total = m.end() -m.start();
            //xml的左边节点的长度
            int leftNode = (total - matcherString.length())/2;
            //要匹配的替换的字符串的开始位置
            int replaceStart = m.start() + leftNode + 2;
            //要匹配的替换的字符串的开始位置
            int replaceEnd = m.start() + leftNode + 5;
            map.put(replaceStart,replaceEnd);
        }

        //替换输入的字符串，并返回替换的字符串
        StringBuffer stringBuffer = new StringBuffer(report);
        for (Map.Entry<Integer,Integer> entry :map.entrySet()) {
            Integer si = (Integer) entry.getKey();
            Integer ei = (Integer) entry.getValue();
            stringBuffer.replace(si,ei, this.replaceIdentifier(si,ei,"*"));
        }
        return stringBuffer.toString();
    }

    public String replaceIdentifier(java.lang.Integer s,java.lang.Integer e,java.lang.String identifier){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < e-s; i++) {
            sb.append(identifier);
        }

        return sb.toString();
    }


}
