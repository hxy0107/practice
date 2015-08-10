package com.hxy.code.net;

import com.hxy.code.model.Model;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by xianyu.hxy on 2015/8/4.
 */
public class MyPost {
    public String doPost(String url,String mycode,String value){
        String result=null;
        HttpResponse httpResponse=null;
        HttpPost post=new HttpPost(Model.HTTPURL+url);
        DefaultHttpClient client=new DefaultHttpClient();
        client.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT,30*1000);
        client.getParams().setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT,10*1000);
        ArrayList<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("mycode",mycode));
        nameValuePairs.add(new BasicNameValuePair("value",value));

        try {
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs,"utf-8"));
            httpResponse=client.execute(post);
            if(httpResponse.getStatusLine().getStatusCode()==200){
                result= EntityUtils.toString(httpResponse.getEntity(),"utf-8");
            }else {
                result=null;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
