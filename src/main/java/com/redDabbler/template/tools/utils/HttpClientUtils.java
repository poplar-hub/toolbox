package com.redDabbler.template.tools.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientUtils {

    private HttpClientUtils(){
    }

    public static JSONObject postForJSON(String url,JSONObject request){
        HttpClient client = HttpClientBuilder.create().setProxy(new HttpHost("127.0.0.1",1080)).build();
        HttpPost post = new HttpPost(url);
        JSONObject response = null;
        try {
            StringEntity s = new StringEntity(request.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");
            post.setEntity(s);
            HttpResponse res = client.execute(post);
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = res.getEntity();
                String responseStr = EntityUtils.toString(entity);
                response = JSON.parseObject(responseStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static JSONObject  getForJSON(String url){
        HttpClient httpClient =  HttpClientBuilder.create().setProxy(new HttpHost("127.0.0.1",1080)).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader(HttpHeaders.CONTENT_TYPE,"application/json");
        JSONObject responseBody = null ;

        try {
            HttpResponse response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = response.getEntity();
                String responseStr = EntityUtils.toString(entity);
                responseBody = JSON.parseObject(responseStr);
            }
        } catch (IOException e) {
            System.out.println("get request:"+url+" occur IOException:"+e.getLocalizedMessage());
            e.printStackTrace();
        }

        return responseBody;


    }

}
