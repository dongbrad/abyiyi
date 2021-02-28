package com.abyiyi.tools.client.http;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URI;
import java.util.*;

/**
 * Created by dongqingsong on 2021/2/28.
 */
public class HttpClientTemplate {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientTemplate.class);


    @Autowired
    private CloseableHttpClient customHttpClient;

    /**
     * 通过post方式调用http接口
     * @param url     url路径
     * @param jsonParam    json格式的参数
     * @param reSend     重发次数
     * @return
     * @throws Exception
     */
    public String doPostByJson(String url, String jsonParam,int reSend) {
        //声明返回结果
        String result = "";
        //开始请求API接口时间
        long startTime=System.currentTimeMillis();
        //请求API接口的响应时间
        long endTime= 0L;
        HttpEntity httpEntity = null;
        CloseableHttpResponse httpResponse = null;

        try {

            // 设置请求头和报文
            HttpPost httpPost = new HttpPost(url);
            Header header=new BasicHeader("Accept-Encoding",null);

            httpPost.setHeader(header);
            // 设置报文和通讯格式
            StringEntity stringEntity = new StringEntity(jsonParam,"UTF-8");
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            logger.info("请求{}接口的参数为{}",url,jsonParam);
            //执行发送，获取相应结果
            httpResponse = customHttpClient.execute(httpPost);
            httpEntity= httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);

            System.out.println("响应状态为:" + httpResponse.getStatusLine());
            if (httpEntity != null) {
                System.out.println("响应内容长度为:" + httpEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(httpEntity));
            }
        }catch (ClientProtocolException e) {
            e.printStackTrace();
        }catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (customHttpClient != null) {
                    customHttpClient.close();
                }
                if (httpResponse != null) {
                    httpResponse.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //请求接口的响应时间
        endTime=System.currentTimeMillis();
        logger.info("请求{}接口的响应报文内容为{},本次请求API接口的响应时间为:{}毫秒",url,result,(endTime-startTime));
        return result;

    }


    /**
     * 通过post方式调用http接口
     * @param url     url路径
     * @param map    json格式的参数
     * @param reSend     重发次数
     * @return
     * @throws Exception
     */
    public String httpPost(String url, Map<String,String> map, int reSend) {
        logger.info("===>>>调用[post]接口开始...===>>> URL:" + url);
        Long beginTime = System.currentTimeMillis();
        //声明返回结果
        String result = "";
        //开始请求API接口时间
        long startTime=System.currentTimeMillis();
        //请求API接口的响应时间
        long endTime= 0L;
        HttpEntity httpEntity = null;
        UrlEncodedFormEntity entity = null;
        HttpResponse httpResponse = null;
        HttpClient httpClient = null;
        try {
            // 设置请求头和报文
            HttpPost httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String,String> elem = (Map.Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
            }
            entity = new UrlEncodedFormEntity(list,"UTF-8");
            httpPost.setEntity(entity);
            logger.info("请求{}接口的参数为{}",url,map);
            //执行发送，获取相应结果
            httpResponse = httpClient.execute(httpPost);
            httpEntity= httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (Exception e) {
            logger.error("请求{}接口出现异常",url,e);
            if (reSend > 0) {
                logger.info("请求{}出现异常:{}，进行重发。进行第{}次重发",url,e.getMessage(),(3));
                result = httpPost(url, map, reSend - 1);
                if (result != null && !"".equals(result)) {
                    return result;
                }
            }
        }finally {
            try {
                EntityUtils.consume(httpEntity);
            } catch (IOException e) {
                logger.error("http请求释放资源异常",e);
            }
        }
        //请求接口的响应时间
        endTime=System.currentTimeMillis();
        logger.info("请求{}接口的响应报文内容为{},本次请求API接口的响应时间为:{}毫秒",url,result,(endTime-startTime));
        return result;

    }



    public String httpPost(String url, JSONObject param) {
        String content = null;
        HttpPost httpPost = new HttpPost(url);

        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        StringEntity se = null;
        try {
            se = new StringEntity(JSONObject.toJSONString(param));
            se.setContentType("text/json");
            httpPost.setEntity(se);

            CloseableHttpResponse response = null;
            response = customHttpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            content = EntityUtils.toString(httpEntity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (customHttpClient != null) {
                try {
                    customHttpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    }


    /**
     *普通的GET请求
     */
    public void doHttpGet(String url,Map map) throws Exception {
        // 创建Httpclient对象
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        URI uri = new URIBuilder("http://www.baidu.com/s").setParameter("wd", "java").build();
        // 创建http GET请求
        try {
            // 执行请求
            response = customHttpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                //请求体内容
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                //内容写入文件
                System.out.println("内容----："+content);
                System.out.println("长度："+content.length());
            }
        } finally {
            if (response != null) {
                response.close();
            }
            //相当于关闭浏览器
            customHttpClient.close();
        }
    }






}
