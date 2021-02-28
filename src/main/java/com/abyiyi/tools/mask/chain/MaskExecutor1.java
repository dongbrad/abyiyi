package com.abyiyi.tools.mask.chain;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dongqingsong on 2020/2/12.
 */
public class MaskExecutor1 implements IMaskExector {
    @Override
    public String execute(String report) {
        System.out.println("-11----------------------");
        System.out.println(report);
        Pattern p = Pattern.compile("<PC>(.+?)</PC>");
        Matcher m = p.matcher(report);
        Map map = new HashMap();
        while(m.find()){
            String matcherString  = m.group(1);
            System.out.println("-00----------------------"+matcherString);
            int total = m.end() -m.start();
            int leftNode = (total - matcherString.length())/2;
            int replaceStart = m.start() + leftNode + 3;
            int replaceEnd = m.start() + leftNode + 9;
            map.put(Integer.valueOf(replaceStart),Integer.valueOf(replaceEnd));
        }
        //替换输入的字符串，并返回替换的字符串
        StringBuffer stringBuffer = new StringBuffer(report);
        Iterator entries = map.entrySet().iterator();
        while(entries.hasNext()) {
            java.util.Map.Entry entry = (java.util.Map.Entry) entries.next();
            Integer si = (Integer) entry.getKey();
            Integer ei = (Integer) entry.getValue();
            stringBuffer.replace(si.intValue(),ei.intValue(), this.replaceIdentifier(si,ei,"#"));
        }
        System.out.println(stringBuffer.toString());
        System.out.println("-22----------------------");
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
