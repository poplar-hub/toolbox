package com.redDabbler.template.tools.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.JSONToken;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

public class HttpClentUtils {

    public static JSONObject post(String url,JSONObject json){
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        JSONObject response = null;
        try {
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");
            post.setEntity(s);

            HttpResponse res = client.execute(post);
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = res.getEntity();
                String charset = EntityUtils.getContentCharSet(entity);
                response = new JSONObject(new JSONTokener(new InputStreamReader(entity.getContent(),charset)));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;

    }

    public static  get(String url){
        HttpClient httpClient =  HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response ;
        try {
          response = httpClient.execute(httpGet);
        } catch (IOException e) {
            System.out.println("get request:"+url+"occur IOException:"+e.getLocalizedMessage());
            e.printStackTrace();
            return;
        }
        int statusCode = response.getStatusLine().getStatusCode();
        HttpEntity responseEntity = response.getEntity();

        EntityUtils.toString(responseEntity);

    }

}
