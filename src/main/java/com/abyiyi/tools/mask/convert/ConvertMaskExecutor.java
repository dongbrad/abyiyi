package com.abyiyi.tools.mask.convert;

import com.abyiyi.tools.javaassist.ClassCreator;
import com.abyiyi.tools.mask.chain.IMaskExector;
import com.abyiyi.tools.mask.rules.MaskRuleLoader;
import com.abyiyi.tools.mask.rules.MaskRuleModel;
import com.abyiyi.tools.utils.Dom4jParserUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by dongqingsong on 2020/2/12.
 */
public class ConvertMaskExecutor {

    public List buildMaskExecutor(String orgCode){
        List result = new LinkedList();
        MaskRuleLoader.getInstance();
        Map map= (Map) Dom4jParserUtil.paraseXml();
        List<MaskRuleModel> list =(List) map.get(orgCode);
        for (int i = 0; i < list.size(); i++) {
            ClassCreator cc = new ClassCreator();
            cc.setClassName("com.dj.mask.chain.IMaskExector_"+orgCode+"_"+i);
            cc.addInterface(IMaskExector.class);
            importClassFile(cc);
            MaskRuleModel mrm = (MaskRuleModel)list.get(i);
            String javacode = buildMaskExecutor(mrm);
            cc.addMethod(javacode);
            try {
                Class clz = cc.makeClass();
                IMaskExector me = (IMaskExector)clz.newInstance();
                result.add(me);
            }catch (Exception e){
                e.printStackTrace();
            }


        }
        return result;

    }

    private String buildMaskExecutor(MaskRuleModel mrm){
        String name = mrm.getName();
        String regex = mrm.getRegex();
        String identifier = mrm.getIdentifier();
        String startindex = mrm.getStartindex();
        String endindex = mrm.getEndindex();
        StringBuffer sb = new StringBuffer();
        Map map = null;


        String code =
                "public String execute(String report) {\n" +
                "   System.out.println(report);\n"+
                "   Pattern p = Pattern.compile(\"<@name>@regex</@name>\");\n" +
                "   Matcher m = p.matcher(report);\n" +
                "   Map map = new HashMap();\n" +
                "   while(m.find()){\n" +
                "       String matcherString  = m.group(1);\n" +
                "       System.out.println(\"-11----------------------\"+m.group(0));\n"+
//                "       System.out.println(\"-00----------------------\"+m.group(1));\n"+
                "       int total = m.end() -m.start();\n" +
                "       int leftNode = (total - matcherString.length())/2;\n" +
                "       int replaceStart = m.start() + leftNode + @startindex;\n" +
                "       int replaceEnd = m.start() + leftNode + @endindex;\n" +
                "       map.put(Integer.valueOf(replaceStart),Integer.valueOf(replaceEnd));\n" +
                "   }\n" +
                "   //替换输入的字符串，并返回替换的字符串\n" +
                "   StringBuffer stringBuffer = new StringBuffer(report);\n" +
                "   Iterator entries = map.entrySet().iterator();\n"+
                "   while(entries.hasNext()) {\n" +
                "   java.util.Map.Entry entry = (java.util.Map.Entry) entries.next();\n"+
                "       Integer si = (Integer) entry.getKey();\n" +
                "       Integer ei = (Integer) entry.getValue();\n" +
                "       stringBuffer.replace(si.intValue(),ei.intValue(), CommonStringUtil.replaceIdentifier(si,ei,\"@identifier\"));\n" +
                "   }\n" +
//                "   System.out.println(stringBuffer.toString());\n"+
                "   return stringBuffer.toString();\n" +
                "}";

        code = code.replace("@name",name)
                .replace("@regex",regex)
                .replace("@identifier",identifier)
                .replace("@startindex",startindex)
                .replace("@endindex",endindex);
//        System.out.println("--"+code);

        return code;
    }


    private static void importClassFile(ClassCreator cc ){
        cc.addImport("java.util.HashMap");
        cc.addImport("java.util.Map");
        cc.addImport("java.util.regex.Matcher");
        cc.addImport("java.util.regex.Pattern");
        cc.addImport("java.util.HashMap");
        cc.addImport("java.util.List");
        cc.addImport("com.dj.utils.CommonStringUtil");
        cc.addImport("java.util.LinkedList");
        cc.addImport("java.util.Iterator");
        cc.addImport("java.util.ArrayList");
        cc.addImport("com.dj.mask.chain.MaskExecutor");
        cc.addImport("java.lang.StringBuffer");

    }

}
