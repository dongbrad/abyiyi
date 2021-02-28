package com.abyiyi.tools.mask.convert;

import com.abyiyi.tools.javaassist.ClassCreator;
import com.abyiyi.tools.mask.chain.IMaskExecutorChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dongqingsong on 2020/2/12.
 */
public class ConvertMaskChain {
    private static final Logger logger = LoggerFactory.getLogger(ConvertMaskExecutor.class);

    private final static String LINE_STR = System.getProperty("line.separator");


    private final static String setPosition =
            "public void setPosition(int position) {\n" +
                    "   this.position = position;\n" +
                    "}";


    private final static String getPosition =
            "public String getReport() {\n" +
                    "   return report;\n" +
                    "}";

    private final static String setReportStr =
            "public void setReport(String report) {\n" +
                    "   this.report = report;\n" +
                    "}";

    private final static String getReportStr =
            "public int getPosition() {\n" +
                    "   return position;\n" +
                    "}";


    private final static String setMaskExecutorListStr =
            "public void setMaskExecutorList(java.util.List maskExectorList) {\n" +
                    "        this.maskExectorList = maskExectorList;\n" +
                    "    }";


    private final static String getMaskExecutorsListStr =
            "public List getMaskExectorList() {\n" +
                    "   return maskExectorList;\n" +
                    "   }\n";



    private static final String executorChainStr =
            "public void execute(){" +
                    "   while (position< this.maskExectorList.size()){\n" +
                    "       IMaskExector exec = this.getMaskExectorList().get(position);\n" +
                    "       position =position +1;\n" +
                    "       report = exec.execute(this.report);\n" +
                    "   }"+
                    "   System.out.println(\"--------MASK SUCCESS ------\");" +
                    "}";


    public IMaskExecutorChain getMaskExectorInstance(String orgCode){

        IMaskExecutorChain instance = null;
        ClassCreator cc = new ClassCreator();
        cc.setClassName("com.dj.mask.chain.IMaskExecutorChain"+orgCode);
        cc.addInterface(IMaskExecutorChain.class);
        ccImportClassFile(cc);
        cc.addField("private String report;");
        cc.addField("private int position;");
        cc.addField("private List maskExectorList;");
        cc.addMethod(setPosition);
        cc.addMethod(getPosition);
        cc.addMethod(getReportStr);
        cc.addMethod(setReportStr);
        cc.addMethod(setMaskExecutorListStr);
        cc.addMethod(getMaskExecutorsListStr);
        cc.addMethod(executorChainStr);

        try{
            Class clazz = cc.makeClass();
            instance = (IMaskExecutorChain)clazz.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return instance;
    }


    public void ccImportClassFile(ClassCreator cc){
        cc.addImport("java.util.Map");
        cc.addImport("java.util.Entry");
        cc.addImport("java.util.Set");
        cc.addImport("java.util.Objects");
        cc.addImport("java.util.Iterator");
        cc.addImport("java.util.List");
        cc.addImport("java.util.ArrayList");
        cc.addImport("java.io.File");
        cc.addImport("com.dj.mask.chain.IMaskExector");
    }





}
