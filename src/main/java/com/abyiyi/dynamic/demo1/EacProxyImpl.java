package com.abyiyi.dynamic.demo1;

import org.codehaus.janino.ClassBodyEvaluator;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by dongqingsong on 2020/2/14.
 */
public class EacProxyImpl {

    private static Map<String, String> map = new HashMap<String, String>();
    static {
        //用map模拟存储的脚本，脚本可以是很复杂，这里只作简单测试
        map.put("0", "return new EacProxy(){	public String query(){		return \"xxx交通管理局子公司分部一\";	}};");
        map.put("1", "return new EacProxy(){	public String query(){		return \"sss交通管理局子公司分部二\";	}};");
        map.put("2", "return new EacProxy(){	public String query(){		return \"阿里杭州萧山一公司\";	}};");
        map.put("3", "return new EacProxy(){	public String query(){		return \"菜鸟物流下沙分仓库\";	}};");
    }

    @RequestMapping("/janino")
    public String sayHello() throws Exception {
        ClassBodyEvaluator ce = new ClassBodyEvaluator();//class body
        String key = String.valueOf(new Random().nextInt(4));
        String source = generateProxySource(map.get(key));
        ce.cook(source);
        Object obj = ce.getClazz().getMethod("createProxy").invoke(null);
        EacProxy ep = (EacProxy) obj;
        return ep.query();
    }

    public static String generateProxySource(String source) throws IOException {
        String template = StreamUtils.copyToString(EacProxyImpl.class.getResourceAsStream("/EacProxy.temp"),
                Charset.forName("utf-8"));
        return template.replaceAll("#replaced#", source);
    }

    public static void main(String[] args) throws Exception{
            EacProxyImpl e = new EacProxyImpl();
            System.out.println(e.sayHello());;
    }

}
