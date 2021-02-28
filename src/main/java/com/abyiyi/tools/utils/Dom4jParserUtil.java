package com.abyiyi.tools.utils;
import java.io.*;
import java.util.*;

import com.abyiyi.tools.mask.rules.MaskRuleModel;
import org.dom4j.*;
import org.dom4j.io.*;

/**
 * Created by dongqingsong on 2020/2/13.
 */
public class Dom4jParserUtil {

    public static final String claaPath = Dom4jParserUtil.class.getResource("/").getPath();


    public static Map paraseXml() {
        long lasting = System.currentTimeMillis();
        try {
            System.out.println(claaPath);
            File f = new File(claaPath + "/mask/crcc.xml");
            SAXReader reader = new SAXReader();
            Document doc = reader.read(f);
            Element root = doc.getRootElement();
            Element foo;
            Map map = new HashMap();
            List<MaskRuleModel> list = new ArrayList<MaskRuleModel>();
            for (Iterator i = root.elementIterator("column"); i.hasNext(); ) {
                foo = (Element) i.next();
                MaskRuleModel mrm = new MaskRuleModel();
                String name = foo.elementText("name");
                String identifier = foo.elementText("identifier");
                String regex = foo.elementText("regex");
                String startindex = foo.elementText("startindex");
                String endindex = foo.elementText("endindex");
                mrm.setName(name);
                mrm.setIdentifier(identifier);
                mrm.setRegex(regex);
                mrm.setStartindex(startindex);
                mrm.setEndindex(endindex);
                list.add(mrm);
            }
            map.put("CRCC", list);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        paraseXml();
    }
}